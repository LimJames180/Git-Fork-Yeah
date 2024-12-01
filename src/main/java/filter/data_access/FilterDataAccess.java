package filter.data_access;

import java.io.IOException;
import java.util.List;

import entity.Recipe;
import misc_interface_adapter.RecipeDataAccess;

/**
 * The FilterDataAccess class is responsible for fetching recipe data from the API.
 */
public class FilterDataAccess implements FilterDataAccessInterface {

    /**
     * Fetches recipes based on the given ingredients.
     * @param params the list filters to apply to the search
     * @return the list of recipes that match the given ingredients
     * @throws IOException if an I/O error occurs
     */
    public List<Recipe> fetchComplexSearch(List<String> params) throws IOException {
        return RecipeDataAccess.complexSearch(params);
    }
}
