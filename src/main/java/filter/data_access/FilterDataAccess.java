package filter.data_access;

import entity.Recipe;
import misc_interface_adapter.RecipeDataAccess;

import java.io.IOException;
import java.util.List;

public class FilterDataAccess implements FilterDataAccessInterface {

    public List<Recipe> fetchComplexSearch(List<String> params) throws IOException {
        return RecipeDataAccess.complexSearch(params);
        }
    }
