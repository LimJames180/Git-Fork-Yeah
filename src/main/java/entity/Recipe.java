package entity;

import misc_interface_adapter.RecipeDataAccess;

import java.io.IOException;
import java.util.List;

public class Recipe {

    private Boolean loadedIngredient = false;
    private Boolean loadedNutrients = false;
    private String id;
    private String title;
    private String image;
    private List<Ingredient> ingredients;
    private List<Nutrients> nutrients;
    private String instructions;

    private RecipeDataAccess recipeController;

    public Recipe(String id, String title, String image) {
        this.recipeController = new RecipeDataAccess();
        this.id = id;
        this.title = title;
        this.image = image;
    }

    public Recipe(String id) {
        this.recipeController = new RecipeDataAccess();
        this.id = id;
    }

    public List<Ingredient> get_ingredients() throws IOException {
        if (!loadedIngredient) {
            ingredients = RecipeDataAccess.getIngredients(id);
            loadedIngredient = true;
        }
        return ingredients;
    }

    public List<Nutrients> get_nutrients() throws IOException {
        if (!loadedNutrients) {
            // load recipe
            RecipeDataAccess.getNutrients(id);
            // load nutrients
            RecipeDataAccess.getNutrients(id);
            loadedNutrients = true;
        }
        return RecipeDataAccess.getNutrients(id);
    }
    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public int getId() {
        int num = Integer.parseInt(id);
        return num;
    }

    public String getRecipeName(Recipe recipe) {
        return recipeController.getName(recipe);
    }

    public String getInstructions() throws IOException {
        if (instructions == null) {
            instructions = RecipeDataAccess.getRecipeInstructions(id);
        }
        return instructions;
    }

}
