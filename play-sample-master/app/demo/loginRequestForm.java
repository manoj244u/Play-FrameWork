package demo;

import lombok.Getter;
import lombok.Setter;
import play.data.validation.Constraints;

@Getter
@Setter
public class loginRequestForm {
    @Constraints.Required
    public String password;
    @Constraints.Email
    public String email;

}
