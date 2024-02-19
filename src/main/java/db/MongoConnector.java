package db;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;
import process.DotsAnimation;
import utilities.AppConstants;
import utilities.AppProperties;

public class MongoConnector {
    
    private static MongoConnector instance;
    
    private MongoDatabase db;
    
    private AppProperties myProps;
    
    private MongoConnector(){
        myProps = new AppProperties();
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
        try(MongoClient client = MongoClients.create(settings)){
            System.out.println("Base de datos seleccionada --> " + myProps.getProperty(AppConstants.PROP_DB));
            System.out.println("Bases de datos disponibles: ");
            t.start();
            listDatabases(client);
            t.interrupt();
        }
        catch(Exception e){
            System.out.println("Error al abrir la conexión :(");
            System.out.println("Cadena de conexion --> " + getConnectionString());
            t.interrupt();
        }
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
    
    private void listDatabases(MongoClient client){
        int count=1;
        MongoIterable<String> dbs = client.listDatabaseNames();
        for(String db:dbs){
            System.out.println(count + ". " + db);
        }
    }
    
    public static void main(String[] args) {
        MongoConnector.getInstance();
    }
    
}
