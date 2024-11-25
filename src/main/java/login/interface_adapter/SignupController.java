package login.interface_adapter;


import login.use_case.SignupInputBoundary;
import login.use_case.SignupInput;

public class SignupController {
    private final SignupInputBoundary interactor;

    public SignupController(SignupInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void handleSignup(String username, String password) {
        SignupInput input = new SignupInput(username, password);
        interactor.execute(input);
    }
}
