package login.use_case;

public class SignupInput {
    private String username;
    private String password;

    public SignupInput(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
