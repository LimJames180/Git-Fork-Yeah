package login.use_case;

import login.entities.User;
import login.data_access.UserDataAccess;

/**
 * The SignupInteractor class handles the signup process for new users.
 * It validates user input, checks for existing users, and saves new users.
 */
public class SignupInteractor implements SignupInputBoundary {
    private final UserDataAccess userDataAccess;
    private final SignupOutputBoundary outputBoundary;

    /**
     * Constructs a SignupInteractor with the given UserDataAccess and SignupOutputBoundary.
     *
     * @param userDataAccess the data access object for user-related operations
     * @param outputBoundary the output boundary for presenting results to the user
     */
    public SignupInteractor(UserDataAccess userDataAccess, SignupOutputBoundary outputBoundary) {
        this.userDataAccess = userDataAccess;
        this.outputBoundary = outputBoundary;
    }

    /**
     * Executes the signup process with the provided input.
     *
     * @param input the input containing the username and password for signup
     */
    @Override
    public void execute(SignupInput input) {
        final String username = input.getUsername();
        final String password = input.getPassword();

        if (username.isEmpty() || password.isEmpty()) {
            outputBoundary.prepareFailView("Error: Please fill in all fields.");
            return;
        }

        final String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,}$";
        if (!password.matches(passwordPattern)) {
            outputBoundary.prepareFailView("Error: Password must be at least 6 characters long, "
                    + "include an uppercase letter, a lowercase letter, and a number.");
            return;
        }

        final User existingUser = userDataAccess.findUser(username);
        if (existingUser != null) {
            outputBoundary.prepareFailView("Error: Username already exists.");
            return;
        }

        final User newUser = new User(username, password);
        userDataAccess.saveUser(newUser);
        outputBoundary.prepareSuccessView("Registration successful! You can now log in.");
    }
}
