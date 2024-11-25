package view.ActionListeners;

import view.IngredientSearchView;
import view.SearchRecipeView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExploreListener implements ActionListener {
    private final IngredientSearchView isv;

    public ExploreListener(IngredientSearchView isv) {
        this.isv = isv;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.isv.dispose();


        new SearchRecipeView();
    }
}
