package use_case.note;

public class SignupInput {
    private String username;
    private String password;

    public SignupInput(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters (and optionally setters, depending on your use case)
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

