package demo;

import org.bson.types.ObjectId;
import demo.DemoModel;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class DemoService {
    private final DemoRepository repository;


    @Inject
    public DemoService(final DemoRepository repository) {
        this.repository = repository;
    }


   /* public DemoModel getUser(ObjectId userId) {
        return repository.getUser(userId);
    }*/

  /*  public DemoModel login(String email,String password)
    {
        return repository.login( email, password);
    }*/
    public DemoModel loginUser(loginRequestForm loginuserForm)
    {
       final DemoModel loginuser=new DemoModel();
               loginuser.setEmail(loginuserForm.getEmail());
               loginuser.setPassword(loginuserForm.getPassword());
               return repository.loginUser(loginuser);
    }

    public DemoModel createUser(DemoRequestForm userForm) {
        final DemoModel newUser = new DemoModel();
        newUser.setUserId(userForm.getUserId());
        newUser.setPassword(userForm.getPassword());
        newUser.setAddress(userForm.getAddress());
        newUser.setAge(userForm.getAge());
        newUser.setName(userForm.getName());
        newUser.setEmail(userForm.getEmail());
        return repository.createUser(newUser);
    }

}
