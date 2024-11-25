import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SearchButtonListener implements ActionListener {
    private static final String API_KEY = "62fb1e66d4be4351b17b5f5043ede6db";
    private static final String BASE_URL = "https://api.spoonacular.com/recipes/findByIngredients";

    private final RecipeFinderSwing app;

    public SearchButtonListener(RecipeFinderSwing app) {
        this.app = app;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String ingredient = app.getIngredient();
        if (ingredient.isEmpty()) {
            app.displayError("Please enter an ingredient.");
            return;
        }

        OkHttpClient client = new OkHttpClient();
        String url = BASE_URL + "?apiKey=" + API_KEY + "&ingredients=" + ingredient + "&number=5";

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                assert response.body() != null;
                if (response.body().equals("[]")) {
                    app.displayResults("No Result Available");
                }
                String jsonData = response.body().string();
                Gson gson = new Gson();
                JsonArray recipes = gson.fromJson(jsonData, JsonArray.class);

                StringBuilder resultBuilder = new StringBuilder();
                recipes.forEach(recipe -> {
                    String title = recipe.getAsJsonObject().get("title").getAsString();
                    resultBuilder.append("entity.Recipe: ").append(title).append("\n\n");
                });
                app.displayResults(resultBuilder.toString());
            } else {
                app.displayResults("Request failed: " + response.code() + " " + response.message());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            app.displayResults("Error: " + ex.getMessage());
        }
    }
}