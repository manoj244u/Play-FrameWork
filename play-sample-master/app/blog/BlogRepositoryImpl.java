package blog;

import global.common.BaseRepository;
import global.configuration.db.mongodb.MongoDBConnection;
import org.bson.types.ObjectId;

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
    public BlogModel postComments(BlogModel newCommetns) {
        BlogModel blog = query()
                .field(BlogModel.Fields.topic.name())
                .equal(newCommetns.getTopic())
                .get();
        if (blog != null) {
        create(newCommetns);
        return newCommetns;
    }
        return null;

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
    public List<String> viewComments(String topic) {

        List<BlogModel> model= query()
                .field(BlogModel.Fields.topic.name())
                .equal(topic)
                .asList();
         List<String> comments=new ArrayList<String>();
        for(BlogModel b1 : model){
            comments.add(b1.getComments());

        }
        return comments;

        /*if (blog.size()!=null) {
            return null;
        } else {
            return blog;
        }*/
    }


    @Override
    public boolean deleteBlog(ObjectId userIdStr) {
        return delete(userIdStr);
    }

    @Override
    public void updateBlog(BlogModel user)
    {
        update(user);
    }
}
