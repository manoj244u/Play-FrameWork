package blog;

import global.common.BaseRepository;
import global.exceptions.CustomException;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Date;
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

    public List<BlogModel> viewComments(String topic) {
        return repository.viewComments(topic);
    }
    public List<BlogModel> view_post_by_maxLikes(String topic) {
        return repository.view_post_by_maxLikes(topic);
    }

    public BlogModel createBlog(blogRequestForm blogForm) {
        final BlogModel newBlog = new BlogModel();
        newBlog.setUserId(blogForm.userId);
        newBlog.setTopic(blogForm.getTopic());
        newBlog.setBlogDesc(blogForm.getBlogDesc());
        // newBlog.setCommnets(blogForm.getComments());
        newBlog.setLike("0");
        return repository.createBlog(newBlog);
    }

    public BlogModel postComments(CommentsRequestForm commentsForm) {
        BlogModel check = repository.checkUser(commentsForm.getPostedId());
       /* */
        /*newComments.setLike(((commentsForm.getLike()).toLowerCase().equalsIgnoreCase("yes")
                ? "1" : "0"));*/

        // newComments.setComments(commentsForm.getComments());
        if (check != null) {
            check.setComments(commentsForm.getComments());
            repository.updateComments(check);
            return check;
        } else {
            final BlogModel newComments = new BlogModel();

            newComments.setTopic(commentsForm.getTopic());
            newComments.setPostedId(commentsForm.getPostedId());
            newComments.setLike("");
            newComments.setPosted(new Date());
            newComments.setComments(commentsForm.getComments());
            repository.postComments(newComments);
            return newComments;
        }
    }

    public BlogModel postLikes(CommentsRequestForm commentsForm) {
        BlogModel check = repository.checkUser(commentsForm.getPostedId());
        if (check != null) {
            check.setLike("1");
            repository.updateLikes(check);
            return check;
        }

      else {
            final BlogModel newComments = new BlogModel();
            newComments.setTopic(commentsForm.getTopic());
            newComments.setPostedId(commentsForm.getPostedId());
            newComments.setPosted(new Date());
            newComments.setLike("1");
            newComments.setComments("");
            return repository.postComments(newComments);
        }
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
        user.setTopic(blogForm.getTopic());
        repository.updateBlog(user);
        return user;
    }

    public BlogModel updateLikes(String userIdStr, CommentsRequestForm blogForm) {
        final BlogModel user = repository.viewBlog(userIdStr);
        if (user == null) {
            throw new CustomException("No user exists for given user ID");
        }
        user.setLike("1");
        repository.updateBlog(user);
        return user;
    }

    public BlogModel updateComments(String userIdStr, CommentsRequestForm blogForm) {
        final BlogModel user = repository.viewBlog(userIdStr);
        if (user == null) {
            throw new CustomException("No user exists for given user ID");
        }
        user.setComments(blogForm.getComments());
        repository.updateBlog(user);
        return user;
    }

}
