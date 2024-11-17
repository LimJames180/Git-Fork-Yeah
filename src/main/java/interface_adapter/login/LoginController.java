package interface_adapter.login;

import use_case.note.LoginInputBoundary;
import use_case.note.LoginInput;
import data_access.MongoUserDataAccessImpl;

public class LoginController {
    private final LoginInputBoundary interactor;
    private final MongoUserDataAccessImpl userDataAccess;

    public LoginController(LoginInputBoundary interactor) {
        this.interactor = interactor;
        this.userDataAccess = new MongoUserDataAccessImpl();
    }

    public void handleLogin(String username, String password) {
        if (userDataAccess.validateUser(username, password)) {
            LoginInput input = new LoginInput(username, password);
            interactor.execute(input);
        } else {
            // Handle login failure (e.g., show error message)
        }
    }
}