import com.google.gson.Gson;
import com.google.gson.JsonArray;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class Recipe {

    public static void get_by_ingidient(String ingredient){
        final String API_KEY = ApiKey.getApiKeys();
        final String BASE_URL = "https://api.spoonacular.com/recipes/findByIngredients";


        OkHttpClient client = new OkHttpClient();
        String url = BASE_URL + "?apiKey=" + API_KEY + "&ingredients=" + ingredient + "&number=5";

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                assert response.body() != null;
                if (response.body().equals("[]")) {
                    System.out.printf("NONE AV");
                }
                String jsonData = response.body().string();
                Gson gson = new Gson();
                JsonArray recipes = gson.fromJson(jsonData, JsonArray.class);

                StringBuilder resultBuilder = new StringBuilder();
                recipes.forEach(recipe -> {
                    String title = recipe.getAsJsonObject().get("title").getAsString();
                    resultBuilder.append("Recipe: ").append(title).append("\n\n");
                });
                System.out.printf(resultBuilder.toString());

            } else {
                System.out.printf("Request failed: " + response.code() + " " + response.message());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.printf("Error: " + ex.getMessage());
        }
    }
}
