package ingredients_searcher.use_case;

import entity.Ingredient;

/**
 * The AddIngredientInput class takes the inputs for searching the ingredient.
 */
public class AddIngredientInput {
    private String ingredient;

    public AddIngredientInput(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getIngredient() { return ingredient; }

}
