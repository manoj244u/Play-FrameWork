package demo;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class BlogService {
    private final BlogRepository repository;

    @Inject
    public BlogService(final BlogRepository repository) {
        this.repository = repository;

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

}
