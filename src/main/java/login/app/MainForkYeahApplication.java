package login.app;

import entity.Recipe;
import login.interface_adapter.*;
import login.use_case.SignupInteractor;
import login.view.LoginPageView;
import login.use_case.LoginInteractor;
import login.data_access.MongoUserDataAccessImpl;

public class MainForkYeahApplication {
    public static void main(String[] args) {
        MongoUserDataAccessImpl userDataAccess = new MongoUserDataAccessImpl();
        LoginViewModel loginViewModel = new LoginViewModel(userDataAccess);
        LoginPresenter loginPresenter = new LoginPresenter(loginViewModel);
        LoginInteractor loginInteractor = new LoginInteractor(userDataAccess, loginPresenter);
        LoginController loginController = new LoginController(loginInteractor);
        SessionService currentSession = new SessionService(userDataAccess);

        SignupViewModel signupViewModel = new SignupViewModel();
        SignupPresenter signupPresenter = new SignupPresenter(signupViewModel);
        SignupInteractor signupInteractor = new SignupInteractor(userDataAccess, signupPresenter);
        SignupController signupController = new SignupController(signupInteractor);


        LoginPageView loginPageView = new LoginPageView(loginController, loginViewModel, signupController, signupViewModel, currentSession);
        loginPageView.setVisible(true);
    }
}