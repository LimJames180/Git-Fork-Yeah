package login.app;

import login.entities.Recipe;
import login.entities.User;
import login.saverecipe.SaveRecipeInteractor;
import login.view.LoginPageView;
import login.view.SampleRecipeView;
import login.view.SignupPageView;
import login.interface_adapter.LoginController;
import login.interface_adapter.LoginPresenter;
import login.interface_adapter.LoginViewModel;
import login.interface_adapter.SignupController;
import login.interface_adapter.SignupPresenter;
import login.interface_adapter.SignupViewModel;
import login.use_case.LoginInteractor;
import login.use_case.SignupInteractor;
import login.data_access.MongoUserDataAccessImpl;
import login.saverecipe.RecipeController;

public class MainForkYeahApplication {
    public static void main(String[] args) {
        MongoUserDataAccessImpl userDataAccess = new MongoUserDataAccessImpl();

        LoginViewModel loginViewModel = new LoginViewModel();
        LoginPresenter loginPresenter = new LoginPresenter(loginViewModel);
        LoginInteractor loginInteractor = new LoginInteractor(userDataAccess, loginPresenter);
        LoginController loginController = new LoginController(loginInteractor);

        SignupViewModel signupViewModel = new SignupViewModel();
        SignupPresenter signupPresenter = new SignupPresenter(signupViewModel);
        SignupInteractor signupInteractor = new SignupInteractor(userDataAccess, signupPresenter);
        SignupController signupController = new SignupController(signupInteractor);

        LoginPageView loginPageView = new LoginPageView(loginController, loginViewModel);
        //SignupPageView signupPageView = new SignupPageView(signupController, signupViewModel);

        loginPageView.setVisible(true);
//
//        SaveRecipeInteractor signupInteractor = new SaveRecipeInteractor(userDataAccess, "hi");
//
//        RecipeCnontroller save = new RecipeController()
//        RecipeView saverecipe = new RecipeView(null,null);

        // Assuming user is the logged-in User object
//        Recipe recipeToSave = new Recipe("recipeID123");
//        user.addRecipe(recipeToSave);
//        userDataAccess.saveRecipeForUser(user.getUsername(), recipeToSave);
    }
}
