package login.interface_adapter;

import login.use_case.LoginInputBoundary;
import login.use_case.LoginInput;

public class LoginController {
    private final LoginInputBoundary interactor;

    public LoginController(LoginInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void handleLogin(String username, String password) {
        LoginInput input = new LoginInput(username, password);
        interactor.execute(input);
    }
}
