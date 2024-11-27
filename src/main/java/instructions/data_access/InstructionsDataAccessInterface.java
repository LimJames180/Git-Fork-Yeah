package instructions.data_access;

import entity.Ingredient;

import java.io.IOException;
import java.util.List;

public interface InstructionsDataAccessInterface {
    //List of strings (instructions)
    String getInstructions(int id);

    String getIngredients(int id);

    String getImage(int id);
}
