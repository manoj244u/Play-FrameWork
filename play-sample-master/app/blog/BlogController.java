package blog;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.ImmutableMap;
import global.common.BaseController;
import global.exceptions.CustomException;
import play.data.Form;
import play.mvc.BodyParser;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;

public class BlogController extends BaseController {
    private final BlogService blogservice;

    @Inject
    public BlogController(BlogService blogservice) {
        this.blogservice = blogservice;

    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result createBlog() {
        if (isSessionValid()) {
            try {
                final Form<BlogRequestForm> blogModelFrom = formFactory.form(BlogRequestForm.class).bindFromRequest();
                if (blogModelFrom.hasErrors()) {
                    return failure(buildValidationErrorMessage(blogModelFrom.allErrors()));
                }

                final BlogRequestForm blogForm = blogModelFrom.get();
                final BlogModel blog = this.blogservice.createBlog(blogForm);
                return blog != null ? ok("Blog created successfully") : failure("Failed to create BlogPost");

            } catch (
                    CustomException e)

            {
                return failure(e.getMessage());
            }
        } else {
            return failure("Seeeion has expired");
        }
    }

    public Result viewBlog(String userIdStr) {
        if (isSessionValid()) {
            try {

                BlogModel blog = blogservice.viewBlog(userIdStr);
                return blog != null ? success(ImmutableMap.of("Blog-post", blog)) : failure("Invalid User ID");
            } catch (CustomException e) {
                return failure(e.getMessage());
            }
        } else
            return failure("Seeeion has expired");
    }

    public Result viewBlogs(String userIdStr) {
        if (isSessionValid()) {
            try {

                List<BlogModel> blog = blogservice.viewBlogs(userIdStr);
                return blog != null ? success(ImmutableMap.of("Blog-post", blog)) : failure("Invalid User ID");
            } catch (CustomException e) {
                return failure(e.getMessage());
            }
        } else
            return failure("Seeeion has expired");
    }

    public Result deleteBlog(String userIdStr) {
        if (isSessionValid()) {
            try {

                final boolean status = blogservice.deleteBlog(userIdStr);
                return status ? success("successfully deleted user") : failure("failed to delete user");
            } catch (CustomException e) {
                return failure(e.getMessage());
            }
        } else
            return failure("Seeeion has expired");
    }

    public Result updateBlog(String userIdStr) {
        if (isSessionValid()) {
            try {
                final Form<BlogRequestForm> blogModelForm = formFactory.form(BlogRequestForm.class).bindFromRequest();
                if (blogModelForm.hasErrors()) {
                    return failure(buildValidationErrorMessage(blogModelForm.allErrors()));
                }

            /*if (!ObjectId.isValid(userIdStr)) {
                return failure("Invalid User ID type");
            }*/

                final BlogRequestForm blogForm = blogModelForm.get();
                final BlogModel user = this.blogservice.updateBlog(userIdStr, blogForm);
                return user != null ? success("successfully updated Blog-Post") : failure("failed to update Blog-Post");
            } catch (CustomException e) {
                return failure(e.getMessage());
            }
        } else
            return failure("Seeeion has expired");
    }


    @BodyParser.Of(BodyParser.Json.class)
    public Result postComments() {
        if (isSessionValid()) {
            try {
                final Form<CommentsRequestForm> commetnsModelFrom = formFactory.form(CommentsRequestForm.class).bindFromRequest();
                if (commetnsModelFrom.hasErrors()) {
                    return failure(buildValidationErrorMessage(commetnsModelFrom.allErrors()));
                }

                final CommentsRequestForm commentsForm = commetnsModelFrom.get();
                final BlogModel blog = this.blogservice.postComments(commentsForm);
                return blog != null ? ok("Comments created successfully") : failure("Failed to create Comments");

            } catch (
                    CustomException e)

            {
                return failure(e.getMessage());
            }
        } else
            return failure("Seeeion has expired");

    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result postLikes() {
        if (isSessionValid()) {
            try {
                final Form<CommentsRequestForm> commetnsModelFrom = formFactory.form(CommentsRequestForm.class).bindFromRequest();
                if (commetnsModelFrom.hasErrors()) {
                    return failure(buildValidationErrorMessage(commetnsModelFrom.allErrors()));
                }

                final CommentsRequestForm commentsForm = commetnsModelFrom.get();
                final BlogModel blog = this.blogservice.postLikes(commentsForm);
                return blog != null ? ok("Comments created successfully") : failure("Failed to create Comments");

            } catch (
                    CustomException e)

            {
                return failure(e.getMessage());
            }
        } else
            return failure("Seeeion has expired");

    }

    public Result viewComments(String topic) {
        if (isSessionValid()) {

            try {
                List<BlogModel> comments = blogservice.viewComments(topic);
                ObjectMapper mapper1 = new ObjectMapper();
                JsonNode childNode1;
                ArrayNode obj = mapper1.createArrayNode();
                for (BlogModel b : comments) {
                    childNode1 = mapper1.createObjectNode();
                    if (b.getComments() != null) {
                        ((ObjectNode) childNode1).put("post", b.getTopic());
                        ((ObjectNode) childNode1).put("comments", b.getComments());
                        ((ObjectNode) childNode1).put("like", b.getLike());
                        ((ArrayNode) obj).add(childNode1);
                    }
                }
                return comments != null ? success(obj) : failure("Failed to Fetch Comments");
            } catch (CustomException e) {
                return failure(e.getMessage());
            }
        } else
            return failure("Seeeion has expired");
    }

    public Result viewCommentsByserIds(String postedIds) {
        if(isSessionValid()) {

            try {
                List<BlogModel> comments = blogservice.viewCommentsByserIds(postedIds);
                System.out.print("Testing   ");
                ObjectMapper mapper1 = new ObjectMapper();
                JsonNode childNode1;
                ArrayNode obj = mapper1.createArrayNode();
                for (BlogModel b : comments) {
                    childNode1 = mapper1.createObjectNode();
                    if (b.getComments() != null) {
                        ((ObjectNode) childNode1).put("post", b.getTopic());
                        ((ObjectNode) childNode1).put("comments", b.getComments());
                        ((ObjectNode) childNode1).put("postedId", b.getPostedId());
                        ((ArrayNode) obj).add(childNode1);
                    }
                }
                return comments != null ? success(obj) : failure("Failed to Fetch Comments");
            } catch (CustomException e) {
                return failure(e.getMessage());
            }
        }
        else
            return failure("Seeeion has expired");
    }

    public Result view_post_by_maxLikes() {
        if (isSessionValid()) {
            try {

                List<BlogModel> comments = blogservice.view_post_by_maxLikes();
                ObjectMapper mapper = new ObjectMapper();
                JsonNode childNode3;
                ArrayNode obj = mapper.createArrayNode();

                for (BlogModel b : comments) {
                    if (b.getBlogDesc() != null) {
                        childNode3 = mapper.createObjectNode();
                        ((ObjectNode) childNode3).put("topic", b.getTopic());
                        ((ObjectNode) childNode3).put("desc", b.getBlogDesc());
                        ((ObjectNode) childNode3).put("Likes", b.getLike());
                        ((ArrayNode) obj).add(childNode3);

                    }
                }
                return comments != null ? success(obj) : failure("Failed to Fetch Comments");
            } catch (CustomException e) {
                return failure(e.getMessage());
            }
        }
        else
            return failure("Seeeion has expired");
    }

}


