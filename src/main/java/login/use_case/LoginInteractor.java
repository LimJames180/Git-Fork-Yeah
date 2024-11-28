package login.use_case;

import login.entities.User;
import login.data_access.UserDataAccess;

import javax.swing.*;

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

        User user = userDataAccess.findUser(username);


        if (username.isEmpty() || password.isEmpty()) {
            outputBoundary.prepareFailView("Error: Please input both username and password.");
            return;
        }

        if (user == null) {
            outputBoundary.prepareFailView("Error: Username does not exist.");
            return;
        }

        if (!user.getPassword().equals(password)) {
            outputBoundary.prepareFailView("Error: Incorrect password.");
            return;
        }

        outputBoundary.prepareSuccessView("Login successful! Welcome " + username + "!");
    }
}
