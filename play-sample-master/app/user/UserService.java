package user;

import global.exceptions.CustomException;
import org.bson.types.ObjectId;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class UserService {

    private final UserRepository repository;

    @Inject
    public UserService(final UserRepository repository) {
        this.repository = repository;
    }

    List<UserModel> getAllUsers() {
        return repository.getAllUsers();
    }


    boolean deleteUser(ObjectId userId) {
        final UserModel user = repository.getUser(userId);
        if (user == null) {
            throw new CustomException("No user exists for given user ID");
        }

        return repository.deleteUser(userId);
    }

    public UserModel getUser(ObjectId userId) {
        return repository.getUser(userId);
    }

    public UserModel createUser(UserRequestForm userForm) {
        final UserModel newUser = new UserModel();
        newUser.setAddress(userForm.getAddress());
        newUser.setAge(userForm.getAge());
        newUser.setName(userForm.getName());
        return repository.createUser(newUser);
    }

    public UserModel updateUser(ObjectId userId, UserRequestForm userForm) {
        final UserModel user = repository.getUser(userId);
        if (user == null) {
            throw new CustomException("No user exists for given user ID");
        }

        user.setName(userForm.getName());
        user.setAge(userForm.getAge());
        user.setAddress(userForm.getAddress());

        repository.updateUser(user);

        return user;
    }

}
