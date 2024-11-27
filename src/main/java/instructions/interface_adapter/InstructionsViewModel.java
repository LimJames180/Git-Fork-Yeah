package instructions.interface_adapter;

import entity.Ingredient;

import java.util.List;

public class InstructionsViewModel {
    private String instructions;
    private String ingredients;
    private String image;

    public String getInstructions() {
        return instructions;
    }

    public String getIngredients() {return ingredients;}

    public String getImage() {
        System.out.println(image);
        return image;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public void setIngredients(String ingredients) {this.ingredients = ingredients;}

    public void setImage(String image) {this.image = image;}
}
