package db;

import com.mongodb.client.MongoCollection;
import java.util.ArrayList;
import java.util.List;
import model.Show;
import model.User;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * @author Martin Ramonda
 */
public class DataLoader {
    
    private MongoCollection<User> userCollection;
    
    private MongoCollection<Show> showsCollection;
    
    private static DataLoader instance;
    
    private DataLoader(){
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
    
    public boolean removeShowFromDb(ObjectId id){
        try{
            Document doc = new Document("_id",id);
            showsCollection.findOneAndDelete(doc);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    
    public List<User> getAllUsers(){
        List<User> lista = new ArrayList();
        for(User u: userCollection.find()){
            lista.add(u);
        }
        return lista;
    }
    
    public List<Show> getAllShows(){
        List<Show> lista = new ArrayList();
        for(Show s:showsCollection.find()){
            lista.add(s);
        }
        return lista;
    }
    
    public static DataLoader getInstance(){
        if(instance==null){
            instance = new DataLoader();
        }
        return instance;
    }
}
