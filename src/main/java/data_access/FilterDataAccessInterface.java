package data_access;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import entity.Recipe;


import java.io.IOException;
import java.util.List;


public interface FilterDataAccessInterface {
    JsonArray fetchRecipes(List<String> ingredients) throws IOException;


    JsonObject fetchRecipe2(int id) throws IOException;

    List<Recipe> fetchComplexSearch(List<String> params) throws IOException;
}
