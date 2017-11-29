package blog;

import global.common.BaseRepository;
import global.configuration.db.mongodb.MongoDBConnection;
import global.exceptions.CustomException;
import org.bson.types.ObjectId;
import play.mvc.Http;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.*;

@Singleton
public class BlogRepositoryImpl extends BaseRepository<BlogModel> implements BlogRepository {
    private BlogModel blog;

    @Inject
    public BlogRepositoryImpl(MongoDBConnection databaseConnection) {
        super(BlogModel.class, databaseConnection);
    }

    @Override
    public BlogModel createBlog(BlogModel newBlog) {
        create(newBlog);
        return newBlog;
    }

    @Override
    public BlogModel postComments(BlogModel blog) {
        BlogModel check = query()
                .field(BlogModel.Fields.topic.name())
                .equal(blog.getTopic())
                .get();

        if (check != null) {
            create(blog);
            return blog;
        }
         throw new CustomException("Only one Comment per user is accepted ");

    }

    @Override
    public BlogModel viewBlog(String userIdStr) {

        return query()
                .field(BlogModel.Fields.userId.name())
                .equal(userIdStr)
                .get();

        /*if (blog.size()!=null) {
            return null;
        } else {
            return blog;
        }*/
    }

    @Override
    public List<BlogModel> viewBlogs(String userIdStr) {

        return query()
                .field(BlogModel.Fields.userId.name())
                .equal(userIdStr)
                .asList();

        /*if (blog.size()!=null) {
            return null;
        } else {
            return blog;
        }*/
    }

    @Override
    public List<BlogModel> viewComments(String topic) {

        return query()
                .field(BlogModel.Fields.topic.name())
                .equal(topic)
                .asList();

    }

    @Override
    public List<BlogModel> viewCommentsByserIds(String s1) {
        List<String> postedIdArray = Arrays.asList(s1.split(","));
        return  query().field(BlogModel.Fields.postedId.name())
                    .in(postedIdArray)
                    .asList();


    }


    @Override
    public List<BlogModel> view_post_by_maxLikes() {
        return query()
                .order("like")
                .asList();
    }


    @Override
    public boolean deleteBlog(ObjectId userIdStr) {
        return delete(userIdStr);
    }

    @Override
    public void updateComments(BlogModel user) {
        update(user);
    }

    @Override
    public void updateLikes(BlogModel user) {
        update(user);
    }

    @Override
    public void updateBlog(BlogModel user) {
        update(user);
    }

    @Override
    public BlogModel checkUser(String userIdStr, String topic) {

        return query()
                .field(BlogModel.Fields.postedId.name())
                .equal(userIdStr)
                .field(BlogModel.Fields.topic.name())
                .equal(topic)
                .get();

    }

    @Override
    public List<BlogModel> checkBlog(String topic) {
        return query()
                .field(BlogModel.Fields.topic.name())
                .equal(topic)
                .asList();

    }
}
