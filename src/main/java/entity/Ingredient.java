package entity;

public class Ingredient {

    private String name;
    private String image;
    private double amount;

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

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public double getAmount() {
        return amount;
    }
}

