package demo;

import lombok.Getter;
import lombok.Setter;
import play.data.validation.Constraints;


@Getter
@Setter
public class DemoRequestForm {
    public String userId;
    @Constraints.Required
    public String password;
    @Constraints.Required
    public String Name;
    @Constraints.Required
    public String address;
    @Constraints.Required
    @Constraints.Min(1)
    @Constraints.Max(99)
    public int age;
    @Constraints.Email
    public String email;
}


