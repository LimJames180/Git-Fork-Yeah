package use_case.filter;

import data_access.FilterDataAccess;
import entity.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class FilterInteractorTest {

    private FilterInteractor filterInteractor;

    @BeforeEach
    void setUp() {
        FilterDataAccess dataAccess = new FilterDataAccess();
        filterInteractor = new FilterInteractor(dataAccess);
    }

    // Test for a normal case of ingredients list
    @Test
    void filterRecipes() {
        Map<String, Boolean> map = Map.of("glutenfree", false, "vegetarian", false, "vegan", false, "ketogenic", false);
        Map<String, Boolean> map2 = Map.of("dairy", false, "egg", false, "peanut", false, "seafood", false);

        FilterInput input = new FilterInput(List.of("chicken", "rice"), map, map2);

        filterInteractor.filterRecipes(input);

        List<Recipe> recipes = filterInteractor.getRecipeList();
        assertNotNull(recipes);
        // Add more assertions based on expected behavior
    }

    // Test for empty ingredients list
    @Test
    void filterRecipes2() {
        List<String> list = new ArrayList<>();
        Map<String, Boolean> map = Map.of("glutenfree", false, "vegetarian", false, "vegan", false, "ketogenic", false);
        Map<String, Boolean> map2 = Map.of("dairy", false, "egg", false, "peanut", false, "seafood", false);

        FilterInput input = new FilterInput(list,map,map2);
        filterInteractor.filterRecipes(input);

        List<Recipe> recipes = filterInteractor.getRecipeList();
        assertNotNull(recipes);
        // Add more assertions based on expected behavior
    }

    //Test for Long ingredients list
    @Test
    void filterRecipes3() {
        List<String> list = List.of("chicken", "rice", "salt", "tomatoes", "onions", "pepper", "garlic", "oil", "water", "sugar", "flour", "butter", "milk", "eggs", "cheese", "bread", "lettuce", "carrots", "potatoes", "beef", "pork", "fish", "shrimp", "lobster", "crab", "squid", "octopus", "mushrooms", "broccoli", "cauliflower", "spinach", "kale", "asparagus", "zucchini", "cucumber", "bell peppers", "chili peppers", "jalapenos", "habaneros", "cayenne", "paprika", "cumin", "coriander", "turmeric", "cinnamon", "nutmeg", "cloves", "allspice", "cardamom", "vanilla", "lemon", "lime", "orange", "grapefruit", "apple", "banana", "strawberries", "blueberries", "raspberries", "blackberries", "cherries", "peaches", "plums", "apricots", "pears", "kiwi", "pineapple", "mango", "papaya", "watermelon", "cantaloupe", "honeydew", "avocado", "olives", "coconut", "almonds", "cashews", "walnuts", "pecans", "pistachios", "peanuts", "macadamia", "hazelnuts", "brazil nuts", "chestnuts", "pine nuts", "sunflower seeds", "pumpkin seeds", "sesame seeds", "flax seeds", "chia seeds", "quinoa", "bulgur", "barley", "oats", "rice", "pasta", "bread", "tortillas", "bagels", "croissants", "muffins", "pancakes", "waffles", "donuts", "cookies", "cake", "pie", "brownies", "candy", "chocolate", "ice cream", "sorbet", "gelato", "frozen yogurt", "pudding", "jello", "cereal", "granola", "trail mix", "chips", "crackers", "popcorn");
        Map<String, Boolean> map = Map.of("glutenfree", false, "vegetarian", false, "vegan", false, "ketogenic", false);
        Map<String, Boolean> map2 = Map.of("dairy", false, "egg", false, "peanut", false, "seafood", false);

        FilterInput input = new FilterInput(list,map,map2);
        filterInteractor.filterRecipes(input);

        List<Recipe> recipes = filterInteractor.getRecipeList();
        assertNotNull(recipes);
        // Add more assertions based on expected behavior
    }

    // Test for restictions and intolerances all true
    @Test
    void filterRecipes4() {
        List<String> list = List.of("chicken", "rice");
        Map<String, Boolean> map = Map.of("glutenfree", true, "vegetarian", true, "vegan", true, "ketogenic", true);
        Map<String, Boolean> map2 = Map.of("dairy", true, "egg", true, "peanut", true, "seafood", true);

        FilterInput input = new FilterInput(list,map,map2);
        filterInteractor.filterRecipes(input);

        List<Recipe> recipes = filterInteractor.getRecipeList();
        assertNotNull(recipes);
        // Add more assertions based on expected behavior
    }

}