package view.ActionListeners;

import view.SearchRecipeView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SearchListener extends Frame implements ActionListener {
    private List<String> ingredientsList;

    public SearchListener(List<String> ingredientList) {
        this.ingredientsList = ingredientList;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        new SearchRecipeView(); // SearchRecipeView() add an ingredientslist parameter, if possible?
        this.dispose();
    }
}
