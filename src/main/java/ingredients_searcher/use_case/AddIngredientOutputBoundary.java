package ingredients_searcher.use_case;

import entity.Ingredient;

/**
 * Interface for output boundary of ingredient searcher.
 */
public interface AddIngredientOutputBoundary {
    /**
     * Alerts if the search failed.
     * @param message The fail message.
     */
    void addfailed(String message);
    void setViewModel(AddIngredientOutput addIngredientOutput);
}
