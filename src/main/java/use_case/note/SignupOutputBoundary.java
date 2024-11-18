package use_case.note;

public interface SignupOutputBoundary {
    void prepareSuccessView(String message);
    void prepareFailView(String message);
}
