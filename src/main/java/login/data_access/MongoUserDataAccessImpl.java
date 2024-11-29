package login.data_access;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import login.entities.User;
import entity.Recipe;
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
            return new User(userDoc.getString("username"), userDoc.getString("password"));
//            User user = new User(userDoc.getString("username"), userDoc.getString("password"));
//            List<Recipe> recipes = getUserRecipes(username);
//            for (Recipe recipe : recipes) {
//                user.addRecipe(recipe);
//            }
//            return user;
        }
        return null;
    }

    @Override
    public void saveUser(User user) {
        Document userDoc = new Document("username", user.getUsername())
                .append("password", user.getPassword())
                .append("savedRecipes", getUserRecipes(user.getUsername()));
        userCollection.insertOne(userDoc);
        //user.getSavedRecipes()
    }

    @Override
    public void saveRecipeForUser(String username, Recipe recipe) {
        userCollection.updateOne(Filters.eq("username", username),
                new Document("$push", new Document("savedRecipes", Integer.toString(recipe.getId()))));
    }

    @Override
    public List<Recipe> getUserRecipes(String username) {
        System.out.println("Searching for user: " + username); // Debugging output

        List<Recipe> recipes = new ArrayList<>();
        Document userDoc = userCollection.find(Filters.eq("username", username)).first();
        System.out.println(userDoc);

        if (userDoc != null) {
            List<String> recipeIds = (List<String>) userDoc.get("savedRecipes");
            System.out.println(recipeIds);
            for (String recipeId : recipeIds) {
                recipes.add(new Recipe(recipeId)); // Replace with actual title fetching logic
            }
        }
        //System.out.println(recipes.get(0).getId());
        System.out.println(recipes.size() + "here");
        return recipes;
    }

//    @Override
//    public List<Recipe> getUserRecipes(String username) {
//        Document query = new Document("username", username);
//        Document userDocument = (Document) userCollection.find(query).first();
//        List<Recipe> recipeList = new ArrayList<>();
//
//        if (userDocument != null) {
//            // Assuming the recipes are stored in a field called "recipes"
//            List<Document> recipes = (List<Document>) userDocument.get("recipes");
//
//            if (recipes != null) {
//                for (Document recipeDoc : recipes) {
//                    // Convert Document to Recipe object (assuming a constructor or method exists)
//                    Recipe recipe = new Recipe(recipeDoc.getString("id"));
//                    recipeList.add(recipe);
//                }
//            }
//        }
//        return recipeList;
//    }
}