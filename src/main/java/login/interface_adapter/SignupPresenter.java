package login.interface_adapter;

import login.use_case.SignupOutputBoundary;

public class SignupPresenter implements SignupOutputBoundary {
    private final SignupViewModel viewModel;

    public SignupPresenter(SignupViewModel viewModel) {
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
