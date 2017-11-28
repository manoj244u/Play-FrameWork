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

    public List<BlogModel> viewCommentsByserIds(String postedIds) {
        System.out.print(" size  " +repository.viewCommentsByserIds(postedIds).size());
        return repository.viewCommentsByserIds(postedIds);
    }

    public List<BlogModel> view_post_by_maxLikes() {
        return repository.view_post_by_maxLikes();
    }

    public BlogModel createBlog(blogRequestForm blogForm) {
        final BlogModel newBlog = new BlogModel();
        newBlog.setUserId(blogForm.userId);
        newBlog.setTopic(blogForm.getTopic());
        newBlog.setBlogDesc(blogForm.getBlogDesc());
        newBlog.setComments("");
        newBlog.setLike("0");
        return repository.createBlog(newBlog);
    }

    public BlogModel postComments(CommentsRequestForm commentsForm) {
        BlogModel check = repository.checkUser(commentsForm.getPostedId(),commentsForm.getTopic());
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
        BlogModel check = repository.checkUser(commentsForm.getPostedId(),commentsForm.getTopic());
        List<BlogModel> check1 = repository.checkBlog(commentsForm.getTopic());
        if (check != null ) {
            if(check.getLike().equals("0")) {
                check.setLike("1");
                updateBlog(check1, commentsForm.getTopic());
                repository.updateLikes(check);
                return check;
            }
            return null;
        } else {
            final BlogModel newComments = new BlogModel();
            newComments.setTopic(commentsForm.getTopic());
            newComments.setPostedId(commentsForm.getPostedId());
            newComments.setPosted(new Date());
            newComments.setLike("1");
            newComments.setComments("");
            updateBlog(check1,commentsForm.getTopic());
            return repository.postComments(newComments);
        }
    }
     public BlogModel updateBlog(List<BlogModel> check1, String topic)
      {
        for(BlogModel b: check1) {
        if (!b.getUserId().isEmpty() && b.getTopic().equalsIgnoreCase(topic)) {
            b.setLike(String.valueOf(Integer.valueOf(b.getLike()) + 1));
            repository.updateLikes(b);
            return b;
        }
    }return null;
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
