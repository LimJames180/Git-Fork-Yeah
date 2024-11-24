package view;

import javax.swing.*;
import java.awt.*;

public class ViewClass extends JFrame {

    ViewClass(String title, int width, int height) {
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setLayout(new BorderLayout());
    }
    // add input field, add button, listeners can be in the og class...
}
