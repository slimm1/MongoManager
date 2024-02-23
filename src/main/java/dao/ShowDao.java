package dao;

import java.util.List;
import model.Show;
import org.bson.types.ObjectId;

/**
 * @author Martin Ramonda
 */
public class ShowDao implements Dao<Show>{

    @Override
    public boolean add(Show objeto) {
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
    public List<Show> list() {
        return null;
    }
    
}
