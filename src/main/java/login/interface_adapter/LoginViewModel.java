package login.interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoginViewModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private String message;
    private String error;

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
}
