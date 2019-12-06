package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ImageService imageService;

    //This controller method is called when the request pattern is of type 'image/{imageId}/{imageTitle}/comments' and also the incoming request is of POST type
    //The method receives a comment to be stored in the database
    //user information is collected from logged in user
    //createddate,text,user and image detail are set to newComment object
    // and now the comment will be sent to the business logic to be persisted in the database
    //After storing the image, this method directs to  controller with mapping images/{imageId}/{title} i.e showImage method of this controller

    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    public String createComment(@PathVariable("imageId") Integer imageId, @PathVariable("imageTitle") String imageTitle, @RequestParam("comment")  String comment, HttpSession session) throws IOException {
        User user = (User) session.getAttribute("loggeduser");
        Image image = imageService.getImage(imageId);

        //creating a new Comment
        Comment newComment = new Comment(comment, user, image);
        commentService.createComment(newComment);

        return "redirect:/images/" + imageId +"/"+imageTitle;
    }
}