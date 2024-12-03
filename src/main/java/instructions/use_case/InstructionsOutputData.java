package instructions.use_case;

public class InstructionsOutputData {
    private String instructions;
    private String ingredients;
    private String image;
    private String nutrients;
    private int id;

    public InstructionsOutputData(String instructions, String ingredients, String image, String nutrients, int id) {
        this.instructions = instructions;
        this.ingredients = ingredients;
        this.image = image;
        this.nutrients = nutrients;
        this.id = id;
    }

    public String getInstructions() {
        return instructions;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getImage() {
        return image;
    }

    public String getNutrients() {
        return nutrients;
    }

    public int getId() {return id;}
}
