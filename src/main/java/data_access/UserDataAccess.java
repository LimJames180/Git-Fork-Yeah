package data_access;

import entity.User;

public interface UserDataAccess {
    User findUser(String username);
}