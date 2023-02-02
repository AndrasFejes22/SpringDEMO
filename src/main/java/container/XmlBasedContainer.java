package container;

import model.Post;
import model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import services.PostService;
import services.UserService;

import java.util.List;

public class XmlBasedContainer {

    public static void main(String[] args) {
        User user = User.editor("testuser", "pw");
        ApplicationContext context = new ClassPathXmlApplicationContext("services.xml");
        UserService userService = context.getBean(UserService.class);
        userService.registerUser(user);

        PostService postService = context.getBean(PostService.class);
        List<Post> posts = postService.getPostsByAuthor(user);
        System.out.println("posts: "+posts);
    }
}
