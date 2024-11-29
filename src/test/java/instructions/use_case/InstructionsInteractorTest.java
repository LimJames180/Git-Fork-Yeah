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
}
