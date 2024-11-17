package use_case.note;

public interface LoginOutputBoundary {
    void prepareSuccessView(String message);
    void prepareFailView(String message);
}
