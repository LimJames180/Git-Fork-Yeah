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

public class FilterButton implements ActionListener {
    private static final String API_KEY = "62fb1e66d4be4351b17b5f5043ede6db";
    private static final String BASE_URL = "https://api.spoonacular.com/recipes/findByIngredients";
    private static final String BASE_URL2 = "https://api.spoonacular.com/recipes/";

    private final FilterSwing app;
    private final MultipleToggleButtonsExample toggleButtonsExample;
    private final List<String> ingredients = List.of("tomato", "pepper");
    private List<Response> recipes1;

    public FilterButton(FilterSwing app, MultipleToggleButtonsExample toggleButtonsExample) {
        this.app = app;
        this.toggleButtonsExample = toggleButtonsExample;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String ingredientsString = String.join(",+", ingredients);

        OkHttpClient client = new OkHttpClient();
        String url = BASE_URL + "?apiKey=" + API_KEY + "&ingredients=" + ingredientsString + "&number=5";

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                assert response.body() != null;
                String jsonData = response.body().string();
                Gson gson = new Gson();
                JsonArray recipes = gson.fromJson(jsonData, JsonArray.class);

                StringBuilder resultBuilder = new StringBuilder();
                recipes.forEach(recipe -> {
                    String title = recipe.getAsJsonObject().get("title").getAsString();
                    int id = recipe.getAsJsonObject().get("id").getAsInt();
                    Request request2 = new Request.Builder().url(BASE_URL2 + id + "/information" + "?apiKey=" + API_KEY + "&includeNutrition=false").build();
                    try (Response response1 = client.newCall(request2).execute()) {
                        String jsonData1 = response1.body().string();
                        JsonObject recipeInfo = gson.fromJson(jsonData1, JsonObject.class);
                        Map<String, Boolean> restrictions = toggleButtonsExample.getVariables();
                        if (restrictions.get("Dairy-free") == recipeInfo.get("dairyFree").getAsBoolean() || !restrictions.get("Dairy-free")) {
                            if (restrictions.get("Gluten-free") == recipeInfo.get("glutenFree").getAsBoolean() || !restrictions.get("Gluten-free")) {
                                if (restrictions.get("Vegan") == recipeInfo.get("vegan").getAsBoolean() || !restrictions.get("Vegan")) {
                                    resultBuilder.append("Recipe: ").append(title).append("\n\n");
                                    recipes1.add(response1);
                                }
                            }
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        app.displayResults("Error: " + ex.getMessage());
                    }
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
    public List<Response> getRecipes1() {
        return recipes1;
    }
}