package login.use_case;

public interface SignupOutputBoundary {
    void prepareSuccessView(String message);
    void prepareFailView(String message);
}
