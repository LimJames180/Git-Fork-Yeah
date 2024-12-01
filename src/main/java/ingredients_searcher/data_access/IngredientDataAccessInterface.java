package ingredients_searcher.data_access;

import entity.Ingredient;

public interface IngredientDataAccessInterface {
    Ingredient fetchIngredientData(String ingredient);
}
