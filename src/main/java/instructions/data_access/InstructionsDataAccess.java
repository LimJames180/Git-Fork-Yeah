package instructions.data_access;

import com.google.gson.*;
import entity.ApiKey;
import entity.Ingredient;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InstructionsDataAccess implements InstructionsDataAccessInterface {
    final String API_KEY = ApiKey.getApiKeys();

    @Override
    public String getInstructions(int id){
        OkHttpClient client = new OkHttpClient();
        String url = "https://api.spoonacular.com/recipes/" + id + "/analyzedInstructions?apiKey=" + API_KEY;

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

                // Extract and append instructions to form a large string
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

                return resultBuilder.toString();

            } else {
                throw new IOException("Request failed: " + response.code() + " " + response.message());
            }
        } catch (IOException ex) {
            return "An I/O error occurred: " + ex.getMessage();
        }
    }

    public String getIngredients(int id) {
        OkHttpClient client = new OkHttpClient();
        String url = "https://api.spoonacular.com/recipes/" + id + "/analyzedInstructions?apiKey=" + API_KEY;

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
                resultBuilder.append("Ingredients needed: ");
                for (JsonElement recipeElement : recipes) {
                    JsonArray stepsArray = recipeElement.getAsJsonObject().getAsJsonArray("steps");
                    for (JsonElement stepElement : stepsArray) {
                        JsonObject stepObject = stepElement.getAsJsonObject();

                        // Extract and append ingredients to form a large string
                        JsonArray ingredientsArray = stepObject.getAsJsonArray("ingredients");
                        if (ingredientsArray != null) {
                            for (JsonElement ingredientElement : ingredientsArray) {
                                JsonObject ingredientObject = ingredientElement.getAsJsonObject();
                                String ingredientName = ingredientObject.get("name").getAsString();
                                resultBuilder.append(ingredientName).append(", ");
                            }
                        }
                    }
                }

                return resultBuilder.toString();

            } else {
                throw new IOException("Request failed: " + response.code() + " " + response.message());
            }
        } catch (IOException ex) {
            return "An I/O error occurred: " + ex.getMessage();
        }
    }

    @Override
    public String getImage(int id) {
        OkHttpClient client = new OkHttpClient();
        String url = "https://api.spoonacular.com/recipes/" + id + "/information?includeNutrition=false&apiKey=" + API_KEY;

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                assert response.body() != null;
                String jsonData = response.body().string();

                Gson gson = new Gson();
                JsonObject recipeObject = gson.fromJson(jsonData, JsonObject.class);
                String imageUrl = recipeObject.get("image").getAsString();

                return imageUrl;
            } else {
                throw new IOException("Request failed: " + response.code() + " " + response.message());
            }
        } catch (IOException ex) {
            return "An I/O error occurred: " + ex.getMessage();
        }
    }
}
