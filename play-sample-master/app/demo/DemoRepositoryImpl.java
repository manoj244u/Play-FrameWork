package demo;


import global.common.BaseModel;
import global.common.BaseRepository;
import global.configuration.db.mongodb.MongoDBConnection;
import user.UserModel;
import user.UserRepository;


import javax.inject.Inject;
import javax.inject.Singleton;

import static demo.DemoModel.Fields.email;

@Singleton
public class DemoRepositoryImpl extends BaseRepository<DemoModel> implements DemoRepository {
    private DemoModel demo;
    private DemoModel demo1;

    @Inject
    public DemoRepositoryImpl(MongoDBConnection databaseConnection) {
        super(DemoModel.class, databaseConnection);
    }

    /*@Override
    public DemoModel login(String email, String password) {
        String msg="Login successfully";
        DemoModel demo = query()
                .field(DemoModel.Fields.email.name())
                .equal(email)
                .field(DemoModel.Fields.password.name())
                .equal(password)
                .get();
        if(demo ==null){
            return null;
        }
        else
        {
          return demo;
        }
    }*/



    @Override
    public DemoModel createUser(DemoModel newUser) {
        DemoModel demo = query()
                .field(DemoModel.Fields.email.name())
                .equal(newUser.getEmail())
                .get();
        if (demo == null) {
            create(newUser);
            return newUser;
        }
        return null;

    }

    @Override
    public DemoModel loginUser(DemoModel loginuser) {
        DemoModel demo = query()
                .field(DemoModel.Fields.email.name())
                .equal(loginuser.getEmail())
                .field(DemoModel.Fields.password.name())
                .equal(loginuser.getPassword())
                .get();
        if (demo != null) {
            return loginuser;
        }
        return null;

    }
}
