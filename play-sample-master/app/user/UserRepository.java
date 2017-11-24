package user;

import org.bson.types.ObjectId;

import java.util.List;

public interface UserRepository {

    UserModel getUserByAge(final int age);

    UserModel getUserByName(final String name);

    List<UserModel> getAllUsers();

    boolean deleteUser(final ObjectId userId);

    void updateUser(final UserModel user);

    UserModel getUser(int age, String name);

    UserModel getUser(ObjectId userId);

    UserModel createUser(UserModel newUser);
}
