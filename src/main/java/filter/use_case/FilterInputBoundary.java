package filter.use_case;


import entity.Recipe;

import java.io.IOException;
import java.util.List;

public interface FilterInputBoundary {
    void filterRecipes(FilterInput input) throws IOException;


}
