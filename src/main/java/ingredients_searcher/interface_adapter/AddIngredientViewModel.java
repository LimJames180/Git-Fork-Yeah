package ingredients_searcher.interface_adapter;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import entity.Ingredient;
import ingredients_searcher.data_access.IngredientDataAccess;

/**
 * The view model for the Ingredient Searcher stores the results from the use case.
 */
public class AddIngredientViewModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private final IngredientDataAccess ingDataAccess;
    private String message;
    private String ingredient;
    private List<String> ingredientsList;

    public AddIngredientViewModel(List<String> ingredients, IngredientDataAccess ingDataAccess) {
        this.ingredientsList = Objects.requireNonNullElseGet(ingredients, ArrayList::new);
        this.ingDataAccess = ingDataAccess;
    }

    /**
     * Message getter.
     * @return message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Message setter.
     * @param message The new message.
     */
    public void setMessage(String message) {
        final String oldMessage = this.message;
        this.message = message;
        support.firePropertyChange("message", oldMessage, message);
    }

    /**
     * Ingredients list getter.
     * @return ingredientsList.
     */
    public List<String> getIngredientsList() { return ingredientsList; }

    public void setOutput(String ingredientName) { this.ingredient = ingredientName; }

    public String getIngredient() { return ingredient; }
}
