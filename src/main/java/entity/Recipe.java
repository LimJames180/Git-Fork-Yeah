package entity;

import misc_interface_adapter.RecipeDataAccess;

import java.io.IOException;
import java.util.List;

/**
 * Represents a recipe.
 */
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

    /**
     * Creates a recipe with the given id.
     *
     * @param id the id of the recipe
     */
    public Recipe(String id) {
        this.recipeController = new RecipeDataAccess();
        this.id = id;
    }

    /**
     * Returns the ingredients of the recipe.
     *
     * @return the ingredients of the recipe
     * @throws IOException if an I/O error occurs
     */
    public List<Ingredient> getIngredients() throws IOException {
        if (!loadedIngredient) {
            ingredients = RecipeDataAccess.getIngredients(id);
            loadedIngredient = true;
        }
        return ingredients;
    }

    /**
     * Returns the nutrients of the recipe.
     *
     * @return the nutrients of the recipe
     * @throws IOException if an I/O error occurs
     */
    public List<Nutrients> getNutrients() throws IOException {
        if (!loadedNutrients) {
            // load recipe
            RecipeDataAccess.getNutrients(id);
            // load nutrients
            RecipeDataAccess.getNutrients(id);
            loadedNutrients = true;
        }
        return RecipeDataAccess.getNutrients(id);
    }

    /**
     * Returns the title of the recipe.
     *
     * @return the title of the recipe
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the image of the recipe.
     *
     * @return the image of the recipe
     */
    public String getImage() {
        return image;
    }

    /**
     * Returns the id of the recipe.
     *
     * @return the id of the recipe
     */
    public int getId() {
        final int num = Integer.parseInt(id);
        return num;
    }

    /**
     * Returns the name of the recipe.
     *
     * @param recipe the recipe
     * @return the name of the recipe
     */
    public String getRecipeName(Recipe recipe) {
        return recipeController.getName(recipe);
    }

    /**
     * Returns the image of the recipe.
     *
     * @return the image of the recipe
     * @throws IOException if an I/O error occurs
     */
    public String getInstructions() throws IOException {
        if (instructions == null) {
            instructions = RecipeDataAccess.getRecipeInstructions(id);
        }
        return instructions;
    }

}
