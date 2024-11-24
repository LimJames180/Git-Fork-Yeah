package login.use_case;

public interface LoginOutputBoundary {
    void prepareSuccessView(String message);
    void prepareFailView(String message);
}
