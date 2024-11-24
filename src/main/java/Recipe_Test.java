import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Recipe_Test {

    public static void main(String[] args) throws IOException {
        List<Recipe> list = RecipeController.complex_search(Arrays.asList("query=burger", "number=5"));
        System.out.println(RecipeController.get_nutrients("1003464"));
//        System.out.println(RecipeController.Random_recipe());
    }
}
