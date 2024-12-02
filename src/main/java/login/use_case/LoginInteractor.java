package login.use_case;

import login.entities.User;
import login.data_access.UserDataAccess;

import javax.swing.*;

/**
 * The LoginInteractor class implements the LoginInputBoundary interface
 * and handles the login process for users.
 */
public class LoginInteractor implements LoginInputBoundary {
    private final UserDataAccess userDataAccess;
    private final LoginOutputBoundary outputBoundary;

    /**
     * Constructs a LoginInteractor with the specified UserDataAccess and LoginOutputBoundary.
     *
     * @param userDataAccess the data access object for user-related operations
     * @param outputBoundary  the output boundary for presenting login results
     */
    public LoginInteractor(UserDataAccess userDataAccess, LoginOutputBoundary outputBoundary) {
        this.userDataAccess = userDataAccess;
        this.outputBoundary = outputBoundary;
    }

    /**
     * Executes the login process using the provided input.
     *
     * @param input the login input containing username and password
     */
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
