package filter.data_access;

import java.io.IOException;
import java.util.List;

import entity.Recipe;

/**
 * Interface for fetching recipe data from the API.
 */
public interface FilterDataAccessInterface {

    /**
     * Fetches recipes based on the given ingredients.
     * @param params the list filters to apply to the search
     * @return the list of recipes that match the given ingredients
     * @throws IOException if an I/O error occurs
     */
    List<Recipe> fetchComplexSearch(List<String> params) throws IOException;
}
