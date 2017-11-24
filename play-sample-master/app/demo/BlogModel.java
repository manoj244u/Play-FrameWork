package demo;

import global.common.BaseModel;
import lombok.Getter;
import lombok.Setter;
import org.mongodb.morphia.annotations.Entity;
import scala.collection.immutable.List;

@Entity(value="userBlog",noClassnameStored = true)
    @Getter
    @Setter

    public class BlogModel extends BaseModel{
        private String userId,title,blogDesc,comments;
       // private List<String> commnets;
        private int like;
        public enum Fields{userId,title,blogDesc,comments}
}
