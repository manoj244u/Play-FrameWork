package blog;

import org.bson.types.ObjectId;

import java.util.List;

public interface BlogRepository {
    BlogModel createBlog(BlogModel newBlog);
    BlogModel postComments(BlogModel newCommetns);
    BlogModel viewBlog(String userIdStr);
    List<BlogModel> viewBlogs(String userIdStr);
    List<BlogModel> viewComments(String topic);
    List<BlogModel> view_post_by_maxLikes(String topic);
    boolean deleteBlog(final ObjectId userIdStr);
    void updateBlog(final BlogModel user);
    void updateComments(final BlogModel user);
    void updateLikes(final BlogModel user);
    BlogModel checkUser(String userIdStr);

}
