package instructions.data_access;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class Ingredients {
    public static String get(String key, int id) {
        OkHttpClient client = new OkHttpClient();
        String url = "https://api.spoonacular.com/recipes/" + id + "/analyzedInstructions?apiKey=" + key;

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
}
