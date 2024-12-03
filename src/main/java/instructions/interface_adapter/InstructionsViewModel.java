package instructions.interface_adapter;

public class InstructionsViewModel {
    private String instructions;
    private String ingredients;
    private String image;
    private String nutrients;
    private int id;

    public String getInstructions() {
        return instructions;
    }

    public String getIngredients() {return ingredients;}

    public String getImage() {
        return image;
    }

    public String getNutrients() {
        return nutrients;
    }

    public int getId() {
        return id;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public void setIngredients(String ingredients) {this.ingredients = ingredients;}

    public void setImage(String image) {this.image = image;}

    public void setNutrients(String nutrients) {this.nutrients = nutrients;}

    public void setId(int id) {this.id = id;}
}
