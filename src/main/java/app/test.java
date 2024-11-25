package app;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        // Connect to MongoDB Atlas
        String connectionString = "mongodb+srv://ForkYeah:12345@forkyeah.vp6il.mongodb.net/?retryWrites=true&w=majority&appName=ForkYeah";
        MongoClient mongoClient = MongoClients.create(connectionString);
        MongoDatabase database = mongoClient.getDatabase("RecipeAppDB");
        MongoCollection<Document> userCollection = database.getCollection("users");
        MongoCollection<Document> savedRecipesCollection = database.getCollection("savedRecipes");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Login System!");

        while (true) {
            System.out.println("1. Register\n2. Login\n3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Registration
                    System.out.print("Enter username: ");
                    String regUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String regPassword = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String regEmail = scanner.nextLine();

                    // Check if user already exists
                    Document existingUser = userCollection.find(Filters.eq("username", regUsername)).first();
                    if (existingUser != null) {
                        System.out.println("Error: Username already exists.");
                    } else {
                        // Insert new user
                        Document newUser = new Document("username", regUsername)
                                .append("password", regPassword) // Note: Hash password in real applications
                                .append("email", regEmail)
                                .append("createdAt", new java.util.Date());
                        userCollection.insertOne(newUser);
                        System.out.println("Registration successful!");
                    }
                    break;
                case 2:
                    // Login
                    System.out.print("Enter username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();

                    // Validate user
                    if (validateLogin(userCollection, loginUsername, loginPassword)) {
                        System.out.println("Would you like to save a recipe? (yes/no)");
                        String saveRecipeChoice = scanner.nextLine();
                        if (saveRecipeChoice.equalsIgnoreCase("yes")) {
                            System.out.print("Enter recipe ID to save: ");
                            String recipeId = scanner.nextLine();
                            saveRecipe(savedRecipesCollection, loginUsername, recipeId);
                        }
                    }
                    break;
                case 3:
                    // Exit
                    System.out.println("Exiting...");
                    mongoClient.close();
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static boolean validateLogin(MongoCollection<Document> userCollection, String username, String password) {
        // Find user
        Document user = userCollection.find(Filters.eq("username", username)).first();
        if (user == null) {
            System.out.println("Error: Username does not exist.");
            return false;
        } else if (!user.getString("password").equals(password)) {
            System.out.println("Error: Incorrect password.");
            return false;
        } else {
            System.out.println("Login successful! Welcome " + username + "!");
            return true;
        }
    }

    private static void saveRecipe(MongoCollection<Document> savedRecipesCollection, String username, String recipeId) {
        Document savedRecipe = new Document("username", username)
                .append("recipeId", recipeId)
                .append("savedAt", new java.util.Date());
        savedRecipesCollection.insertOne(savedRecipe);
        System.out.println("entity.Recipe " + recipeId + " saved successfully for user " + username + ".");
    }
}
