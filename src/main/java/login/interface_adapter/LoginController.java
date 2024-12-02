package login.interface_adapter;

import login.use_case.LoginInput;
import login.use_case.LoginInputBoundary;

/**
 * The LoginController class is responsible for handling login requests.
 * It interacts with the LoginInputBoundary to process login inputs.
 */
public class LoginController {
    private final LoginInputBoundary interactor;

    /**
     * Constructs a LoginController with the specified LoginInputBoundary.
     *
     * @param interactor the LoginInputBoundary that will handle login execution
     */
    public LoginController(LoginInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Handles the login process by creating a LoginInput and executing it
     * through the interactor.
     *
     * @param username the username of the user attempting to log in
     * @param password the password of the user attempting to log in
     */
    public void handleLogin(String username, String password) {
        final LoginInput input = new LoginInput(username, password);
        interactor.execute(input);
    }
}
