package config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import services.publishers.EmailPublisherService;
import services.publishers.PushNotificationPublisherService;

@Configuration
public class PublisherConfig {

    @Bean("email")
    @Primary
    public EmailPublisherService emailPublisherService() {
        return new EmailPublisherService();
    }

    @Bean({"push", "pushService"})
    public PushNotificationPublisherService pushNotificationService() {
        return new PushNotificationPublisherService();
    }
}
