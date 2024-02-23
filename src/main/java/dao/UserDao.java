package dao;

import java.util.List;
import model.User;
import org.bson.types.ObjectId;

/**
 * @author Martin Ramonda
 */
public class UserDao implements Dao<User>{

    @Override
    public boolean add(User objeto) {
        return false;
    }

    @Override
    public boolean remove(ObjectId id) {
        return false;
    }

    @Override
    public boolean update(ObjectId id) {
        return false;
    }

    @Override
    public List<User> list() {
        return null;
    }
    
}
