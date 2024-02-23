package dao;

import java.util.List;
import org.bson.types.ObjectId;

/**
 * @author Martin Ramonda
 */
public interface Dao<T> {
    public boolean add(T objeto);
    public boolean remove(ObjectId id);
    public boolean update(ObjectId id);
    public List<T> list();
    
}
