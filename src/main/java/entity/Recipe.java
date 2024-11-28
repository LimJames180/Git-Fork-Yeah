package entity;

import interface_adapter.RecipeController;

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

    public Recipe(String id, String title, String image) {
        this.id = id;
        this.title = title;
        this.image = image;
    }

    public List<Ingredient> get_ingredients() throws IOException {
        if (!loadedIngredient) {
            ingredients = RecipeController.get_ingredients(id);
            loadedIngredient = true;
        }
        return ingredients;
    }

    public List<Nutrients> get_nutrients() throws IOException {
        if (!loadedNutrients) {
            // load recipe
            RecipeController.get_nutrients(id);
            // load nutrients
            RecipeController.get_nutrients(id);
            loadedNutrients = true;
        }
        return RecipeController.get_nutrients(id);
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

    public String getInstructions() throws IOException {
        if (instructions == null) {
            instructions = RecipeController.get_recipe_instructions(id);
        }
        return instructions;
    }

}
