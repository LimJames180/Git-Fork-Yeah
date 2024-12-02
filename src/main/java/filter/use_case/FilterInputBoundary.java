package filter.use_case;

import java.io.IOException;

/**
 * Interface for filtering recipes based on the given input.
 */
public interface FilterInputBoundary {
    /**
     * Filters recipes based on the given input.
     * @param input the input for filtering recipes
     * @throws IOException if an I/O error occurs
     */
    void filterRecipes(FilterInput input) throws IOException;
}
