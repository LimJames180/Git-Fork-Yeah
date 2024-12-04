package ingredientsSearch.use_case;

import entity.Ingredient;
import ingredients_searcher.data_access.IngredientDataAccess;
import ingredients_searcher.interface_adapter.AddIngredientPresenter;
import ingredients_searcher.interface_adapter.AddIngredientViewModel;
import ingredients_searcher.use_case.AddIngredientInput;
import ingredients_searcher.use_case.AddIngredientInteractor;
import ingredients_searcher.use_case.AddIngredientOutput;
import ingredients_searcher.use_case.AddIngredientOutputBoundary;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

import static org.junit.Assert.assertEquals;

class IngredientsSearchInteractorTest {
    private final Ingredient testIngredient = new Ingredient("carrot",
            "https://spoonacular.com/cdn/ingredients_100x100/sliced-carrot.png", 0);
    private AddIngredientViewModel viewModel = new AddIngredientViewModel();
    private IngredientDataAccess dataAccess = new IngredientDataAccess();
    private AddIngredientPresenter presenter = new AddIngredientPresenter(viewModel);
    private AddIngredientInteractor interactor = new AddIngredientInteractor(presenter, dataAccess);
    private AddIngredientOutputBoundary outputBoundary = mock(AddIngredientOutputBoundary.class);

    @Test
    void searchIngredient(){
        AddIngredientInput input = new AddIngredientInput("carrot");
        Ingredient result = interactor.execute(input);

        assertEquals(testIngredient.getName(), result.getName());
        assertEquals(testIngredient.getImage(), result.getImage());
    }

    @Test
    void invalidIngredient() {
        AddIngredientInteractor testInteractor = new AddIngredientInteractor(outputBoundary, dataAccess);
        AddIngredientInput input = new AddIngredientInput("shtbjhejwkew");
        testInteractor.execute(input);

        verify(outputBoundary).addfailed("Ingredient not found");
    }

    @Test
    void presenter(){
        AddIngredientOutput output = new AddIngredientOutput(testIngredient);
        presenter.setViewModel(output);

        assertEquals("carrot", viewModel.getIngredient());
        assertSame(testIngredient, output.getIngredient());
    }

}
