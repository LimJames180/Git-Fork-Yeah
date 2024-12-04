package ingredients_searcher.use_case;

import entity.Ingredient;

/**
 * The AddIngredientOutput class stores the output of the ingredient search.
 */
public class AddIngredientOutput {
    Ingredient ingredient;

    public AddIngredientOutput(Ingredient ingredient) { this.ingredient = ingredient; }

    public String getIngredientName() { return ingredient.getName(); }
}
