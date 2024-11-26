package login.saverecipe;

import login.data_access.UserDataAccess;
import login.entities.Recipe;
import login.entities.User;

public class SaveRecipeInteractor implements SaveRecipeInputBoundary {
    private final UserDataAccess userDataAccess;
    private final String username; // Assume you have the username available

    public SaveRecipeInteractor(UserDataAccess userDataAccess, String username) {
        this.userDataAccess = userDataAccess;
        this.username = username;
    }

    @Override
    public void execute(SaveRecipeInput input) {
        Recipe recipe = input.getRecipe();
        User user = userDataAccess.findUser(username);
        if (user != null) {
            user.addRecipe(recipe); // Add the recipe to the user's saved recipes
            userDataAccess.saveUser(user); // Save the updated user back to the database
        }
    }
}
