package interface_adapter.filter;

import entity.Recipe;
import use_case.filter.FilterInput;
import use_case.filter.FilterInteractor;

import java.util.List;
import java.util.Map;

public class FilterController {
    private FilterInteractor interactor;

    public FilterController(FilterInteractor interactor) {
        this.interactor = interactor;
    }

    public List<Recipe> handlefilter(List<String> ingrients, Map<String, Boolean> restrictions) {
        FilterInput input = new FilterInput(ingrients, restrictions);
        interactor.filterRecipes(input);
        return interactor.getRecipeList();

    }
}
