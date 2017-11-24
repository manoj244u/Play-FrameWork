package demo;

import org.bson.types.ObjectId;

import java.util.List;

public interface DemoRepository {
    /*DemoModel getUserByAge(final int age);

    DemoModel getUserByName(final String name);

    List<DemoModel> getAllUsers();

    boolean deleteUser(final ObjectId userId);

    void updateUser(final DemoModel user);

    DemoModel getUser(int age, String name);*/

   /* DemoModel getUser(ObjectId userIdstr);*/
    DemoModel loginUser(DemoModel loginuser);
    DemoModel createUser(DemoModel newUser);
    DemoModel getUserByEmail(String email);
}
