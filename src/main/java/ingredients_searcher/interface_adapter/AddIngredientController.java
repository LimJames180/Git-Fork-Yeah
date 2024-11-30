package ingredients_searcher.interface_adapter;

import entity.Ingredient;
import ingredients_searcher.use_case.AddIngredientInput;
import ingredients_searcher.use_case.AddIngredientInputBoundary;

import java.util.ArrayList;
import java.util.List;

public class AddIngredientController {
    List<String> ingredients;
    private final AddIngredientInputBoundary interactor;

    public AddIngredientController(AddIngredientInputBoundary interactor) { this.interactor = interactor; }

    public List<String> getIngredients() {
        if (ingredients == null){
            return new ArrayList<>();
        }
        return ingredients;
    }

    public void addIngredient(String query) {
        AddIngredientInput input = new AddIngredientInput(query);
        interactor.execute(input);
    }


}
