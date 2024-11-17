package interface_adapter.login;

import use_case.note.SignupInputBoundary;
import use_case.note.SignupInput;
import entity.User;
import data_access.MongoUserDataAccessImpl;

public class SignupController {
    private final SignupInputBoundary interactor;
    private final MongoUserDataAccessImpl userDataAccess;

    public SignupController(SignupInputBoundary interactor) {
        this.interactor = interactor;
        this.userDataAccess = new MongoUserDataAccessImpl();
    }

    public void handleSignup(String username, String password) {
        SignupInput input = new SignupInput(username, password);
        interactor.execute(input);
        userDataAccess.saveUser(new User(username, password)); // Save user to MongoDB
    }
}