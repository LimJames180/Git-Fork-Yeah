package ingredients_searcher.interface_adapter;

import entity.Ingredient;
import ingredients_searcher.use_case.AddIngredientInput;
import ingredients_searcher.use_case.AddIngredientInputBoundary;

import java.util.ArrayList;
import java.util.List;

/**
 * The AddIngredientController class searches for the ingredient using the input.
 */
public class AddIngredientController {
    private final AddIngredientInputBoundary interactor;

    public AddIngredientController(AddIngredientInputBoundary interactor) { this.interactor = interactor; }

    public Ingredient ingredientSearch(String ingredient) {
        return interactor.execute(new AddIngredientInput(ingredient));
    }


}
