package ingredients_searcher.use_case;

public class AddIngredientInput {
    private String ingredient;

    public AddIngredientInput(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getIngredient() { return ingredient; }
}
