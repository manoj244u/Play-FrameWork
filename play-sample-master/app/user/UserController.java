package user;


import com.google.common.collect.ImmutableMap;
import global.common.BaseController;
import global.exceptions.CustomException;
import org.bson.types.ObjectId;
import play.data.Form;
import play.mvc.BodyParser;
import play.mvc.Result;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public final class UserController extends BaseController {

    private final UserService userService;

    @Inject
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public Result getUser(String userIdStr) {
        try {
            if (!ObjectId.isValid(userIdStr)) {
                return failure("Invalid User ID type");
            }
            UserModel user = userService.getUser(new ObjectId(userIdStr));
            return user != null ? success(ImmutableMap.of("user", user)) : failure("Invalid User ID");
        } catch (CustomException e) {
            return failure(e.getMessage());
        }
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result createUser() {
        try {
            final Form<UserRequestForm> userModelForm = formFactory.form(UserRequestForm.class).bindFromRequest();
            if (userModelForm.hasErrors()) {
                return failure(buildValidationErrorMessage(userModelForm.allErrors()));
            }
            final UserRequestForm userForm = userModelForm.get();
            final UserModel user = this.userService.createUser(userForm);
            return user != null ? success("successfully created user with ID: " + user.getId()) : failure("failed to create user");
        } catch (CustomException e) {
            return failure(e.getMessage());
        }
    }

    public Result updateUser(String userIdStr) {
        try {
            final Form<UserRequestForm> userModelForm = formFactory.form(UserRequestForm.class).bindFromRequest();
            if (userModelForm.hasErrors()) {
                return failure(buildValidationErrorMessage(userModelForm.allErrors()));
            }

            if (!ObjectId.isValid(userIdStr)) {
                return failure("Invalid User ID type");
            }

            final UserRequestForm userForm = userModelForm.get();
            final UserModel user = this.userService.updateUser(new ObjectId(userIdStr), userForm);
            return user != null ? success("successfully updated user") : failure("failed to update user");
        } catch (CustomException e) {
            return failure(e.getMessage());
        }
    }

    public Result deleteUser(String userIdStr) {
        try {
            if (!ObjectId.isValid(userIdStr)) {
                return failure("Invalid User ID type");
            }
            final boolean status = userService.deleteUser(new ObjectId(userIdStr));
            return status ? success("successfully deleted user") : failure("failed to delete user");
        } catch (CustomException e) {
            return failure(e.getMessage());
        }
    }

    public Result getAllUsers() {
        try {
            return success(ImmutableMap.of("users", userService.getAllUsers()));
        } catch (CustomException e) {
            return failure(e.getMessage());
        }
    }

}
