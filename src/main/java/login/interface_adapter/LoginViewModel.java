package login.interface_adapter;

import entity.Recipe;
import login.data_access.UserDataAccess;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class LoginViewModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private String message;
    private String error;
    private List<Recipe> savedRecipes; // Add this field
    private final UserDataAccess userDataAccess; // Assuming you have a UserDataAccess interface

    public LoginViewModel(UserDataAccess userDataAccess) {
        this.userDataAccess = userDataAccess;
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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        String oldError = this.error;
        this.error = error;
        support.firePropertyChange("error", oldError, error);
    }

    public List<Recipe> getSavedRecipes(String username) {
        return userDataAccess.getUserRecipes(username);
    }

    public void setSavedRecipes(List<Recipe> savedRecipes) { // Add this method
        this.savedRecipes = savedRecipes;
    }
}