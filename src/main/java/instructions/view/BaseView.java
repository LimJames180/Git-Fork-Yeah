package instructions.view;

import login.app.SessionService;

public interface BaseView {
    void setVisible(boolean visible);
    SessionService getCurrentSession();
}