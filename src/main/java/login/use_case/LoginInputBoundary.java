package login.use_case;

/**
 * Interface representing the input boundary for the login use case.
 */
public interface LoginInputBoundary {
    /**
     * Executes the login operation with the provided input.
     *
     * @param input the login input containing the necessary credentials
     */
    void execute(LoginInput input);
}

