package instructions.data_access;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class Image {
    public static String get(String key, int id) {
        OkHttpClient client = new OkHttpClient();
        String url = "https://api.spoonacular.com/recipes/" + id + "/information?includeNutrition=false&apiKey=" + key;

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
