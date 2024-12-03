package ingredients_searcher.interface_adapter;

import entity.Ingredient;
import ingredients_searcher.data_access.IngredientDataAccess;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddIngredientViewModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private final IngredientDataAccess ingDataAccess;
    private String message;
    private List<String> ingredientsList;

    public AddIngredientViewModel(List<String> ingredients, IngredientDataAccess ingDataAccess) {
        this.ingredientsList = Objects.requireNonNullElseGet(ingredients, ArrayList::new);
        this.ingDataAccess = ingDataAccess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        String oldMessage = this.message;
        this.message = message;
        support.firePropertyChange("message", oldMessage, message);
    }

    public Ingredient getIngredient(String query) {
        return ingDataAccess.fetchIngredientData(query);
    }

    public List<String> getIngredientsList() {
        return ingredientsList;
    }
}
