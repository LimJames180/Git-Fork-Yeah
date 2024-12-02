package login.use_case;

/**
 * Interface representing the input boundary for the signup use case.
 */
public interface SignupInputBoundary {

    /**
     * Executes the signup process with the provided input.
     *
     * @param input the signup input containing the necessary data for the signup
     */
    void execute(SignupInput input);
}
