package entity;

/**
 * Represents an ingredient in a recipe.
 */
public class Ingredient {

    private String name;
    private String image;
    private double amount;

    /**
     * Creates an ingredient with the given name, image, and amount.
     *
     * @param name   the name of the ingredient
     * @param image  the image of the ingredient
     * @param amount the amount of the ingredient
     * @throws IllegalArgumentException if name is null or empty, image is null or empty, or amount is negative
     */
    public Ingredient(String name, String image, double amount) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (image == null || image.isEmpty()) {
            throw new IllegalArgumentException("Image cannot be null or empty");
        }
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }

        this.name = name;
        this.image = image;
        this.amount = amount;
    }

    /**
     * Returns the name of the ingredient.
     *
     * @return the name of the ingredient
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the image of the ingredient.
     *
     * @return the image of the ingredient
     */
    public String getImage() {
        return image;
    }

    /**
     * Returns the amount of the ingredient.
     *
     * @return the amount of the ingredient
     */
    public double getAmount() {
        return amount;
    }
}

