package filter.use_case;

import java.util.List;
import java.util.Map;

public class FilterInput {
    private List<String> ingredients;
    private Map<String, Boolean> restrictions;
    private Map<String, Boolean> intolerances;
    private int offset;


    public FilterInput(List<String> ingredients, Map<String, Boolean> restrictions, Map<String, Boolean> intolerances, int offset) {
        this.ingredients = ingredients;
        this.restrictions = restrictions;
        this.intolerances = intolerances;
        this.offset = offset;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public Map<String, Boolean> getRestrictions() {
        return restrictions;
    }

    public Map<String, Boolean> getIntolerances() {
        return intolerances;
    }

    public int getOffset() {
        return offset;
    }
}