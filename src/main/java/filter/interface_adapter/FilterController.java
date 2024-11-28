package filter.interface_adapter;

import entity.Recipe;
import filter.use_case.FilterInput;
import filter.use_case.FilterInteractor;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class FilterController {
    private FilterInteractor interactor;

    public FilterController(FilterInteractor interactor) {
        this.interactor = interactor;
    }

    public void handlefilter(List<String> ingrients, Map<String, Boolean> restrictions, Map<String, Boolean> intolerances, int offset) throws IOException {
        FilterInput input = new FilterInput(ingrients, restrictions, intolerances, offset);
        interactor.filterRecipes(input);
    }
}
