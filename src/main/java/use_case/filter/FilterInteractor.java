package use_case.filter;


import data_access.FilterDataAccess;
import data_access.FilterDataAccessInterface;
import entity.Recipe;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class FilterInteractor implements FilterInputBoundary {
    private final FilterDataAccessInterface filterDataAccess;
    private List<Recipe> recipeList;



    public FilterInteractor(FilterDataAccess filterDataAccess) {
        this.filterDataAccess = filterDataAccess;
    }


    @Override
    public void filterRecipes(FilterInput input) {
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
        try {
            recipeList = filterDataAccess.fetchComplexSearch(complexsearch);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Recipe> getRecipeList() {
        return recipeList;
    }

}
