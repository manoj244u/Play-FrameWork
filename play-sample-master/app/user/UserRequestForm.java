package user;

import lombok.Getter;
import lombok.Setter;
import play.data.validation.Constraints;

@Getter
@Setter
public class UserRequestForm {

    public String id;

    @Constraints.Required
    public String name;

    @Constraints.Required
    public String address;

    @Constraints.Required
    @Constraints.Min(1)
    @Constraints.Max(100)
    public  int age;
}
