package view.ActionListeners;

import view.IngredientSearchView;
import view.SearchRecipeView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SearchListener extends Frame implements ActionListener {
    private List<String> ingredientsList;
    private IngredientSearchView isv;

    public SearchListener(List<String> ingredientList, IngredientSearchView isv) {
        this.ingredientsList = ingredientList;
        this.isv = isv;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        new SearchRecipeView(); // SearchRecipeView() add an ingredientslist parameter, if possible?
        this.isv.dispose();
    }
}
