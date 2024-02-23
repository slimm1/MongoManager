package db;

import com.mongodb.client.MongoCollection;
import model.Show;
import model.User;
import org.bson.Document;

/**
 * @author Martin Ramonda
 */
public class DataLoader {
    
    private MongoCollection<User> userCollection;
    
    private MongoCollection<Show> showsCollection;
    
    public DataLoader(){
        userCollection = MongoConnector.getInstance().getDatabase().getCollection("user", User.class);
        showsCollection = MongoConnector.getInstance().getDatabase().getCollection("show", Show.class);
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
    
    public boolean insertShowIntoDb(Show s){
        try{
            showsCollection.insertOne(s);
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
    
    public boolean updateShowInDb(Show show){
        try{
            Document doc = new Document("_id", show.getId());
            showsCollection.findOneAndReplace(doc, show);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
}
