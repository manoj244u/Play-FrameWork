package blog;

import global.common.BaseModel;
import lombok.Getter;
import lombok.Setter;
import org.mongodb.morphia.annotations.Entity;
import scala.collection.immutable.List;
import scala.util.parsing.json.JSONArray;
import scala.util.parsing.json.JSONArray$;

import java.util.Date;

@Entity(value="user_blogs",noClassnameStored = true)
    @Getter
    @Setter

    public class BlogModel extends BaseModel{
        private String userId,blogDesc,comments,topic,postedId;
       // private List<String> commnets;
        long like;
        private Date posted;
        public enum Fields{userId,topic,blogDesc,comments,posted,like,postedId}
}
