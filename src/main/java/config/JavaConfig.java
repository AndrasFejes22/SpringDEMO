package config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import services.InMemoryUserService;
import services.PostService;
import services.UserService;
import services.publishers.EmailPublisherService;
import services.publishers.PublisherService;

import java.lang.annotation.Native;

//@ComponentScan("services") // ez az xml-es leképzés
@Configuration
public class JavaConfig {
    @Bean
    public UserService userService() {
        return new InMemoryUserService();
    }

    /*
    @Bean
    public PostService postService(UserService userService, @Nullable PublisherService publisherService) {
        return new PostService(userService, publisherService);
    }
    */

    @Bean
    public PostService postService(@Qualifier("push") PublisherService publisherService) {
        return new PostService(userService(), publisherService);
    }

    /*
    @Bean
    public PublisherService emailPublisherService() { // így megtalálja @Nullable nélkül is
        return new EmailPublisherService();
    }
    */

    //a @Nullable-hez kell egy null check a PostService-be

    /*
    @Bean
    public PostService postService(@Qualifier("push") PublisherService publisherService) {
        return new PostService(userService(), publisherService);
    }
    */
}
