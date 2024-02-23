package db;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import java.util.ArrayList;
import java.util.List;
import model.Show;
import model.User;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 * @author Martin Ramonda
 */
public class DataLoader {
    
    private MongoCollection<User> userCollection;
    
    private static DataLoader instance;
    
    private DataLoader(){
        userCollection = MongoConnector.getInstance().getDatabase().getCollection("user", User.class);
    }
    
    public boolean insertUserIntoDb(User user){
        try{
            userCollection.insertOne(user);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    
    public boolean updateUserInDb(User user){
        try{
            Document doc = new Document("_id", user.getId());
            userCollection.findOneAndReplace(doc, user);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    
    public boolean removeUserFromDb(ObjectId id){
        try{
            Document doc = new Document("_id", id);
            userCollection.findOneAndDelete(doc);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    
    public List<User> listByShow(String show){
        Bson filter = Filters.eq("shows.titulo", show);
        return userCollection.find(filter).into(new ArrayList());
    }
    
    public List<User> listByUsername(String username){
        Bson filter = Filters.eq("username", username);
        return userCollection.find(filter).into(new ArrayList());
    }
    
    public List<User> listByAge(int age){
        Bson filter = Filters.eq("edad", age);
        return userCollection.find(filter).into(new ArrayList());
    }
    
    public List<User> getAllUsers(){
        return userCollection.find().into(new ArrayList());
    }
    
    public static DataLoader getInstance(){
        if(instance==null){
            instance = new DataLoader();
        }
        return instance;
    }
}
