package login.interface_adapter;

import login.use_case.SignupInput;
import login.use_case.SignupInputBoundary;

/**
 * The SignupController class handles user signup requests.
 * It interacts with the SignupInputBoundary to process signup inputs.
 */
public class SignupController {
    private final SignupInputBoundary interactor;

    /**
     * Constructs a SignupController with the specified interactor.
     *
     * @param interactor the SignupInputBoundary that will handle the signup logic
     */
    public SignupController(SignupInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Handles the signup process by creating a SignupInput and executing it.
     *
     * @param username the username of the user signing up
     * @param password the password of the user signing up
     */
    public void handleSignup(String username, String password) {
        final SignupInput input = new SignupInput(username, password);
        interactor.execute(input);
    }
}
