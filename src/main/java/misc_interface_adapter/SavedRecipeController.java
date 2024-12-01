package misc_interface_adapter;

import login.data_access.UserDataAccess;
import entity.Recipe;

public class SavedRecipeController {
    private final UserDataAccess userDataAccess;

    public SavedRecipeController(UserDataAccess userDataAccess) {
        this.userDataAccess = userDataAccess;
    }

    public void saveRecipe(String username, Recipe recipe) {
        userDataAccess.saveRecipeForUser(username, recipe);
    }
}