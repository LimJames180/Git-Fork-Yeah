package ingredients_searcher.view.action_listeners;

import RandomFYP.view.RandomView;
import ingredients_searcher.view.IngredientSearchView;
import login.app.SessionService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class RandomListener implements ActionListener {
    private final SessionService currentSession;
    private final IngredientSearchView isv;

    public RandomListener(SessionService currentSession, IngredientSearchView isv) {
        this.currentSession = currentSession;
        this.isv = isv;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SwingUtilities.invokeLater(() -> { // look into removing the try/catch
            RandomView next = null;
            try {
                next = new RandomView(currentSession);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        isv.dispose();
    }

}
