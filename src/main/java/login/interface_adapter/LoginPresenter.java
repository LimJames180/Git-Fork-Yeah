package login.interface_adapter;

import login.use_case.LoginOutputBoundary;

public class LoginPresenter implements LoginOutputBoundary {
    private final LoginViewModel viewModel;

    public LoginPresenter(LoginViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void prepareSuccessView(String message) {
        viewModel.setMessage(message);
    }

    @Override
    public void prepareFailView(String message) {
        viewModel.setError(message);
    }
}