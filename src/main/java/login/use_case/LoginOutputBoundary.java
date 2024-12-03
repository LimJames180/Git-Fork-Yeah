package login.use_case;

public interface LoginOutputBoundary {
    void prepareSuccessView(LoginOutput output);
    void prepareFailView(String message);
}
