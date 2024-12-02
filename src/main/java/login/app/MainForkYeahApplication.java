package login.app;

import login.data_access.MongoUserDataAccessImpl;
import login.interface_adapter.LoginController;
import login.interface_adapter.LoginPresenter;
import login.interface_adapter.LoginViewModel;
import login.interface_adapter.SignupController;
import login.interface_adapter.SignupPresenter;
import login.interface_adapter.SignupViewModel;
import login.use_case.LoginInteractor;
import login.use_case.SignupInteractor;
import login.view.LoginPageView;

public class MainForkYeahApplication {
    /**
     * The starting point of the application. This method initializes necessary components
     * for user login and signup functionalities and displays the login page for initial stage of program.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        final MongoUserDataAccessImpl userDataAccess = new MongoUserDataAccessImpl();

        final SessionService currentSession = new SessionService(userDataAccess);

        final LoginViewModel loginViewModel = new LoginViewModel(userDataAccess);
        final LoginPresenter loginPresenter = new LoginPresenter(loginViewModel);
        final LoginInteractor loginInteractor = new LoginInteractor(userDataAccess, loginPresenter);
        final LoginController loginController = new LoginController(loginInteractor);

        final SignupViewModel signupViewModel = new SignupViewModel();
        final SignupPresenter signupPresenter = new SignupPresenter(signupViewModel);
        final SignupInteractor signupInteractor = new SignupInteractor(userDataAccess, signupPresenter);
        final SignupController signupController = new SignupController(signupInteractor);

        final LoginPageView loginPageView = new LoginPageView(
                loginController, loginViewModel, signupController, signupViewModel, currentSession);
        loginPageView.setVisible(true);
    }
}
