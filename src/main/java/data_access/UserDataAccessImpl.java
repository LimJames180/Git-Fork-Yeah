package data_access;

import entity.User;

import java.util.HashMap;
import java.util.Map;

public class UserDataAccessImpl implements UserDataAccess {
    private final Map<String, User> users = new HashMap<>();

    public UserDataAccessImpl() {
        // Sample user for demonstration
        users.put("testUser", new User("testUser", "password123"));
    }

    @Override
    public User findUser(String username) {
        return users.get(username);
    }
}
