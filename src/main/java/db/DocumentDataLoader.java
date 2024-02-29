package db;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import java.util.ArrayList;
import java.util.List;
import model.User;
import org.bson.Document;
import org.bson.conversions.Bson;
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
        try{
            Document userDoc = new Document();
            userDoc.append("_id", new ObjectId())
                    .append("username", user.getUsername())
                    .append("edad", user.getEdad())
                    .append("shows", user.getShows());
            documentCollection.insertOne(userDoc);
            return true;
        }
        catch(Exception e){
            return false;
        }

    }
    
    public boolean removeUser(ObjectId id){
        try{
            Document query = new Document("_id",id);
            documentCollection.findOneAndDelete(query);
            return true;
        }
        catch(Exception e){
            return false;
        }      
    }

    public boolean updateUser(User user){
        try{
            Document query = new Document("_id",user.getId());
            Document userDoc = new Document();
            userDoc.append("_id", new ObjectId())
                    .append("username", user.getUsername())
                    .append("edad", user.getEdad())
                    .append("shows", user.getShows());
            documentCollection.findOneAndReplace(query, userDoc);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    
    public List<User> listAll(){
        return documentCollection.find().into(new ArrayList());
    }
    
    public List<User> listByShow(String show){
        Bson filter = Filters.eq("shows.titulo", show);
        return documentCollection.find(filter).into(new ArrayList());
    }
}
