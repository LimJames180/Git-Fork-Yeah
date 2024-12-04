package ingredients_searcher.data_access;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import entity.Ingredient;

/**
 * This class access the Spoonacular database.
 */
public class IngredientDataAccess implements IngredientDataAccessInterface {
    // Spoonacular API Details
    // Replace with your actual API key
    private static final String SPOONACULAR_API_KEY = "62fb1e66d4be4351b17b5f5043ede6db";
    private static final String SPOONACULAR_SEARCH_URL =
            "https://api.spoonacular.com/food/ingredients/search?query=%s&apiKey=%s";

    /**
     * The actual API call.
     */
    @Override
    public Ingredient fetchIngredientData(String ingredient) {
        try {
            // Construct the API URL
            final String urlString = String.format(SPOONACULAR_SEARCH_URL, ingredient, SPOONACULAR_API_KEY);
            final URL url = new URL(urlString);
            final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Read the response
            final BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            final StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            connection.disconnect();

            // Parse and display ingredient data
            final JSONObject jsonResponse = new JSONObject(content.toString());
            final JSONArray results = jsonResponse.getJSONArray("results");
            if (!results.isEmpty()) {
                final JSONObject firstResult = results.getJSONObject(0);
                final String name = firstResult.getString("name");
                final String imageUrl = "https://spoonacular.com/cdn/ingredients_100x100/"
                        + firstResult.getString("image");

                // Display ingredient information
                return new Ingredient(name, imageUrl, 0);
            }
            else {
                return null;
            }
        }
        catch (Exception error) {
            error.printStackTrace();
            return null;
        }
    }
}
