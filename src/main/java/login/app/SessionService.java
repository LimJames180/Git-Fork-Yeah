package login.app;

import login.data_access.MongoUserDataAccessImpl;

public class SessionService {
    private String username;
    private MongoUserDataAccessImpl data;

    public SessionService(MongoUserDataAccessImpl data) {
        this.data = data;
    }

    public SessionService() {
        
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }



    // You can add more session-related data and methods as needed
}