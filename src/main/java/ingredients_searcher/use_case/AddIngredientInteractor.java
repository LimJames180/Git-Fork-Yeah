package ingredients_searcher.use_case;

import entity.Ingredient;
import ingredients_searcher.data_access.IngredientDataAccess;

public class AddIngredientInteractor implements AddIngredientInputBoundary{
    private final IngredientDataAccess dataAccess;
    private final AddIngredientOutputBoundary outputBoundary;

    public AddIngredientInteractor(AddIngredientOutputBoundary outputBoundary, IngredientDataAccess dataAccess) {
        this.outputBoundary = outputBoundary;
        this.dataAccess = dataAccess;
    }

    @Override
    public void execute(AddIngredientInput input) {
        // calling API
        Ingredient ingredient = dataAccess.fetchIngredientData(input.getIngredient());

        // send a message for failure
        if (ingredient == null) {
            outputBoundary.addfailed("Ingredient not found");
        }

    }
}
