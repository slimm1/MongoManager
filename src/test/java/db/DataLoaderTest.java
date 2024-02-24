package db;

import java.util.List;
import model.User;
import org.bson.types.ObjectId;
import org.junit.Test;
import static org.junit.Assert.*;

/*
 * @author Martin Ramonda
 */
public class DataLoaderTest {
    
    public DataLoaderTest() {
        MongoConnector.getInstance().tryConnect();
    }

    @Test
    public void testInsertUserIntoDb() {
        System.out.println("Insert null user into db");
        User user = null;
        boolean expResult = false;
        boolean result = DataLoader.getInstance().insertUserIntoDb(user);
        assertEquals(expResult, result);
    }

    @Test
    public void testUpdateUserInDb() {
        System.out.println("update null user");
        User user = null;
        boolean expResult = false;
        boolean result = DataLoader.getInstance().updateUserInDb(user);
        assertEquals(expResult, result);
    }

    @Test
    public void testRemoveUserFromDb() {
        System.out.println("remove null user");
        ObjectId id = null;
        boolean expResult = false;
        boolean result = DataLoader.getInstance().removeUserFromDb(id);
        assertEquals(expResult, result);
    }

    @Test
    public void testListByShow() {
        System.out.println("list By Empty Show");
        String show = "";
        List<User> expResult = null;
        List<User> result = DataLoader.getInstance().listByShow(show);
        assertEquals(expResult, result);
    }

    @Test
    public void testListByUsername() {
        System.out.println("list By Empty Username");
        String username = "";
        List<User> expResult = null;
        List<User> result = DataLoader.getInstance().listByUsername(username);
        assertEquals(expResult, result);
    }
    
}
