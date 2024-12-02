package misc_interface_adapter;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.ApiKey;
import entity.Ingredient;
import entity.Nutrients;
import entity.Recipe;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RecipeDataAccess {

    private static List<Recipe> recipeList(JsonArray recipes) {
        List<Recipe> recipeList = new ArrayList<>();
        for (JsonElement recipe : recipes) {
            String id = recipe.getAsJsonObject().get("id").getAsString();
            String title = recipe.getAsJsonObject().get("title").getAsString();
            String image = recipe.getAsJsonObject().get("image").getAsString();
            Recipe rec = new Recipe(id, title, image);
            recipeList.add(rec);
        }
        return recipeList;
    }

    public static List<Ingredient> getIngredients(String id) throws IOException {
        String apiKey = ApiKey.getApiKeys();
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.spoonacular.com/recipes/" + id + "/ingredientWidget.json?apiKey=" + apiKey)
                .get()
                .build();

        Response response = client.newCall(request).execute();
        String jsonData = response.body().string();
        JsonElement root = new JsonParser().parse(jsonData);
        JsonArray ingredients = root.getAsJsonObject().get("ingredients").getAsJsonArray();
        List<Ingredient> ingredientList = new ArrayList<>();
        for (JsonElement ingredient : ingredients) {
            String name = ingredient.getAsJsonObject().get("name").getAsString();
            String image = ingredient.getAsJsonObject().get("image").getAsString();
            JsonObject amountObject = ingredient.getAsJsonObject().getAsJsonObject("amount");
            int amount = 0;
            if (amountObject != null) {
                JsonObject metricObject = amountObject.getAsJsonObject("metric");
                if (metricObject != null) {
                    amount = metricObject.get("value").getAsInt();
                    // Use the amount variable as needed
                }
            }
            Ingredient ing = new Ingredient(name, image, amount);
            ingredientList.add(ing);
        }
        return ingredientList;
    }

    public static List<Nutrients> getNutrients(String id) throws IOException {
        String apiKey = ApiKey.getApiKeys();
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.spoonacular.com/recipes/" + id + "/nutritionWidget.json?apiKey=" + apiKey)
                .get()
                .build();

        Response response = client.newCall(request).execute();
        String jsonData = response.body().string();
        JsonElement root = new JsonParser().parse(jsonData);
        JsonArray nutrients = root.getAsJsonObject().get("nutrients").getAsJsonArray();
        List<Nutrients> nutrientList = new ArrayList<>();
        for (JsonElement nutrient : nutrients) {
            String title = nutrient.getAsJsonObject().get("name").getAsString();
            int amount = nutrient.getAsJsonObject().get("amount").getAsInt();
            String unit = nutrient.getAsJsonObject().get("unit").getAsString();
            int percentOfDailyNeeds = nutrient.getAsJsonObject().get("percentOfDailyNeeds").getAsInt();
            Nutrients nut = new Nutrients(title, amount, unit, percentOfDailyNeeds);
            nutrientList.add(nut);
        }
        return nutrientList;
    }

    public static List<Recipe> complexSearch(List<String> params) throws IOException {
        String apiKey = ApiKey.getApiKeys();
        OkHttpClient client = new OkHttpClient();

        StringBuilder parameter = new StringBuilder("https://api.spoonacular.com/recipes/complexSearch?apiKey=" + apiKey);
        for (String param : params) {
            parameter.append("&").append(param);
        }

        Request request = new Request.Builder()
                .url(parameter.toString())
                .get()
                .addHeader("User-Agent", "insomnia/10.1.1")
                .build();

        Response response = client.newCall(request).execute();
        String jsonData = response.body().string();
        JsonElement root = new JsonParser().parse(jsonData);
        List<Recipe> recipeList = recipeList(root.getAsJsonObject().get("results").getAsJsonArray());
        return recipeList;
    }

    public static List<Recipe> randomRecipe() throws IOException {
        String apiKey = ApiKey.getApiKeys();
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.spoonacular.com/recipes/random?apiKey=" + apiKey + "&number=10")
                .get()
                .build();

        Response response = client.newCall(request).execute();
        String jsonData = response.body().string();
        JsonElement root = new JsonParser().parse(jsonData);
        List<Recipe> recipeList = recipeList(root.getAsJsonObject().get("recipes").getAsJsonArray());
        return recipeList;
    }

    public String getName(Recipe recipe) {
        String apiKey = ApiKey.getApiKeys();
        OkHttpClient client = new OkHttpClient();

        StringBuilder parameter = new StringBuilder("https://api.spoonacular.com/recipes/" + recipe.getId() + "/information?apiKey=" + apiKey);
        Request request = new Request.Builder()
                .url(parameter.toString())
                .get()
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful() && response.body() != null) {
                JSONObject jsonResponse = new JSONObject(response.body().string());
                return jsonResponse.getString("title");
            } else {
                // Handle unsuccessful response or null body
                return "Title not found";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error fetching title";
        } catch (Exception e) {
            e.printStackTrace();
            return "Unexpected error";
        }
    }

    public static String getRecipeInstructions(String id) throws IOException {
        String apiKey = ApiKey.getApiKeys();
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.spoonacular.com/recipes/" + id + "/analyzedInstructions?apiKey=" + apiKey)
                .get()
                .build();

        Response response = client.newCall(request).execute();
        String jsonData = response.body().string();
        JsonElement root = new JsonParser().parse(jsonData);
        JsonArray instructions = root.getAsJsonArray();
        StringBuilder instruction = new StringBuilder();
        for (JsonElement step : instructions) {
            JsonArray steps = step.getAsJsonObject().get("steps").getAsJsonArray();
            for (JsonElement s : steps) {
                instruction.append(s.getAsJsonObject().get("step").getAsString()).append("\n");
            }
        }
        return instruction.toString();
    }
}

