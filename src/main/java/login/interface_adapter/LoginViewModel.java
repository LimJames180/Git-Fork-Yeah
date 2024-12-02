package login.interface_adapter;

import entity.Recipe;
import login.data_access.UserDataAccess;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

/**
 * ViewModel for handling user login operations and property change notifications.
 */
public class LoginViewModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private String message;
    private String error;
    private final UserDataAccess userDataAccess;

    /**
     * Constructs a LoginViewModel with the specified UserDataAccess.
     *
     * @param userDataAccess the data access object for user-related operations
     */
    public LoginViewModel(UserDataAccess userDataAccess) {
        this.userDataAccess = userDataAccess;
    }

    /**
     * Adds a property change listener to this ViewModel.
     *
     * @param listener the listener to be added
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Returns an array of property change listeners registered with this ViewModel.
     *
     * @return an array of registered property change listeners
     */
    public PropertyChangeListener[] getPropertyChangeListeners() {
        return support.getPropertyChangeListeners();
    }

    /**
     * Removes a property change listener from this ViewModel.
     *
     * @param listener the listener to be removed
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    /**
     * Returns the current message.
     *
     * @return the current message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets a new message and notifies listeners of the change.
     *
     * @param message the new message to set
     */
    public void setMessage(String message) {
        final String oldMessage = this.message;
        this.message = message;
        support.firePropertyChange("message", oldMessage, message);
    }

    /**
     * Returns the current error message.
     *
     * @return the current error message
     */
    public String getError() {
        return error;
    }

    /**
     * Sets a new error message and notifies listeners of the change.
     *
     * @param error the new error message to set
     */
    public void setError(String error) {
        final String oldError = this.error;
        this.error = error;
        support.firePropertyChange("error", oldError, error);
    }

    /**
     * Retrieves the saved recipes for a given user.
     *
     * @param username the username of the user whose recipes are to be retrieved
     * @return a list of saved recipes for the specified user
     */
    public List<Recipe> getSavedRecipes(String username) {
        return userDataAccess.getUserRecipes(username);
    }
}
