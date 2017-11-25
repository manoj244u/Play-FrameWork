package blog;

import lombok.Getter;
import lombok.Setter;
import play.data.validation.Constraints;

import java.util.Date;

@Getter
@Setter
public class CommentsRequestForm {
    @Constraints.Required
    public String postedId;

    @Constraints.Required
    public String topic;

    @Constraints.Required
    public String comments;

    public int like;
    public Date posted;

}
