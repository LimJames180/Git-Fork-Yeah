package filter.use_case;


import filter.data_access.FilterDataAccess;
import filter.data_access.FilterDataAccessInterface;
import entity.Recipe;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Filter;


public class FilterInteractor implements FilterInputBoundary {
    private final FilterDataAccessInterface filterDataAccess;
    private FilterOutputBoundary FilterOutputBoundary;
    private List<Recipe> recipeList;



    public FilterInteractor(FilterDataAccess filterDataAccess, FilterOutputBoundary FilterOutputBoundary) {
        this.filterDataAccess = filterDataAccess;
        this.FilterOutputBoundary = FilterOutputBoundary;
    }


    @Override
    public void filterRecipes(FilterInput input) throws IOException {
        List<String> complexsearch = new ArrayList<>();
        List<String> ingredients = input.getIngredients();
        Map<String, Boolean> restrictions = input.getRestrictions();
        Map<String, Boolean> intolerances = input.getIntolerances();
        int offset = input.getOffset();

        List<String> some_list = new ArrayList<>();
        List<String> some_list2 = new ArrayList<>();

        for (String key : restrictions.keySet()) {
            if (restrictions.get(key)) {
                some_list.add(key);
            }
        }

        for (String key : intolerances.keySet()) {
            if (intolerances.get(key)) {
                some_list2.add(key);
            }
        }

        String ingredientsString = String.join(",", ingredients);
        String restrictionsString = String.join(",", some_list);
        String intolerancesString = String.join(",", some_list2);


        if (offset > 0) {
            complexsearch.add("offset=" + offset);
        }

        if (!intolerancesString.equals("")) {
            complexsearch.add("intolerances="+intolerancesString);
        }

        if (!restrictionsString.equals("")) {
            complexsearch.add("diet="+restrictionsString);
        }

        if (!ingredientsString.equals("")) {
            complexsearch.add("includeIngredients=" + ingredientsString);
        }


        recipeList = filterDataAccess.fetchComplexSearch(complexsearch);

        FilterOutput filterOutput = new FilterOutput(recipeList);
        FilterOutputBoundary.setFilterViewModel(filterOutput);
    }
}
