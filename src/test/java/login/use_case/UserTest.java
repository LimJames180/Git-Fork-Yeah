package login.use_case;

import login.entities.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testUserCreation() {
        User user = new User("testUser", "password123");
        assertEquals("testUser", user.getUsername(), "Username match input");
        assertEquals("password123", user.getPassword(), "Password match input");
    }

    @Test
    void testGetUsername() {
        User user = new User("anotherUser", "securePassword");
        assertEquals("anotherUser", user.getUsername(), "Getter return correct username");
    }

    @Test
    void testGetPassword() {
        User user = new User("anotherUser", "securePassword");
        assertEquals("securePassword", user.getPassword(), "Getter return correct password");
    }
}
