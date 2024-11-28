package instructions.use_case;

public class InstructionsOutputData {
    private String instructions;
    private String ingredients;
    private String image;
    private String nutrients;

    public InstructionsOutputData(String instructions, String ingredients, String image, String nutrients) {
        this.instructions = instructions;
        this.ingredients = ingredients;
        this.image = image;
        this.nutrients = nutrients;
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
}
