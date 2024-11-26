package login.data_access;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import login.entities.User;
import login.entities.Recipe;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class MongoUserDataAccessImpl implements UserDataAccess {
    private final MongoCollection<Document> userCollection;

    public MongoUserDataAccessImpl() {
        String connectionString = "mongodb+srv://ForkYeah:12345@forkyeah.vp6il.mongodb.net/?retryWrites=true&w=majority&appName=ForkYeah";
        MongoClient mongoClient = MongoClients.create(connectionString);
        MongoDatabase database = mongoClient.getDatabase("ForkYeah");
        userCollection = database.getCollection("users");
    }

    @Override
    public User findUser(String username) {
        Document userDoc = userCollection.find(Filters.eq("username", username)).first();
        if (userDoc != null) {
            User user = new User(userDoc.getString("username"), userDoc.getString("password"));
            List<Recipe> recipes = getUserRecipes(username);
            for (Recipe recipe : recipes) {
                user.addRecipe(recipe);
            }
            return user;
        }
        return null;
    }

    @Override
    public void saveUser(User user) {
        Document newUser = new Document("username", user.getUsername())
                .append("password", user.getPassword())
                .append("savedRecipes", new ArrayList<>()); // Initialize with an empty list
        userCollection.insertOne(newUser);
    }

    @Override
    public void saveRecipeForUser(String username, Recipe recipe) {
        userCollection.updateOne(
                Filters.eq("username", username),
                new Document("$addToSet", new Document("savedRecipes", recipe.getRecipeId()))
        );
    }

    @Override
    public List<Recipe> getUserRecipes(String username) {
        List<Recipe> recipes = new ArrayList<>();
        Document userDoc = userCollection.find(Filters.eq("username", username)).first();
        if (userDoc != null) {
            List<String> recipeIds = (List<String>) userDoc.get("savedRecipes");
            for (String recipeId : recipeIds) {
                recipes.add(new Recipe(recipeId, "Recipe Title Placeholder")); // Replace with actual title fetching logic
            }
        }
        return recipes;
    }
}