package dao;

import db.PojoDataLoader;
import java.util.List;
import java.util.Set;
import model.Show;
import model.User;
import org.bson.types.ObjectId;

/**
 * @author Martin Ramonda
 */
public class UserDao implements Dao<User>{

    @Override
    public boolean add(User user) {
        return PojoDataLoader.getInstance().insertUserIntoDb(user);
    }

    @Override
    public boolean remove(ObjectId id) {
        return PojoDataLoader.getInstance().removeUserFromDb(id);
    }

    @Override
    public boolean update(User user) {
        return PojoDataLoader.getInstance().updateUserInDb(user);
    }

    @Override
    public List<User> listAll() {
        return PojoDataLoader.getInstance().getAllUsers();
    }
    
    public List<User> listByShow(String show){
        return PojoDataLoader.getInstance().listByShow(show);
    }
    
    public List<User> listByName(String username){
        return PojoDataLoader.getInstance().listByUsername(username);
    }

    public List<User> listByAge(int age){
        return PojoDataLoader.getInstance().listByAge(age);
    }
    
    public Set<Show> listAllShows(){
        return PojoDataLoader.getInstance().getAllShow();
    }
}
