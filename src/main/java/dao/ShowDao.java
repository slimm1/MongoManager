package dao;

import db.DataLoader;
import java.util.List;
import model.Show;
import org.bson.types.ObjectId;

/**
 * @author Martin Ramonda
 */
public class ShowDao implements Dao<Show>{

    @Override
    public boolean add(Show show) {
        return DataLoader.getInstance().insertShowIntoDb(show);
    }

    @Override
    public boolean remove(ObjectId id) {
        return DataLoader.getInstance().removeShowFromDb(id);
    }

    @Override
    public boolean update(Show show) {
        return DataLoader.getInstance().updateShowInDb(show);
    }

    @Override
    public List<Show> list() {
        return DataLoader.getInstance().getAllShows();
    }
    
}
