package login.data_access;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import entity.Recipe;
import login.entities.User;

import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the UserDataAccess interface with MongoDB.
 * This class provides methods to interact with user data stored in a MongoDB database.
 * It includes functionalities for creating, reading, updating, and deleting user records.
 */
public class MongoUserDataAccessImpl implements UserDataAccess {
    private static final String RECIPE = "savedRecipes";
    private static final String USERNAME = "username";
    private final MongoCollection<Document> userCollection;

    /**
     * Constructs a MongoUserDataAccessImpl instance and initializes the
     * MongoDB and user collection.
     */
    public MongoUserDataAccessImpl() {
        final String connectionString = "mongodb+srv://ForkYeah:12345@forkyeah"
                + ".vp6il.mongodb.net/?retryWrites=true&w=majority&appName=ForkYeah";
        final MongoClient mongoClient = MongoClients.create(connectionString);
        final MongoDatabase database = mongoClient.getDatabase("ForkYeah");
        userCollection = database.getCollection("users");
    }

    /**
     * Finds a user by their username.
     *
     * @param username the username of the user to find
     * @return the User if found, or null if not found
     */
    @Override
    public User findUser(String username) {
        final Document userDoc = userCollection.find(Filters.eq(USERNAME, username)).first();
        if (userDoc != null) {
            return new User(userDoc.getString(USERNAME), userDoc.getString("password"));
        }
        return null;
    }

    /**
     * Saves a new user to the database.
     *
     * @param user the User object to save
     */
    @Override
    public void saveUser(User user) {
        final Document userDoc = new Document(USERNAME, user.getUsername())
                .append("password", user.getPassword())
                .append(RECIPE, getUserRecipes(user.getUsername()));
        userCollection.insertOne(userDoc);
    }

    /**
     * Saves a recipe for a specific user.
     *
     * @param username the username of the user
     * @param recipe the Recipe object to save
     */
    @Override
    public void saveRecipeForUser(String username, Recipe recipe) {
        userCollection.updateOne(Filters.eq(USERNAME, username),
                new Document("$push", new Document(RECIPE, Integer.toString(recipe.getId()))));
    }

    /**
     * Retrieves a list of recipes saved by a specific user.
     *
     * @param username the username of the user
     * @return a list of Recipe objects saved by the user
     */
    @Override
    public List<Recipe> getUserRecipes(String username) {
        final List<Recipe> recipes = new ArrayList<>();
        final Document userDoc = userCollection.find(Filters.eq(USERNAME, username)).first();

        if (userDoc != null) {
            final List<String> recipeIds = (List<String>) userDoc.get(RECIPE);
            for (String recipeId : recipeIds) {
                recipes.add(new Recipe(recipeId));
            }
        }
        return recipes;
    }
}
