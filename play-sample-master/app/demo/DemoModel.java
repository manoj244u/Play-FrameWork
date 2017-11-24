package demo;

import global.common.BaseModel;
import lombok.Getter;
import lombok.Setter;
import org.mongodb.morphia.annotations.Entity;

@Entity(value="demos",noClassnameStored = true)
@Getter
@Setter
public class DemoModel extends BaseModel {
    private String name,userId,address,email,password;
    private int age;

public enum Fields{name,address,userId,email,password,age}
}
