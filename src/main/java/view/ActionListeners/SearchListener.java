package view.ActionListeners;

import view.SearchRecipeView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchListener extends Frame implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        new SearchRecipeView();
        this.dispose();
    }
}
