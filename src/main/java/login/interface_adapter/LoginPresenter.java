package login.interface_adapter;

import login.use_case.LoginOutputBoundary;

/**
 * The LoginPresenter class implements the LoginOutputBoundary interface.
 * It is responsible for preparing the view for successful and failed login attempts
 * by updating the LoginViewModel with appropriate messages.
 */
public class LoginPresenter implements LoginOutputBoundary {
    private final LoginViewModel viewModel;

    /**
     * Constructs a LoginPresenter with the specified LoginViewModel.
     *
     * @param viewModel the view model to be updated with login messages
     */
    public LoginPresenter(LoginViewModel viewModel) {
        this.viewModel = viewModel;
    }

    /**
     * Prepares the view model for a successful login by setting the success message.
     *
     * @param message the success message to be displayed
     */
    @Override
    public void prepareSuccessView(String message) {
        viewModel.setMessage(message);
    }

    /**
     * Prepares the view model for a failed login by setting the error message.
     *
     * @param message the error message to be displayed
     */
    @Override
    public void prepareFailView(String message) {
        viewModel.setError(message);
    }
}
