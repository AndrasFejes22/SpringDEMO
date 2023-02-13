package services;

import model.Post;
import model.User;
import model.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import services.publishers.PublisherService;

import java.util.ArrayList;
import java.util.List;

//@Component
@Service
public class PostService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostService.class);

    private final UserService userService;

    private final PublisherService publisherService;
    private final List<Post> posts = new ArrayList<>();

    //@Autowired
    //public PostService(UserService userService) {
        //this(userService, null);
    //}

    //@Autowired // constructor injection, van setter injection is és filed injection is
    //public PostService(UserService userService, PublisherService publisherService) { //így a beans-only-ban: <bean id="email" class="services.publishers.EmailPublisherService" primary="true"/>
    public PostService(UserService userService ,@Qualifier("push") PublisherService publisherService) {
        this.userService = userService;
        //this.publisherService = publisherService;
        this.publisherService = publisherService;
    }

    public void createPost(Post post) {
        User author = userService.getUserByName(post.getAuthor().getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Author not found: " + post.getAuthor()));
        if (author.getRole() != UserRole.EDITOR) {
            throw new IllegalArgumentException("User is not an editor");
        }
        post.setAuthor(author);
        posts.add(post);
        LOGGER.info("Created new post {}", post);

        //if(publisherService != null) {
            publisherService.notifyUsers(post);
        //}

    }

    public List<Post> getPostsByAuthor(User author) {
        return posts.stream()
                .filter(post -> post.getAuthor().equals(author))
                .toList();
    }
}
