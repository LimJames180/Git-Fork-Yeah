import interface_adapter.RecipeController;

import java.io.IOException;

public class Recipe_Test {

    public static void main(String[] args) throws IOException {
        System.out.println(RecipeController.get_recipe_instructions("324694"));
    }
}
