package login.data_access;


import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import login.entities.User;
import org.bson.Document;

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
        }
        return null;
    }

    @Override
    public void saveUser(User user) {
        Document newUser = new Document("username", user.getUsername())
                .append("password", user.getPassword());
        userCollection.insertOne(newUser);
    }
}