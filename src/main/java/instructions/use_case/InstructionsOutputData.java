package instructions.use_case;

public class InstructionsOutputData {
    private String instructions;
    private String ingredients;
    private String image;
    public InstructionsOutputData(String instructions, String ingredients, String image) {
        this.instructions = instructions;
        this.ingredients = ingredients;
        this.image = image;
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
}
