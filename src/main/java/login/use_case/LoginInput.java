package login.use_case;

/**
 * Represents the input for a login operation, containing the username and password.
 */
public class LoginInput {
    private String username;
    private String password;

    /**
     * Constructs a LoginInput with the specified username and password.
     *
     * @param username the username for the login
     * @param password the password for the login
     */
    public LoginInput(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Returns the username associated with this LoginInput.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the password associated with this LoginInput.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }
}
