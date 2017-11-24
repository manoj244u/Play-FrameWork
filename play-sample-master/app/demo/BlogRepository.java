package demo;

import org.bson.types.ObjectId;

import java.util.List;

public interface BlogRepository {
    BlogModel createBlog(BlogModel newBlog);
    BlogModel viewBlog(String userIdStr);
    List<BlogModel> viewBlogs(String userIdStr);
    boolean deleteBlog(final ObjectId userIdStr);
    void updateBlog(final BlogModel user);

}
