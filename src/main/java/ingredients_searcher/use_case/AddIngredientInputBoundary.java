package ingredients_searcher.use_case;

import entity.Ingredient;

public interface AddIngredientInputBoundary {
    Ingredient execute(AddIngredientInput input);
}
