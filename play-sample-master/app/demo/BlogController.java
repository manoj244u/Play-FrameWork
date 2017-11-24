package demo;

import global.common.BaseController;
import global.exceptions.CustomException;
import play.data.Form;
import play.mvc.BodyParser;
import play.mvc.Result;

import javax.inject.Inject;

public class BlogController extends BaseController {
    private final BlogService blogservice;
    @Inject
    public BlogController(BlogService blogservice) {
        this.blogservice = blogservice;

    }
    @BodyParser.Of(BodyParser.Json.class)
    public Result createBlog() {
        try {
            final Form<blogRequestForm> blogModelFrom = formFactory.form(blogRequestForm.class).bindFromRequest();
            if (blogModelFrom.hasErrors()) {
                return failure(buildValidationErrorMessage(blogModelFrom.allErrors()));
            }

            final blogRequestForm blogForm = blogModelFrom.get();
            final BlogModel blog = this.blogservice.createBlog(blogForm);
            return blog != null ? ok("Blog created successfully") : failure("Failed to create BlogPost");

        } catch (
                CustomException e)

        {
            return failure(e.getMessage());
        }

    }

}
