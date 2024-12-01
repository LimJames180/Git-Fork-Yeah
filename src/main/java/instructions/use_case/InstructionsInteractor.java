package instructions.use_case;

import instructions.data_access.InstructionsDataAccessInterface;

public class InstructionsInteractor implements InstructionsInputBoundary {
    private final InstructionsDataAccessInterface instructionsDataAccess;
    private final InstructionsOutputBoundary instructionsOutputBoundary;

    public InstructionsInteractor(InstructionsDataAccessInterface instructionsDataAccess, InstructionsOutputBoundary instructionsOutputBoundary){
        this.instructionsDataAccess = instructionsDataAccess;
        this.instructionsOutputBoundary = instructionsOutputBoundary;
    }

    @Override
    public void execute(InstructionsInputData instructionsInputData){
        final int id = instructionsInputData.getId();

        String instructionsOutput = instructionsDataAccess.getInstructions(id);
        String ingredients = instructionsDataAccess.getIngredients(id);
        String image = instructionsDataAccess.getImage(id);
        String nutrients = instructionsDataAccess.getNutrients(id);
        InstructionsOutputData instructionsOutputData = new InstructionsOutputData(instructionsOutput, ingredients, image, nutrients);
        instructionsOutputBoundary.setInstructionsViewModel(instructionsOutputData);
    }
}

