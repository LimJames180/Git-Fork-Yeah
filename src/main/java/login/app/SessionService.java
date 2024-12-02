package login.app;

import login.data_access.MongoUserDataAccessImpl;

/**
 * The SessionService class manages user session information,
 * including the username associated with the session.
 */
public class SessionService {
    private String username;
    private MongoUserDataAccessImpl data;

    /**
     * Constructs a SessionService with the specified data access implementation.
     *
     * @param data the MongoUserDataAccessImpl used for data access
     */
    public SessionService(MongoUserDataAccessImpl data) {
        this.data = data;
    }

    /**
     * Constructor for SessionService (specifically used for Signup).
     */
    public SessionService() {
    }

    /**
     * Sets the username for the session.
     *
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the username associated with the session.
     *
     * @return the username of the session
     */
    public String getUsername() {
        return username;
    }
}
