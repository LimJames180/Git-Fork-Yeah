package use_case.note;

import entity.User;
import data_access.UserDataAccess;

public class LoginInteractor implements LoginInputBoundary {
    private final UserDataAccess userDataAccess;
    private final LoginOutputBoundary outputBoundary;

    public LoginInteractor(UserDataAccess userDataAccess, LoginOutputBoundary outputBoundary) {
        this.userDataAccess = userDataAccess;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(LoginInput input) {
        String username = input.getUsername();
        String password = input.getPassword();

        // Check if username or password is empty
        if (username.isEmpty() || password.isEmpty()) {
            outputBoundary.prepareFailView("Error: Please fill in both username and password.");
            return;
        }

        // Check if the user exists
        User user = userDataAccess.findUser(username);
        if (user == null) {
            outputBoundary.prepareFailView("Error: Username does not exist.");
            return;
        }

        // Check if the password matches
        if (!user.getPassword().equals(password)) {
            outputBoundary.prepareFailView("Error: Invalid username or password.");
            return;
        }

        // If all checks pass, prepare success view
        outputBoundary.prepareSuccessView("Login successful!");
    }
}