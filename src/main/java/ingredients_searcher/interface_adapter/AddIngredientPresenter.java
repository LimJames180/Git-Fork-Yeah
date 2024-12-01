package ingredients_searcher.interface_adapter;

import ingredients_searcher.use_case.AddIngredientOutputBoundary;

public class AddIngredientPresenter implements AddIngredientOutputBoundary {
    private final AddIngredientViewModel viewModel;

    public AddIngredientPresenter(AddIngredientViewModel viewModel) { this.viewModel = viewModel; }

    @Override
    public void addfailed(String message) { viewModel.setMessage(message); }

}
