package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IngredientTest {

    @Test
    void testValidIngredientCreation() {
        String name = "Sugar";
        String image = "sugar.png";
        double amount = 500.0;

        Ingredient ingredient = new Ingredient(name, image, amount);

        assertNotNull(ingredient, "Ingredient object should not be null");
        assertEquals(name, ingredient.getName(), "Name should match the input");
        assertEquals(image, ingredient.getImage(), "Image should match the input");
        assertEquals(amount, ingredient.getAmount(), "Amount should match the input");
    }

    @Test
    void testIngredientWithNullName() {
        String name = null;
        String image = "sugar.png";
        double amount = 500.0;

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new Ingredient(name, image, amount),
                "Creating an Ingredient with a null name should throw IllegalArgumentException");

        assertEquals("Name cannot be null or empty", exception.getMessage());
    }

    @Test
    void testIngredientWithEmptyName() {
        String name = "";
        String image = "sugar.png";
        double amount = 500.0;

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new Ingredient(name, image, amount),
                "Creating an Ingredient with an empty name should throw IllegalArgumentException");

        assertEquals("Name cannot be null or empty", exception.getMessage());
    }

    @Test
    void testIngredientWithNullImage() {
        String name = "Sugar";
        String image = null;
        double amount = 500.0;

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new Ingredient(name, image, amount),
                "Creating an Ingredient with a null image should throw IllegalArgumentException");

        assertEquals("Image cannot be null or empty", exception.getMessage());
    }

    @Test
    void testIngredientWithEmptyImage() {
        String name = "Sugar";
        String image = "";
        double amount = 500.0;

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new Ingredient(name, image, amount),
                "Creating an Ingredient with an empty image should throw IllegalArgumentException");

        assertEquals("Image cannot be null or empty", exception.getMessage());
    }

    @Test
    void testIngredientWithNegativeAmount() {
        String name = "Sugar";
        String image = "sugar.png";
        double amount = -10.0;

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new Ingredient(name, image, amount),
                "Creating an Ingredient with a negative amount should throw IllegalArgumentException");

        assertEquals("Amount cannot be negative", exception.getMessage());
    }

    @Test
    void testIngredientWithZeroAmount() {
        String name = "Salt";
        String image = "salt.png";
        double amount = 0.0;

        Ingredient ingredient = new Ingredient(name, image, amount);

        assertEquals(0.0, ingredient.getAmount(), "Amount should be correctly set to 0");
    }

    @Test
    void testValidGetters() {
        String name = "Butter";
        String image = "butter.jpg";
        double amount = 250.0;

        Ingredient ingredient = new Ingredient(name, image, amount);

        assertEquals(name, ingredient.getName(), "Getter should return the correct name");
        assertEquals(image, ingredient.getImage(), "Getter should return the correct image");
        assertEquals(amount, ingredient.getAmount(), "Getter should return the correct amount");
    }
}