package login.use_case;

import login.entities.User;
import login.data_access.UserDataAccess;

public class SignupInteractor implements SignupInputBoundary {
    private final UserDataAccess userDataAccess;
    private final SignupOutputBoundary outputBoundary;

    public SignupInteractor(UserDataAccess userDataAccess, SignupOutputBoundary outputBoundary) {
        this.userDataAccess = userDataAccess;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(SignupInput input) {
        String username = input.getUsername();
        String password = input.getPassword();

        if (username.isEmpty() || password.isEmpty()) {
            outputBoundary.prepareFailView("Error: Please fill in all fields.");
            return;
        }

        String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,}$";
        if (!password.matches(passwordPattern)) {
            outputBoundary.prepareFailView("Error: Password must be at least 6 characters long, " +
                    "include an uppercase letter, a lowercase letter, and a number.");
            return;
        }

        User existingUser = userDataAccess.findUser(username);
        if (existingUser != null) {
            outputBoundary.prepareFailView("Error: Username already exists.");
            return;
        }

        User newUser = new User(username, password);
        userDataAccess.saveUser(newUser);
        outputBoundary.prepareSuccessView("Registration successful! You can now log in.");
    }
}
