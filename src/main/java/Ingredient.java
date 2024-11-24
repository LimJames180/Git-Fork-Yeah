public class Ingredient {

    private String name;
    private String image;
    private float amount;

    public Ingredient(String name, String image, int amount) {
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

    public float getAmount() {
        return amount;
    }
    
}
