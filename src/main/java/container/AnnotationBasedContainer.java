package container;

import model.Post;
import model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import services.PostService;
import services.UserService;

public class AnnotationBasedContainer {
    public static void main(String[] args) {
        User user = User.editor("testuser", "pw");
        Post post = Post.of("Spring is fun", user);
        ApplicationContext context = new ClassPathXmlApplicationContext("beans-only.xml");

        UserService userService = context.getBean(UserService.class);
        userService.registerUser(user);

        PostService postService = context.getBean(PostService.class);
        postService.createPost(post);
    }
}
