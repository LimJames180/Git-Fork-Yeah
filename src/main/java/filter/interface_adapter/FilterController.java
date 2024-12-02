package filter.interface_adapter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import filter.use_case.FilterInput;
import filter.use_case.FilterInteractor;

/**
 * The FilterController class is responsible for handling the filtering of recipes.
 */
public class FilterController {
    private final FilterInteractor interactor;

    public FilterController(FilterInteractor interactor) {
        this.interactor = interactor;
    }

    /**
     * Handles the filtering of recipes based on the given input criteria.
     * @param ingrients the list of ingredients to filter by
     * @param restrictions the map of restrictions to filter by
     * @param intolerances the map of intolerances to filter by
     * @param offset the offset for the search results
     * @throws IOException if an I/O error occurs
     */
    public void handlefilter(List<String> ingrients, Map<String, Boolean> restrictions,
                             Map<String, Boolean> intolerances, int offset) throws IOException {
        final FilterInput input = new FilterInput(ingrients, restrictions, intolerances, offset);
        interactor.filterRecipes(input);
    }
}
