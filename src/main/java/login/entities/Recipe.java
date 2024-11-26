package login.entities;

public class Recipe {
    private String recipeId;
    private String title;

    public Recipe(String recipeId, String title) {
        this.recipeId = recipeId;
        this.title = title;
    }

    public String getRecipeId() {
        return recipeId;
    }

    public String getTitle() {
        return title;
    }
}