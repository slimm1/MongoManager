package dao;

import db.DataLoader;
import java.util.List;
import model.User;
import org.bson.types.ObjectId;

/**
 * @author Martin Ramonda
 */
public class UserDao implements Dao<User>{

    @Override
    public boolean add(User user) {
        return DataLoader.getInstance().insertUserIntoDb(user);
    }

    @Override
    public boolean remove(ObjectId id) {
        return DataLoader.getInstance().removeUserFromDb(id);
    }

    @Override
    public boolean update(User user) {
        return DataLoader.getInstance().updateUserInDb(user);
    }

    @Override
    public List<User> listAll() {
        return DataLoader.getInstance().getAllUsers();
    }
    
    public List<User> listByShow(String show){
        return DataLoader.getInstance().listByShow(show);
    }
    
    public List<User> listByName(String username){
        return DataLoader.getInstance().listByUsername(username);
    }

    public List<User> listByAge(int age){
        return DataLoader.getInstance().listByAge(age);
    }
}
