package data_access;

import entity.Recipe;
import interface_adapter.RecipeController;

import java.io.IOException;
import java.util.List;

public class FilterDataAccess implements FilterDataAccessInterface {

    public List<Recipe> fetchComplexSearch(List<String> params) throws IOException {
        return RecipeController.complex_search(params);
        }
    }
