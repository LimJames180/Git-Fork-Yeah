package instructions.data_access;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class Nutrients {
    public static String get(String key, int id) {
        OkHttpClient client = new OkHttpClient();
        String url = "https://api.spoonacular.com/recipes/" + id + "/nutritionWidget.json?apiKey=" + key;

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                assert response.body() != null;
                String jsonData = response.body().string();

                Gson gson = new Gson();
                JsonObject nutritionObject = gson.fromJson(jsonData, JsonObject.class);
                JsonArray nutrientsArray = nutritionObject.getAsJsonArray("nutrients");

                StringBuilder resultBuilder = new StringBuilder();
                int size = nutrientsArray.size();

                for (int i = 0; i < size; i++) {
                    JsonObject nutrientObject = nutrientsArray.get(i).getAsJsonObject();
                    String nutrientName = nutrientObject.get("name").getAsString();
                    double nutrientAmount = nutrientObject.get("amount").getAsDouble();
                    String nutrientUnit = nutrientObject.get("unit").getAsString();

                    resultBuilder.append(nutrientName).append(": ")
                            .append(nutrientAmount).append(" ")
                            .append(nutrientUnit);

                    if (i < size - 1) {
                        resultBuilder.append("\n");
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
