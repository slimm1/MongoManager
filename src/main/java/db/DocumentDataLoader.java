package db;

import com.mongodb.client.MongoCollection;
import java.util.List;
import model.User;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * @author Martin Ramonda
 */
public class DocumentDataLoader {
    
    private static DocumentDataLoader instance;
    
    private MongoCollection<Document> documentCollection;
    
    private DocumentDataLoader(){
        documentCollection = MongoConnector.getInstance().getDatabase().getCollection("user");
    }
    
    public static DocumentDataLoader getInstance(){
        if(instance==null){
            instance = new DocumentDataLoader();
        }
        return instance;
    }
    
    public boolean addUser(User user){
    
    }
    
    public boolean removeUser(ObjectId id){
        
    }
    
    public boolean updateUser(User user){
    
    }
    
    public List<User> listAll(){
    
    }
    
    public List<User> listByShow(String show){
    
    }
    
    public List<User> listByName(String username){
    
    }
    
    public List<User> listByAge(int age){
    
    }
}
