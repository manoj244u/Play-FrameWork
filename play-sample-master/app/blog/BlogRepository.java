package blog;

import org.bson.types.ObjectId;

import java.util.List;

public interface BlogRepository {
    BlogModel createBlog(BlogModel newBlog);
    BlogModel postComments(BlogModel newCommetns);
    BlogModel viewBlog(String userIdStr);
    List<BlogModel> viewBlogs(String userIdStr);
    List<String> viewComments(String topic);
    boolean deleteBlog(final ObjectId userIdStr);
    void updateBlog(final BlogModel user);

}
