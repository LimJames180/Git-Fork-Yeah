package instructions.app;

import instructions.data_access.InstructionsDataAccess;
import instructions.interface_adapter.InstructionsController;
import instructions.interface_adapter.InstructionsPresenter;
import instructions.interface_adapter.InstructionsViewModel;
import instructions.use_case.InstructionsInteractor;
import instructions.view.BaseView;
import instructions.view.InstructionsView;
import login.data_access.MongoUserDataAccessImpl;
import login.data_access.UserDataAccess;

public class InstructionsCaseFactory {
    private InstructionsCaseFactory() {}

    public static InstructionsView create(BaseView baseView, int id) {
        InstructionsViewModel instructionsViewModel = new InstructionsViewModel();
        instructionsViewModel.setId(id);
        InstructionsDataAccess instructionsDataAccess = new InstructionsDataAccess();
        InstructionsPresenter instructionsPresenter = new InstructionsPresenter(instructionsViewModel);
        InstructionsInteractor instructionsInteractor = new InstructionsInteractor(instructionsDataAccess,instructionsPresenter);

        InstructionsController instructionsController = new InstructionsController(instructionsInteractor);

        MongoUserDataAccessImpl userDataAccess = new MongoUserDataAccessImpl();

        InstructionsView instructionsView = new InstructionsView(instructionsController, instructionsViewModel, userDataAccess);
        instructionsView.setSession(baseView);
        return instructionsView;
    }
}
