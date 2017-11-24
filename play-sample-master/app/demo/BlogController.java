package demo;

import com.google.common.collect.ImmutableMap;
import global.common.BaseController;
import global.exceptions.CustomException;
import play.data.Form;
import play.mvc.BodyParser;
import play.mvc.Result;

import java.util.*;

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

    public Result viewBlog(String userIdStr) {
        try {

            BlogModel blog = blogservice.viewBlog(userIdStr);
            return blog != null ? success(ImmutableMap.of("Blog-post", blog)) : failure("Invalid User ID");
        } catch (CustomException e) {
            return failure(e.getMessage());
        }
    }
    public Result viewBlogs(String userIdStr) {
        try {

            List<BlogModel> blog = blogservice.viewBlogs(userIdStr);
            return blog != null ? success(ImmutableMap.of("Blog-post", blog)) : failure("Invalid User ID");
        } catch (CustomException e) {
            return failure(e.getMessage());
        }
    }

    public Result deleteBlog(String userIdStr) {
        try {

            final boolean status = blogservice.deleteBlog(userIdStr);
            return status ? success("successfully deleted user") : failure("failed to delete user");
        } catch (CustomException e) {
            return failure(e.getMessage());
        }
    }

    public Result updateBlog(String userIdStr) {
        try {
            final Form<blogRequestForm> blogModelForm = formFactory.form(blogRequestForm.class).bindFromRequest();
            if (blogModelForm.hasErrors()) {
                return failure(buildValidationErrorMessage(blogModelForm.allErrors()));
            }

            /*if (!ObjectId.isValid(userIdStr)) {
                return failure("Invalid User ID type");
            }*/

            final blogRequestForm blogForm = blogModelForm.get();
            final BlogModel user = this.blogservice.updateBlog(userIdStr, blogForm);
            return user != null ? success("successfully updated Blog-Post") : failure("failed to update Blog-Post");
        } catch (CustomException e) {
            return failure(e.getMessage());
        }
    }

}
