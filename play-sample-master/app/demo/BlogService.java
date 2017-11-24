package demo;

import global.exceptions.CustomException;
import org.bson.types.ObjectId;
import user.UserModel;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class BlogService {
    private final BlogRepository repository;

    @Inject
    public BlogService(final BlogRepository repository) {
        this.repository = repository;

    }

    public BlogModel viewBlog(String userIdStr) {
        return repository.viewBlog(userIdStr);
    }
    public List<BlogModel> viewBlogs(String userIdStr) {
        return repository.viewBlogs(userIdStr);
    }

    public BlogModel createBlog(blogRequestForm blogForm) {
        final BlogModel newBlog = new BlogModel();
        newBlog.setUserId(blogForm.userId);
        newBlog.setTitle(blogForm.getTitle());
        newBlog.setBlogDesc(blogForm.getBlogDesc());
        newBlog.setComments(blogForm.getComments());
        newBlog.setLike(0);
        return repository.createBlog(newBlog);
    }

    boolean deleteBlog(String userIdStr) {
        boolean status = false;
        List<BlogModel> blog = repository.viewBlogs(userIdStr);
        if (!blog.isEmpty()) {
            for (BlogModel b : blog) {
                repository.deleteBlog(b.getId());
            }
            return true;
        }
        return false;

    }

    public BlogModel updateBlog(String userIdStr, blogRequestForm blogForm) {
        final BlogModel user = repository.viewBlog(userIdStr);
        if (user == null) {
            throw new CustomException("No user exists for given user ID");
        }

        user.setBlogDesc(blogForm.getBlogDesc());
        user.setTitle(blogForm.getTitle());
        repository.updateBlog(user);
        return user;
    }

}
