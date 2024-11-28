package use_case.filter;

import filter.data_access.FilterDataAccess;
import entity.Recipe;
import filter.interface_adapter.FilterPresenter;
import filter.interface_adapter.FilterViewModel;
import filter.use_case.FilterInput;
import filter.use_case.FilterInteractor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class FilterInteractorTest {

    private FilterInteractor filterInteractor;
    private FilterViewModel filterViewModel;

    @BeforeEach
    void setUp() {
        FilterDataAccess dataAccess = new FilterDataAccess();
        filterViewModel = new FilterViewModel();
        FilterPresenter presenter = new FilterPresenter(filterViewModel);
        filterInteractor = new FilterInteractor(dataAccess, presenter);
    }

    // Test for a normal case of ingredients list
    @Test
    void filterRecipes() throws IOException {
        Map<String, Boolean> map = Map.of("glutenfree", false, "vegetarian", false, "vegan", false, "ketogenic", false);
        Map<String, Boolean> map2 = Map.of("dairy", false, "egg", false, "peanut", false, "seafood", false);

        FilterInput input = new FilterInput(List.of("chicken", "rice"), map, map2,0);

        filterInteractor.filterRecipes(input);
        List<Recipe> recipes = filterViewModel.getRecipeList();
        System.out.println(recipes);
        assertDoesNotThrow(() -> filterInteractor.filterRecipes(input));
        assertNotNull(recipes);
        assertFalse(recipes.contains(null), "The list should not contain null elements.");
    }

    // Test for empty ingredients list
    @Test
    void filterRecipes2() throws IOException {
        List<String> list = new ArrayList<>();
        Map<String, Boolean> map = Map.of("glutenfree", false, "vegetarian", false, "vegan", false, "ketogenic", false);
        Map<String, Boolean> map2 = Map.of("dairy", false, "egg", false, "peanut", false, "seafood", false);

        FilterInput input = new FilterInput(list,map,map2,0);
        filterInteractor.filterRecipes(input);

        List<Recipe> recipes = filterViewModel.getRecipeList();
        System.out.println(recipes);

        assertDoesNotThrow(() -> filterInteractor.filterRecipes(input));
        assertNotNull(recipes);
        assertFalse(recipes.contains(null), "The list should not contain null elements.");
    }

    //Test for Long ingredients list
    @Test
    void filterRecipes3() throws IOException {
        List<String> list = List.of("chicken", "rice", "salt", "tomatoes", "onions", "pepper", "garlic", "oil", "water", "sugar", "flour", "butter", "milk", "eggs", "cheese", "bread", "lettuce", "carrots", "potatoes", "beef", "pork", "fish", "shrimp", "lobster", "crab", "squid", "octopus", "mushrooms", "broccoli", "cauliflower", "spinach", "kale", "asparagus", "zucchini", "cucumber", "bell peppers", "chili peppers", "jalapenos", "habaneros", "cayenne", "paprika", "cumin", "coriander", "turmeric", "cinnamon", "nutmeg", "cloves", "allspice", "cardamom", "vanilla", "lemon", "lime", "orange", "grapefruit", "apple", "banana", "strawberries", "blueberries", "raspberries", "blackberries", "cherries", "peaches", "plums", "apricots", "pears", "kiwi", "pineapple", "mango", "papaya", "watermelon", "cantaloupe", "honeydew", "avocado", "olives", "coconut", "almonds", "cashews", "walnuts", "pecans", "pistachios", "peanuts", "macadamia", "hazelnuts", "brazil nuts", "chestnuts", "pine nuts", "sunflower seeds", "pumpkin seeds", "sesame seeds", "flax seeds", "chia seeds", "quinoa", "bulgur", "barley", "oats", "rice", "pasta", "bread", "tortillas", "bagels", "croissants", "muffins", "pancakes", "waffles", "donuts", "cookies", "cake", "pie", "brownies", "candy", "chocolate", "ice cream", "sorbet", "gelato", "frozen yogurt", "pudding", "jello", "cereal", "granola", "trail mix", "chips", "crackers", "popcorn");
        Map<String, Boolean> map = Map.of("glutenfree", false, "vegetarian", false, "vegan", false, "ketogenic", false);
        Map<String, Boolean> map2 = Map.of("dairy", false, "egg", false, "peanut", false, "seafood", false);

        FilterInput input = new FilterInput(list,map,map2,0);
        filterInteractor.filterRecipes(input);

        List<Recipe> recipes = filterViewModel.getRecipeList();
        System.out.println(recipes);

        assertDoesNotThrow(() -> filterInteractor.filterRecipes(input));
        assertNotNull(recipes);
        assertFalse(recipes.contains(null), "The list should not contain null elements.");
    }

    // Test for restictions and intolerances all true
    @Test
    void filterRecipes4() throws IOException {
        List<String> list = List.of("rice");
        Map<String, Boolean> map = Map.of("glutenfree", true, "vegetarian", true, "vegan", true, "ketogenic", true);
        Map<String, Boolean> map2 = Map.of("dairy", true, "egg", true, "peanut", true, "seafood", true);

        FilterInput input = new FilterInput(list,map,map2,0);
        filterInteractor.filterRecipes(input);

        List<Recipe> recipes = filterViewModel.getRecipeList();
        System.out.println(recipes);

        assertDoesNotThrow(() -> filterInteractor.filterRecipes(input));
        assertNotNull(recipes);
        assertFalse(recipes.contains(null), "The list should not contain null elements.");
    }

    // Test for a normal case of ingredients list
    @Test
    void filterRecipes5() throws IOException {
        Map<String, Boolean> map = Map.of("glutenfree", false, "vegetarian", false, "vegan", false, "ketogenic", false);
        Map<String, Boolean> map2 = Map.of("dairy", false, "egg", false, "peanut", false, "seafood", false);

        FilterInput input = new FilterInput(List.of("chicken", "rice"), map, map2,10);

        filterInteractor.filterRecipes(input);


        List<Recipe> recipes = filterViewModel.getRecipeList();
        System.out.println(recipes);

        assertDoesNotThrow(() -> filterInteractor.filterRecipes(input));
        assertNotNull(recipes);
        assertFalse(recipes.contains(null), "The list should not contain null elements.");
    }


    @Test
    void filterRecipes7() throws IOException {
        Map<String, Boolean> map = Map.of("glutenfree", true, "vegetarian", false, "vegan", false, "ketogenic", false);
        Map<String, Boolean> map2 = Map.of("dairy", false, "egg", false, "peanut", false, "seafood", false);

        FilterInput input = new FilterInput(List.of("chicken", "rice"), map, map2,-1);

        filterInteractor.filterRecipes(input);

        List<Recipe> recipes = filterViewModel.getRecipeList();
        System.out.println(recipes);

        assertDoesNotThrow(() -> filterInteractor.filterRecipes(input));
        assertNotNull(recipes);
        assertFalse(recipes.contains(null), "The list should not contain null elements.");
    }
}