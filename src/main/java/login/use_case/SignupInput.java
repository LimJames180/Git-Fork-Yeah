package login.use_case;

/**
 * Represents the input data for user signup.
 */
public class SignupInput {
    private String username;
    private String password;

    /**
     * Constructs a SignupInput with the username and password.
     *
     * @param username the username for the new account
     * @param password the password for the new account
     */
    public SignupInput(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Returns the username associated with this SignupInput.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the password associated with this SignupInput.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }
}
