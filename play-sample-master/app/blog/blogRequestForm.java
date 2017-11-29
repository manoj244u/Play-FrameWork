package blog;

import lombok.Getter;
import lombok.Setter;
import play.data.validation.Constraints;
import scala.util.parsing.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BlogRequestForm {
    @Constraints.Required
    public String userId;
    public String postedId;
    public List<String> comments = new ArrayList<String>();
    @Constraints.Required
    public String topic;
    public String blogDesc;
    public long like;
}
