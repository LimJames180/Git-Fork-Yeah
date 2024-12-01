package filter.use_case;

import java.io.IOException;

public interface FilterInputBoundary {
    void filterRecipes(FilterInput input) throws IOException;
}
