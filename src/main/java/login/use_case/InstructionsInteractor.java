package login.use_case;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import login.data_access.InstructionsDataAccessInterface;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import login.entities.ApiKey;
import login.use_case.InstructionsOutputData;

import java.io.IOException;

public class InstructionsInteractor implements InstructionsInputBoundary {
    private final InstructionsDataAccessInterface instructionsDataAccess;
    private final InstructionsOutputBoundary instructionsOutputBoundary;

    public InstructionsInteractor(InstructionsDataAccessInterface instructionsDataAccess, InstructionsOutputBoundary instructionsOutputBoundary){
        this.instructionsDataAccess = instructionsDataAccess;
        this.instructionsOutputBoundary = instructionsOutputBoundary;
    }

    @Override
    public void execute(InstructionsInputData instructionsInputData) {
        final int id = instructionsInputData.getId();

        String output = instructionsDataAccess.getInstructions(id);
        InstructionsOutputData instructionsOutputData = new InstructionsOutputData(output);
        instructionsOutputBoundary.setInstructionsViewModel(instructionsOutputData);
        }
    }

