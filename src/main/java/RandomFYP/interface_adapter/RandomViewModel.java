package RandomFYP.interface_adapter;

public class RandomViewModel {
    private String id;
    private String title;
    private String image;
    private String instructions;
    private String ingredients;

    public RandomViewModel(String id, String title, String image, String instructions, String ingredients) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.instructions = instructions;
        this.ingredients = ingredients;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getInstructions() {
        return instructions;
    }

    public String getIngredients() {
        return ingredients;
    }
}
