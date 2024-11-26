package login.saverecipe;

import login.entities.Recipe;

public class SaveRecipeInput {
    private final Recipe recipe;

    public SaveRecipeInput(Recipe recipe) {
        this.recipe = recipe;
    }

    public Recipe getRecipe() {
        return recipe;
    }
}