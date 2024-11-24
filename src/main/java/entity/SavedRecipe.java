package entity;

public class SavedRecipe {
    private final String username;
    private final String recipeId;

    public SavedRecipe(String username, String recipeId) {
        this.username = username;
        this.recipeId = recipeId;
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getRecipeId() {
        return recipeId;
    }
}
