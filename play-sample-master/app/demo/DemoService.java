package demo;

import org.bson.types.ObjectId;
import demo.DemoModel;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

import static global.utils.Helper.compare;
import static global.utils.Helper.hash;




@Singleton
public class DemoService {
    private final DemoRepository repository;


    @Inject
    public DemoService(final DemoRepository repository) {
        this.repository = repository;
    }


  public Optional<DemoModel> loginUser(loginRequestForm mForm) {
      final DemoModel user = repository.getUserByEmail(mForm.getEmail());
      boolean flag = compare(mForm.getPassword(), user.getPassword());

      if (flag)
          return Optional.of(user);
      else
          return Optional.empty();
  }

       public DemoModel createUser(DemoRequestForm userForm) {
        final DemoModel newUser = new DemoModel();
        newUser.setUserId(userForm.getUserId());
        newUser.setPassword(hash(userForm.getPassword()));
        newUser.setAddress(userForm.getAddress());
        newUser.setAge(userForm.getAge());
        newUser.setName(userForm.getName());
        newUser.setEmail(userForm.getEmail());
        return repository.createUser(newUser);
    }

}
