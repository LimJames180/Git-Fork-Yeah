package filter.data_access;

import entity.Recipe;


import java.io.IOException;
import java.util.List;


public interface FilterDataAccessInterface {


    List<Recipe> fetchComplexSearch(List<String> params) throws IOException;
}
