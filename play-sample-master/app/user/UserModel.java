package user;

import global.common.BaseModel;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity(value = "users",noClassnameStored = true)
@Getter
@Setter
public class UserModel extends BaseModel {
    private String name, address;
    private int age;


    public enum Fields {address, age, name}
}
