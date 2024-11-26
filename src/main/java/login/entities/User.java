package login.entities;

import java.util.ArrayList;
import java.util.List;


public class User {
    private final String username;
    private final String password;
    private List<Recipe> savedRecipes;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.savedRecipes = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Recipe> getSavedRecipes() {
        return savedRecipes;
    }

    public void addRecipe(Recipe recipe) {
        savedRecipes.add(recipe);
    }
}
