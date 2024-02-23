package db;

import com.mongodb.client.MongoCollection;
import java.util.ArrayList;
import java.util.List;
import model.User;
import org.bson.Document;
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
    
    public List<User> getAllUsers(){
        List<User> lista = new ArrayList();
        return userCollection.find().into(lista);
    }
    
    public static DataLoader getInstance(){
        if(instance==null){
            instance = new DataLoader();
        }
        return instance;
    }
}
