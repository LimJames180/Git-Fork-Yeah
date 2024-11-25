package data_access;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


import java.io.IOException;
import java.util.List;


public interface FilterDataAccessInterface {
    JsonArray fetchRecipes(List<String> ingredients) throws IOException;


    JsonObject fetchRecipe2(int id) throws IOException;
}
