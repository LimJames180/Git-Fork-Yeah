package use_case.filter;


import data_access.FilterDataAccess;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import data_access.FilterDataAccessInterface;


import java.io.IOException;
import java.util.List;
import java.util.Map;


public class FilterInteractor implements FilterInputBoundary {
    private final FilterDataAccessInterface filterDataAccess;
    StringBuilder resultBuilder = new StringBuilder();


    public FilterInteractor(FilterDataAccess filterDataAccess) {
        this.filterDataAccess = filterDataAccess;
    }


    @Override
    public void filterRecipes(FilterInput input) {
        List<String> ingredients = input.getIngredients();
        Map<String, Boolean> restrictions = input.getRestrictions();


        JsonArray recipes = null;
        try {
            recipes = filterDataAccess.fetchRecipes(ingredients);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        recipes.forEach(recipe -> {
            String title = recipe.getAsJsonObject().get("title").getAsString();
            int id = recipe.getAsJsonObject().get("id").getAsInt();
            JsonObject recipeInfo = null;
            try {
                recipeInfo = filterDataAccess.fetchRecipe2(id);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (restrictions.get("Dairy-free") == recipeInfo.get("dairyFree").getAsBoolean() || !restrictions.get("Dairy-free")) {
                if (restrictions.get("Gluten-free") == recipeInfo.get("glutenFree").getAsBoolean() || !restrictions.get("Gluten-free")) {
                    if (restrictions.get("Vegan") == recipeInfo.get("vegan").getAsBoolean() || !restrictions.get("Vegan")) {
                        resultBuilder.append("Recipe: ").append(title).append("\n\n");
                    }
                }
            }
        });
    }
    public StringBuilder getResults() {
        return resultBuilder;
    }
}
