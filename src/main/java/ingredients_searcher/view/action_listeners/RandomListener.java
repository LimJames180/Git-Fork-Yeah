package ingredients_searcher.view.action_listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.SwingUtilities;

import RandomFYP.view.RandomView;
import ingredients_searcher.view.IngredientSearchView;
import login.app.SessionService;

/**
 * Listener class for the random recipe button.
 */
public class RandomListener implements ActionListener {
    private final SessionService currentSession;
    private final IngredientSearchView isv;

    public RandomListener(SessionService currentSession, IngredientSearchView isv) {
        this.currentSession = currentSession;
        this.isv = isv;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SwingUtilities.invokeLater(() -> {
            try {
                new RandomView(currentSession);
            }
            catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        isv.dispose();
    }

}
