package login.use_case;

/**
 * Interface representing the output boundary for the signup use case.
 */
public interface SignupOutputBoundary {

    /**
     * Prepares the view for a successful signup.
     *
     * @param message A message to be displayed in the success view.
     */
    void prepareSuccessView(String message);

    /**
     * Prepares the view for a failed signup.
     *
     * @param message A message to be displayed in the failure view.
     */
    void prepareFailView(String message);
}
