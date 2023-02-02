package services;

import model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> getUserByName(String username);

    User loginUser(String username, String password);

    void registerUser(User user);

}
