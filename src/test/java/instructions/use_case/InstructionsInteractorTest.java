package instructions.use_case;

import instructions.data_access.InstructionsDataAccess;
import instructions.interface_adapter.InstructionsPresenter;
import instructions.interface_adapter.InstructionsViewModel;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Objects;


public class InstructionsInteractorTest extends TestCase {

    private final int id = 644387;
    private InstructionsInteractor instructionsInteractor;
    private InstructionsPresenter instructionsPresenter;
    private InstructionsViewModel instructionsViewModel;

    @BeforeEach
    public void setUp() {
        InstructionsDataAccess instructionsDataAccess = new InstructionsDataAccess();
        instructionsViewModel = new InstructionsViewModel();
        instructionsPresenter = new InstructionsPresenter(instructionsViewModel);
        instructionsInteractor = new InstructionsInteractor(instructionsDataAccess, instructionsPresenter);
    }

    @Test
    public void testImage() {
        InstructionsInputData instructionsInputData = new InstructionsInputData(id);
        instructionsInteractor.execute(instructionsInputData);
        assert Objects.equals(instructionsViewModel.getImage(), "https://img.spoonacular.com/recipes/644387-556x370.jpg");

    }

    @Test
    public void testIngredients() {
        InstructionsInputData instructionsInputData = new InstructionsInputData(id);
        instructionsInteractor.execute(instructionsInputData);
        assert Objects.equals(instructionsViewModel.getIngredients(), "Ingredients needed: olive oil, kale, vinegar, garlic");
    }

    @Test
    public void testInstructions() {
        InstructionsInputData instructionsInputData = new InstructionsInputData(id);
        instructionsInteractor.execute(instructionsInputData);
        assert Objects.equals(instructionsViewModel.getInstructions(), "1. Heat the olive oil in a large pot over medium heat.\n" +
                "2. Add the kale and cover.Stir occasionally until the volume of the kale is reduced by half. Uncover.\n" +
                "3. Add garlic and basalmic.Allow to cook for about another 30 seconds or so, mixing well so that the garlic and vinegar are well distributed.\n" +
                "4. Serve hot.");
    }

    @Test
    public void testNutrients() {
        InstructionsInputData instructionsInputData = new InstructionsInputData(id);
        instructionsInteractor.execute(instructionsInputData);
        assert Objects.equals(instructionsViewModel.getNutrients(), "Calories: 169.87 kcal\n" +
                "Fat: 14.98 g\n" +
                "Saturated Fat: 2.05 g\n" +
                "Carbohydrates: 7.46 g\n" +
                "Net Carbohydrates: 4.76 g\n" +
                "Sugar: 4.12 g\n" +
                "Cholesterol: 0.0 mg\n" +
                "Sodium: 40.51 mg\n" +
                "Protein: 2.11 g\n" +
                "Vitamin K: 261.95 µg\n" +
                "Vitamin A: 6493.63 IU\n" +
                "Vitamin C: 61.18 mg\n" +
                "Manganese: 0.48 mg\n" +
                "Calcium: 174.44 mg\n" +
                "Vitamin E: 2.45 mg\n" +
                "Vitamin B2: 0.23 mg\n" +
                "Fiber: 2.7 g\n" +
                "Folate: 40.35 µg\n" +
                "Potassium: 259.24 mg\n" +
                "Iron: 1.32 mg\n" +
                "Magnesium: 24.71 mg\n" +
                "Vitamin B6: 0.11 mg\n" +
                "Vitamin B1: 0.08 mg\n" +
                "Phosphorus: 42.61 mg\n" +
                "Vitamin B3: 0.78 mg\n" +
                "Copper: 0.05 mg\n" +
                "Zinc: 0.29 mg\n" +
                "Selenium: 0.8 µg");
    }
}
