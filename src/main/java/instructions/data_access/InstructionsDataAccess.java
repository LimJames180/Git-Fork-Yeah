package instructions.data_access;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import entity.ApiKey;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class InstructionsDataAccess implements InstructionsDataAccessInterface {
    @Override
    public String getInstructions(int id) {
        final String API_KEY = ApiKey.getApiKeys();
        final String BASE_URL = "https://api.spoonacular.com/recipes/";

        OkHttpClient client = new OkHttpClient();
        String url = BASE_URL + id + "/analyzedInstructions?apiKey=" + API_KEY;

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                assert response.body() != null;
                if (response.body().equals("[]")) {
                    System.out.println("No Result Available");
                }
                String jsonData = response.body().string();
                Gson gson = new Gson();
                JsonArray recipes = gson.fromJson(jsonData, JsonArray.class);

                StringBuilder resultBuilder = new StringBuilder();
                int n = 1;
                for (JsonElement recipeElement : recipes) {
                    JsonArray stepsArray = recipeElement.getAsJsonObject().getAsJsonArray("steps");
                    for (JsonElement stepElement : stepsArray) {
                        JsonObject stepObject = stepElement.getAsJsonObject();
                        String stepInstruction = stepObject.get("step").getAsString();
                        resultBuilder.append(n).append(". ").append(stepInstruction).append("\n");
                        n++;
                    }
                }
                System.out.println(resultBuilder);
                return resultBuilder.toString();

            } else {
                return ("Request failed: " + response.code() + " " + response.message());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            return ("Error: " + ex.getMessage());
        }
    }
}
