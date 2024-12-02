package login.use_case;

/**
 * Interface representing the output boundary for the login use case.
 * This interface includes methods for preparing success and failure views
 * in response to login.
 */
public interface LoginOutputBoundary {

    /**
     * Prepares the view for a successful login attempt.
     *
     * @param message A message to be displayed in the success view.
     */
    void prepareSuccessView(String message);

    /**
     * Prepares the view for a failed login attempt.
     *
     * @param message A message to be displayed in the failure view.
     */
    void prepareFailView(String message);
}
