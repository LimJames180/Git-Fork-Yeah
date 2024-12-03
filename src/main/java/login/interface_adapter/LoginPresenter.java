package login.interface_adapter;

import login.use_case.LoginOutput;
import login.use_case.LoginOutputBoundary;

public class LoginPresenter implements LoginOutputBoundary {
    private final LoginViewModel viewModel;

    public LoginPresenter(LoginViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutput output) {
        System.out.println("Preparing Success View");
        viewModel.setMessage(output.getMessage());
        viewModel.setSavedRecipes(output.getSavedRecipes());
    }

    @Override
    public void prepareFailView(String message) {
        viewModel.setError(message);
    }
}