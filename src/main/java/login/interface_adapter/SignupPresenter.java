package login.interface_adapter;

import login.use_case.SignupOutputBoundary;

/**
 * The SignupPresenter class is for preparing the view for the signup process.
 * It implements the SignupOutputBoundary interface to handle success and failure scenarios
 * by updating the SignupViewModel with appropriate messages.
 */
public class SignupPresenter implements SignupOutputBoundary {
    private final SignupViewModel viewModel;

    /**
     * Constructs a SignupPresenter with the specified SignupViewModel.
     *
     * @param viewModel the view model to be updated with Signup messages
     */
    public SignupPresenter(SignupViewModel viewModel) {
        this.viewModel = viewModel;
    }

    /**
     * Prepares the view for a successful signup by setting the success message.
     *
     * @param message the success message to be displayed in the view
     */
    @Override
    public void prepareSuccessView(String message) {
        viewModel.setMessage(message);
    }

    /**
     * Prepares the view for a failed signup by setting the error message.
     *
     * @param message the error message to be displayed in the view
     */
    @Override
    public void prepareFailView(String message) {
        viewModel.setError(message);
    }
}
