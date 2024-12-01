package login.use_case;
import login.data_access.UserDataAccess;
import login.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class LoginInteractorTest {
    private UserDataAccess userDataAccess;
    private LoginOutputBoundary outputBoundary;
    private LoginInteractor interactor;

    @BeforeEach
    void setUp() {
        userDataAccess = mock(UserDataAccess.class);
        outputBoundary = mock(LoginOutputBoundary.class);
        interactor = new LoginInteractor(userDataAccess, outputBoundary);
    }

    @Test
    void testEmptyPassword() {
        User user = new User("missing", "");
        when(userDataAccess.findUser("missing")).thenReturn(user);

        LoginInput input = new LoginInput("missing", "");
        interactor.execute(input);

        verify(outputBoundary).prepareFailView("Error: Please input both username and password.");
    }

    @Test
    void testEmptyUsername() {
        LoginInput input = new LoginInput("", "Password123");
        interactor.execute(input);

        verify(outputBoundary).prepareFailView("Error: Please input both username and password.");
    }

    @Test
    void testSuccessfulLogin() {
        User user = new User("testUser", "Password123");
        when(userDataAccess.findUser("testUser")).thenReturn(user);

        LoginInput input = new LoginInput("testUser", "Password123");
        interactor.execute(input);

        verify(outputBoundary).prepareSuccessView("Login successful! Welcome testUser!");
    }

    @Test
    void testNonExistentUser() {
        when(userDataAccess.findUser("nonExistentUser")).thenReturn(null);

        LoginInput input = new LoginInput("nonExistentUser", "Password123");
        interactor.execute(input);

        verify(outputBoundary).prepareFailView("Error: Username does not exist.");
    }

    @Test
    void testIncorrectPassword() {
        User user = new User("testUser", "Password123");
        when(userDataAccess.findUser("testUser")).thenReturn(user);

        LoginInput input = new LoginInput("testUser", "wrongPassword");
        interactor.execute(input);

        verify(outputBoundary).prepareFailView("Error: Incorrect password.");
    }


}