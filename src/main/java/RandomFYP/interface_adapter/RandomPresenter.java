package RandomFYP.interface_adapter;

import RandomFYP.use_case.RandomOutput;
import RandomFYP.use_case.RandomOutputBoundary;
import entity.Ingredient;
import entity.Recipe;

import java.io.IOException;

public class RandomPresenter implements RandomOutputBoundary {
    private String id;
    private String title;
    private String image;
    private String instructions;

    private StringBuilder ingredients;

    public void RandomPresenter() throws IOException {

    }

    @Override
    public RandomViewModel setRandomViewModel(RandomOutput input) throws IOException {
        Recipe recipe = input.getRecipeList().get(0);

        this.id = String.valueOf(recipe.getId());
        this.title = recipe.getTitle();
        this.image = recipe.getImage();
        this.instructions = recipe.getInstructions();
        this.ingredients = new StringBuilder();

        for (final Ingredient ingredient : recipe.getIngredients()) {
            this.ingredients .append(ingredient.getName() + "\n");
        }


        return new RandomViewModel(this.id, this.title, this.image, this.instructions, this.ingredients.toString());

    }
}
