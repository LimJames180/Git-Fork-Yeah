package login.use_case;

import login.data_access.UserDataAccess;
import login.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class SignupInteractorTest {
    private UserDataAccess userDataAccess;
    private SignupOutputBoundary outputBoundary;
    private SignupInteractor interactor;

    @BeforeEach
    void setUp() {
        userDataAccess = mock(UserDataAccess.class);
        outputBoundary = mock(SignupOutputBoundary.class);
        interactor = new SignupInteractor(userDataAccess, outputBoundary);
    }

    @Test
    void testSuccessfulSignup() {
        when(userDataAccess.findUser("newUser")).thenReturn(null);

        SignupInput input = new SignupInput("newUser", "Password123");
        interactor.execute(input);

        verify(userDataAccess).saveUser(any(User.class));
        verify(outputBoundary).prepareSuccessView("Registration successful! You can now log in.");
    }

    @Test
    void testExistingUsername() {
        User existingUser = new User("existingUser", "Password123");
        when(userDataAccess.findUser("existingUser")).thenReturn(existingUser);

        SignupInput input = new SignupInput("existingUser", "Password123");
        interactor.execute(input);

        verify(outputBoundary).prepareFailView("Error: Username already exists.");
    }

    @Test
    void testEmptyFields() {
        SignupInput input = new SignupInput("", "");
        interactor.execute(input);

        verify(outputBoundary).prepareFailView("Error: Please fill in all fields.");
    }

    @Test
    void testOneEmptyField() {
        SignupInput input = new SignupInput("newUser", "");
        interactor.execute(input);

        verify(outputBoundary).prepareFailView("Error: Please fill in all fields.");
    }

    @Test
    void testInvalidPassword() {
        when(userDataAccess.findUser("newUser")).thenReturn(null);

        SignupInput input = new SignupInput("newUser", "invalid");        interactor.execute(input);

        verify(outputBoundary).prepareFailView("Error: Password must be at least 6 characters long, " +
                "include an uppercase letter, a lowercase letter, and a number.");
    }
}
