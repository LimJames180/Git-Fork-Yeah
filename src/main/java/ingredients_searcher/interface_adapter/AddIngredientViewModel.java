package ingredients_searcher.interface_adapter;

import java.beans.PropertyChangeSupport;

/**
 * The view model for the Ingredient Searcher stores the results from the use case.
 */
public class AddIngredientViewModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private String message;
    private String ingredient;

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

    public void setOutput(String ingredientName) { this.ingredient = ingredientName; }

    public String getIngredient() { return ingredient; }
}
