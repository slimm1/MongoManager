package dao;

import db.UserDataLoader;
import java.util.List;
import model.User;
import org.bson.types.ObjectId;

/**
 * @author Martin Ramonda
 */
public class UserDao implements Dao<User>{

    @Override
    public boolean add(User user) {
        return UserDataLoader.getInstance().insertUserIntoDb(user);
    }

    @Override
    public boolean remove(ObjectId id) {
        return UserDataLoader.getInstance().removeUserFromDb(id);
    }

    @Override
    public boolean update(User user) {
        return UserDataLoader.getInstance().updateUserInDb(user);
    }

    @Override
    public List<User> listAll() {
        return UserDataLoader.getInstance().getAllUsers();
    }
    
    public List<User> listByShow(String show){
        return UserDataLoader.getInstance().listByShow(show);
    }
    
    public List<User> listByName(String username){
        return UserDataLoader.getInstance().listByUsername(username);
    }

    public List<User> listByAge(int age){
        return UserDataLoader.getInstance().listByAge(age);
    }
}
