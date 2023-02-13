package container;

import model.Post;
import model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import services.PostService;
import services.UserService;

public class JavaBasedContainer {
    public static void main(String[] args) {
        User user = User.editor("testuser", "pw");
        Post post = Post.of("Spring is fun", user);
        //ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
        // ilyen is van ez még használja az xml-t:
        ApplicationContext context = new AnnotationConfigApplicationContext("services");

        UserService userService = context.getBean(UserService.class);
        userService.registerUser(user);

        PostService postService = context.getBean(PostService.class);
        postService.createPost(post);
    }
}
