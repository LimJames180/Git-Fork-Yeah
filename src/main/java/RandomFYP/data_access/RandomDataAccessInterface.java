package RandomFYP.data_access;

import entity.Recipe;

import java.io.IOException;
import java.util.List;

public interface RandomDataAccessInterface {

    List<Recipe> fetchRandom() throws IOException;
}
