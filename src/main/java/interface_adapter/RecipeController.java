package interface_adapter;

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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RecipeController {

    private static List<Recipe> Recipe_List(JsonArray recipes) {
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

    public static List<Ingredient> get_ingredients(String id) throws IOException {
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

    public static List<Nutrients> get_nutrients(String id) throws IOException {
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

    public static List<Recipe> complex_search(List<String> params) throws IOException {
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
        List<Recipe> recipeList = Recipe_List(root.getAsJsonObject().get("results").getAsJsonArray());
        return recipeList;
    }



    public static List<Recipe> Random_recipe() throws IOException {
        String apiKey = ApiKey.getApiKeys();
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.spoonacular.com/recipes/random?apiKey=" + apiKey + "&number=10")
                .get()
                .build();

        Response response = client.newCall(request).execute();
        String jsonData = response.body().string();
        JsonElement root = new JsonParser().parse(jsonData);
        List<Recipe> recipeList = Recipe_List(root.getAsJsonObject().get("recipes").getAsJsonArray());
        return recipeList;
    }

    public static String get_recipe_instructions(String id) throws IOException {
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

