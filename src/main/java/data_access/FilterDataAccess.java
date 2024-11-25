package data_access;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Filter;


public class FilterDataAccess implements FilterDataAccessInterface {
    private static final String API_KEY = "62fb1e66d4be4351b17b5f5043ede6db";
    private static final String BASE_URL = "https://api.spoonacular.com/recipes/findByIngredients";
    private static final String BASE_URL2 = "https://api.spoonacular.com/recipes/";
    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();

    public JsonArray fetchRecipes (List <String> ingredients) throws IOException {
        String ingredientsString = String.join(",+", ingredients);
        String url = BASE_URL + "?apiKey=" + API_KEY + "&ingredients=" + ingredientsString + "&number=5";


        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful() || response.body() == null) {
                throw new IOException("Failed to fetch recipes: " + response.message());
            }


            return gson.fromJson(response.body().string(), JsonArray.class);
        }
    }


    public JsonObject fetchRecipe2 ( int id) throws IOException {
        String url = BASE_URL2 + id + "/information" + "?apiKey=" + API_KEY + "&includeNutrition=false";
        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful() || response.body() == null) {
                throw new IOException("Failed to fetch recipe information: " + response.message());
            }


            return gson.fromJson(response.body().string(), JsonObject.class);
        }
    }
}