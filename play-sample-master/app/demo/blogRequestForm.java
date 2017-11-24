package demo;

import lombok.Getter;
import lombok.Setter;
import play.data.validation.Constraints;

@Getter
@Setter
public class blogRequestForm {
   public String userId;
    public String comments;
    public String title;
    public String blogDesc;
    public int like;
}
