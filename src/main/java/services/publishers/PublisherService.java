package services.publishers;

import model.Post;

public interface PublisherService {
    PublisherService NO_OP = post -> {};

    void notifyUsers(Post post);
}
