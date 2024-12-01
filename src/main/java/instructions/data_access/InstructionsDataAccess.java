package instructions.data_access;

import entity.ApiKey;

public class InstructionsDataAccess implements InstructionsDataAccessInterface {
    final String API_KEY = ApiKey.getApiKeys();

    @Override
    public String getInstructions(int id) {
        return Instructions.get(API_KEY, id);
    }

    @Override
    public String getIngredients(int id) {
        return InstructionsIngredients.get(API_KEY, id);
    }

    @Override
    public String getImage(int id) {
        return InstructionsImage.get(API_KEY, id);
    }

    @Override
    public String getNutrients(int id) {
        return InstructionsNutrients.get(API_KEY, id);
    }
}
