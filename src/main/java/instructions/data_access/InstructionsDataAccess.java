package instructions.data_access;

import com.google.gson.*;
import entity.ApiKey;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

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

                StringBuilder resultBuilder = new StringBuilder();
                int n = 1;
                boolean firstStep = true;

                for (JsonElement recipeElement : recipes) {
                    JsonArray stepsArray = recipeElement.getAsJsonObject().getAsJsonArray("steps");
                    for (JsonElement stepElement : stepsArray) {
                        JsonObject stepObject = stepElement.getAsJsonObject();
                        String stepInstruction = stepObject.get("step").getAsString();

                        if (!firstStep) {
                            resultBuilder.append("\n");
                        }

                        resultBuilder.append(n).append(". ").append(stepInstruction);
                        n++;
                        firstStep = false;
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
                resultBuilder.append("Ingredients needed: ");
                boolean firstIngredient = true;

                for (JsonElement recipeElement : recipes) {
                    JsonArray stepsArray = recipeElement.getAsJsonObject().getAsJsonArray("steps");
                    for (JsonElement stepElement : stepsArray) {
                        JsonObject stepObject = stepElement.getAsJsonObject();

                        JsonArray ingredientsArray = stepObject.getAsJsonArray("ingredients");
                        if (ingredientsArray != null) {
                            for (JsonElement ingredientElement : ingredientsArray) {
                                JsonObject ingredientObject = ingredientElement.getAsJsonObject();
                                String ingredientName = ingredientObject.get("name").getAsString();

                                if (!firstIngredient) {
                                    resultBuilder.append(", ");
                                }
                                resultBuilder.append(ingredientName);
                                firstIngredient = false;
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
