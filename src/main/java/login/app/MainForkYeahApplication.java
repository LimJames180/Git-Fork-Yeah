package login.app;

import login.data_access.InstructionsDataAccess;
import login.data_access.InstructionsDataAccessInterface;
import login.interface_adapter.*;
import login.use_case.InstructionsInteractor;
import login.view.InstructionsView;
import login.view.LoginPageView;
import login.view.SignupPageView;
import login.use_case.LoginInteractor;
import login.use_case.SignupInteractor;
import login.data_access.MongoUserDataAccessImpl;

public class MainForkYeahApplication {
    public static void main(String[] args) {
        MongoUserDataAccessImpl userDataAccess = new MongoUserDataAccessImpl();
//        InstructionsDataAccess instructionsDataAccess = new InstructionsDataAccess();

        LoginViewModel loginViewModel = new LoginViewModel();
        LoginPresenter loginPresenter = new LoginPresenter(loginViewModel);
        LoginInteractor loginInteractor = new LoginInteractor(userDataAccess, loginPresenter);
        LoginController loginController = new LoginController(loginInteractor);

        SignupViewModel signupViewModel = new SignupViewModel();
        SignupPresenter signupPresenter = new SignupPresenter(signupViewModel);
        SignupInteractor signupInteractor = new SignupInteractor(userDataAccess, signupPresenter);
        SignupController signupController = new SignupController(signupInteractor);

//        LoginPageView loginPageView = new LoginPageView(loginController, loginViewModel);
        //SignupPageView signupPageView = new SignupPageView(signupController, signupViewModel);

//        InstructionsViewModel instructionsViewModel = new InstructionsViewModel();
//        InstructionsPresenter instructionsPresenter = new InstructionsPresenter(instructionsViewModel);
//        InstructionsInteractor instructionsInteractor = new InstructionsInteractor(instructionsDataAccess,instructionsPresenter);
//        InstructionsController instructionsController = new InstructionsController(instructionsInteractor);
        InstructionsView instructionsView = new InstructionsView(324694);
//
        instructionsView.setVisible(true);
    }
}
