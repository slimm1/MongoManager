package db;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import java.util.logging.Level;
import java.util.logging.Logger;
import process.DotsAnimation;
import utilities.AppConstants;
import utilities.AppProperties;

public class MongoConnector {
    
    private static MongoConnector instance;
    
    private MongoClient client;
    
    private MongoDatabase db;
    
    private AppProperties myProps;
    
    private MongoConnector(){
        myProps = AppProperties.getInstance();
        tryConnect();
    }
    
    public static MongoConnector getInstance(){
        if(instance == null){
            instance = new MongoConnector();
        }
        return instance;
    }
    
    private String getConnectionString(){
        return AppConstants.CONNECTION_STRING+myProps.getProperty(AppConstants.PROP_HOST)+":"+myProps.getProperty(AppConstants.PROP_PORT);
    }
    
    private void tryConnect(){
        Thread t = new DotsAnimation();
        ConnectionString connString = new ConnectionString(getConnectionString());
        MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(connString)
            .build();
        try{
            client = MongoClients.create(settings);
            System.out.println("Probando conexion");
            t.start();
            Thread.sleep(1500);
            tryConnectCollection(client);
            t.interrupt();
            System.out.println("***** Conexion establecida *****");
        }
        catch(MongoException m){
            System.out.println("\n***** Error al abrir la conexion *****");
            System.out.println("Cadena de conexion --> " + getConnectionString());
            System.out.println("REVISAR QUE EL PUERTO " + myProps.getProperty(AppConstants.PROP_PORT) + " ESTA OPERATIVO");
            t.interrupt();
        } catch (InterruptedException ex) {
            Logger.getLogger(MongoConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void tryConnectCollection(MongoClient client) throws MongoException{
        db = client.getDatabase(myProps.getProperty(AppConstants.PROP_DB));
        db.createCollection(myProps.getProperty(AppConstants.PROP_COLLECTION));
    }
    
    public MongoDatabase getDatabase(){
        try{
            return db;
        }
        catch(Exception e){
            System.out.println("Parece que " + e.getMessage());
            return null;
        }
    }
    
    public int listDatabases(){
        int count=1;
        System.out.println("Bases de datos disponibles: ");
        MongoIterable<String> dbs = client.listDatabaseNames();
        for(String db:dbs){
            System.out.println(count + ". " + db);
            count++;
        }
        return count;
    }
}
