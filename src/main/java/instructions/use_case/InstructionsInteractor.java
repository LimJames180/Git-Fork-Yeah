package instructions.use_case;

import entity.Ingredient;
import instructions.data_access.InstructionsDataAccessInterface;

import java.io.IOException;
import java.util.List;

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
        InstructionsOutputData instructionsOutputData = new InstructionsOutputData(instructionsOutput, ingredients, image);
        instructionsOutputBoundary.setInstructionsViewModel(instructionsOutputData);


        }
    }

