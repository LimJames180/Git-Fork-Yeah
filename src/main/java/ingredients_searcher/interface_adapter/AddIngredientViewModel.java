package ingredients_searcher.interface_adapter;

import entity.Ingredient;
import entity.Recipe;
import ingredients_searcher.data_access.IngredientDataAccess;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class AddIngredientViewModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private final IngredientDataAccess ingDataAccess;
    private String message;

    public AddIngredientViewModel(IngredientDataAccess ingDataAccess) {
        this.ingDataAccess = ingDataAccess;
    }
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        String oldMessage = this.message;
        this.message = message;
        support.firePropertyChange("message", oldMessage, message);
    }

    public Ingredient getIngredients(String query) {
        return ingDataAccess.fetchIngredientData(query);
    }
    // TODO add more relevant methods here
}
