package login.entities;

import entity.Recipe;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;
    private List<Recipe> savedRecipes;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.savedRecipes = new ArrayList<>();
    }

    public void addRecipe(Recipe recipe) {
        savedRecipes.add(recipe);
    }

    public List<Recipe> getSavedRecipes() {
        return savedRecipes;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}