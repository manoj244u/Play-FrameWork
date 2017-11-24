package demo;

import global.common.BaseModel;
import lombok.Getter;
import lombok.Setter;
import org.mongodb.morphia.annotations.Entity;

    @Entity(value="userBlog",noClassnameStored = true)
    @Getter
    @Setter

    public class BlogModel extends BaseModel{
        private String userId,title,blogDesc,comments;
        private int like;
        public enum Fields{userId,title,blogDesc,comments}
}
