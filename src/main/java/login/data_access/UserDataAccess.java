package login.data_access;

import login.entities.User;

public interface UserDataAccess {
    User findUser(String username);
    void saveUser(User user);
}
