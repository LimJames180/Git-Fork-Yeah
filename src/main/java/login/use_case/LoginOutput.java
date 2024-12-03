package login.use_case;

import entity.Recipe;
import java.util.List;

public class LoginOutput {
    private final String message;
    private final List<Recipe> savedRecipes;

    public LoginOutput(String message, List<Recipe> savedRecipes) {
        this.message = message;
        this.savedRecipes = savedRecipes;
    }

    public String getMessage() {
        return message;
    }

    public List<Recipe> getSavedRecipes() {
        return savedRecipes;
    }
}