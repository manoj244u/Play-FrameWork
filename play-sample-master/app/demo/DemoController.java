package demo;

import com.google.common.collect.ImmutableMap;
import global.common.BaseController;
import global.exceptions.CustomException;
import play.data.Form;
import play.mvc.BodyParser;
import play.mvc.Result;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;


@Singleton
public final class DemoController extends BaseController {
    private final DemoService demoService;


    @Inject
    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }


    /* public Result getUser(String userIdStr) {
         try {
             if (!ObjectId.isValid(userIdStr)) {
                 return failure("InValid UserId Type");
             }
             DemoModel user = demoService.getUser(new ObjectId(userIdStr));
             return user != null ? success(ImmutableMap.of("user", user)) : failure("invalid User Id");
         } catch (CustomException e) {
             return failure(e.getMessage());
         }
     }*/
    /*public Result login(String email, String password) {
        try {
            *//*if (!String.isValid(email) && !ObjectId.isValid(password)) {
                return failure("InValid UserId Type");
            }*//*
            DemoModel user = demoService.login(email, password);
            return user != null ? ok("Login Successfully") : failure("invalid User password or email");
        } catch (CustomException e) {
            return failure(e.getMessage());
        }
    }
*/
    @BodyParser.Of(BodyParser.Json.class)
    public Result createUser() {
        try {
            final Form<DemoRequestForm> userModelForm = formFactory.form(DemoRequestForm.class).bindFromRequest();
            if (userModelForm.hasErrors()) {
                return failure(buildValidationErrorMessage(userModelForm.allErrors()));
            }
            final DemoRequestForm userForm = userModelForm.get();
            final DemoModel user = this.demoService.createUser(userForm);
            return user != null ? success("successfully created user with ID: " + user.getId()) : failure("failed to create user");
        } catch (CustomException e) {
            return failure(e.getMessage());

        }
    }



   /* @BodyParser.Of(BodyParser.Json.class)
    public Result loginUser() {
        try {
            final Form<loginRequestForm> loginModelForm = formFactory.form(loginRequestForm.class).bindFromRequest();
            if (loginModelForm.hasErrors()) {
                return failure(buildValidationErrorMessage(loginModelForm.allErrors()));
            }
            final loginRequestForm loginForm = loginModelForm.get();

            final DemoModel user = this.demoService.loginUser(loginForm);
            return user != null ? success("successfully logedin: " + user.getEmail()) : failure("failed to logedin");
        } catch (CustomException e) {
            return failure(e.getMessage());

        }
    }*/

    public Result loginUser() {
        try {

            final Form<loginRequestForm> loginModelForm = formFactory.form(loginRequestForm.class).bindFromRequest();
            final loginRequestForm loginForm = loginModelForm.get();
            Optional<DemoModel> demo = this.demoService.loginUser(loginForm);
            if (demo.isPresent()) {
                DemoModel demoModel = demo.get();
                String session = sessionService.generateSession();
                boolean status = sessionService.assignSessionToUser(demoModel.getId(), session,loginForm);

                return status ? success("successfully login", ImmutableMap.of("session", session)) : failure("failed to login");
            } else {
                return failure("Invalid Login credentials");
            }

        } catch (CustomException e) {
            return failure(e.getMessage());
        }
    }

    public Result logoutUser(String sessionToken) {
        boolean status = this.sessionService.deleteSession(sessionToken);
        return status ? success("You've been successfully logged out") : failure("not logout");
    }

}

