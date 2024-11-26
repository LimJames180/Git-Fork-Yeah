package login.saverecipe;

import login.entities.Recipe;
import login.saverecipe.SaveRecipeInputBoundary;

public class RecipeController {
    private final SaveRecipeInputBoundary interactor;

    public RecipeController(SaveRecipeInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void handleSaveRecipe(Recipe recipe) {
        interactor.execute(new SaveRecipeInput(recipe));
    }
}