package login.data_access;

import entity.Recipe;
import login.entities.User;

import java.util.List;

/**
 * Interface for accessing user data in the system.
 */
public interface UserDataAccess {

    /**
     * Finds a user by their username.
     *
     * @param username the username of the user to find
     * @return the User object if found, otherwise null
     */
    User findUser(String username);

    /**
     * Saves a new user to the data store.
     *
     * @param user the User object to save
     */
    void saveUser(User user);

    /**
     * Saves a recipe for a specific user.
     *
     * @param username the username of the user
     * @param recipe the Recipe object to save
     */
    void saveRecipeForUser(String username, Recipe recipe);

    /**
     * Retrieves a list of recipes associated with a specific user.
     *
     * @param username the username of the user
     * @return a List of Recipe objects associated with the user
     */
    List<Recipe> getUserRecipes(String username);
}
