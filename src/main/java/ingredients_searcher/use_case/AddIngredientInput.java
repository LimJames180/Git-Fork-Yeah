package ingredients_searcher.use_case;

import entity.Ingredient;

public class AddIngredientInput {
    private Ingredient ingredient;

    public AddIngredientInput(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public String getIngredient() { return ingredient.getName(); }

    public String getImage() { return ingredient.getImage(); }
}
