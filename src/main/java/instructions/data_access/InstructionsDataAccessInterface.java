package instructions.data_access;

public interface InstructionsDataAccessInterface {
    //List of strings (instructions)
    String getInstructions(int id);

    String getIngredients(int id);

    String getImage(int id);

    String getNutrients(int id);
}
