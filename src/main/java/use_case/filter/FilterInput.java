package use_case.filter;

import java.util.List;
import java.util.Map;

public class FilterInput {
    private List<String> ingredients;
    private Map<String, Boolean> restrictions;


    public FilterInput(List<String> ingredients, Map<String, Boolean> restrictions) {
        this.ingredients = ingredients;
        this.restrictions = restrictions;
    }

    public List<String> getIngredients() {
        return ingredients;
    }


    public Map<String, Boolean> getRestrictions() {
        return restrictions;
    }
}