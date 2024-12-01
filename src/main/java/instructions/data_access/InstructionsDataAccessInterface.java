package instructions.data_access;

public interface InstructionsDataAccessInterface {

    String getInstructions(int id);

    String getIngredients(int id);

    String getImage(int id);

    String getNutrients(int id);
}
