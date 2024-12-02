package login.interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * ViewModel for handling user signup operations and property change notifications.
 */
public class SignupViewModel {
    private final PropertyChangeSupport support;
    private String message;
    private String error;

    /**
     * Constructs a new SignupViewModel instance.
     * Initializes the PropertyChangeSupport object.
     */
    public SignupViewModel() {
        support = new PropertyChangeSupport(this);
    }

    /**
     * Adds a PropertyChangeListener to the listener list.
     *
     * @param listener the PropertyChangeListener to be added
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Gets the current message.
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
     * Gets the current error message.
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
}
