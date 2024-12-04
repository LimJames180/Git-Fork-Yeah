package ingredients_searcher.data_access;

import entity.Ingredient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * This class access the Spoonacular database.
 */
public class IngredientDataAccess implements IngredientDataAccessInterface {
    // Spoonacular API Details
    private static final String SPOONACULAR_API_KEY = "62fb1e66d4be4351b17b5f5043ede6db"; // Replace with your actual API key
    private static final String SPOONACULAR_SEARCH_URL = "https://api.spoonacular.com/food/ingredients/search?query=%s&apiKey=%s";

    @Override
    /**
     * The actual API call.
     */
    public Ingredient fetchIngredientData(String ingredient) {
        try {
            // Construct the API URL
            String urlString = String.format(SPOONACULAR_SEARCH_URL, ingredient, SPOONACULAR_API_KEY);
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Read the response
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            connection.disconnect();

            // Parse and display ingredient data
            JSONObject jsonResponse = new JSONObject(content.toString());
            JSONArray results = jsonResponse.getJSONArray("results");
            if (!results.isEmpty()) {
                JSONObject firstResult = results.getJSONObject(0);
                String name = firstResult.getString("name");
                String imageUrl = "https://spoonacular.com/cdn/ingredients_100x100/" +
                        firstResult.getString("image");

                // Display ingredient information
                return new Ingredient(name, imageUrl, 0);
            }
            else { return null; }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}