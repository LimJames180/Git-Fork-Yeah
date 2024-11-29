package login.data_access;

import entity.Recipe;
import login.entities.User;

import java.util.List;

public interface UserDataAccess {
    User findUser(String username);
    void saveUser(User user);
    void saveRecipeForUser(String username, Recipe recipe);
    List<Recipe> getUserRecipes(String username);
}
