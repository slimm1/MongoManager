package db;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.ListDatabasesIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import process.DotsAnimation;
import utilities.AppConstants;
import utilities.AppProperties;

public class MongoConnector {
    
    private static MongoConnector instance;
    
    private MongoClient client;
    
    private MongoDatabase db;
    
    private AppProperties myProps;
    
    private CodecRegistry pojoCodecRegistry;
    
    private MongoConnector(){
        initPojo();
        myProps = AppProperties.getInstance();
    }
    
    public static MongoConnector getInstance(){
        if(instance == null){
            instance = new MongoConnector();
        }
        return instance;
    }
    
    private String getConnectionString(){
        return AppConstants.CONNECTION_STRING + myProps.getProperty(AppConstants.PROP_HOST)+":" + myProps.getProperty(AppConstants.PROP_PORT);
    }
    
    public boolean tryConnect(){
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
            System.out.println("--------------------------------");
            System.out.println("|     Conexion establecida     |");
            System.out.println("--------------------------------");
            return true;
        }
        catch(MongoException m){
            System.out.println("\n***** Error al abrir la conexion *****");
            System.out.println("----------------------------------------");
            System.out.println("Cadena de conexion --> " + getConnectionString());
            System.out.println("REVISAR QUE EL PUERTO " + myProps.getProperty(AppConstants.PROP_PORT) + " ESTA OPERATIVO y reinicia la app");
            t.interrupt();
            return false;
        } catch (InterruptedException ex) {
            Logger.getLogger(MongoConnector.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    private void tryConnectCollection(MongoClient client) throws MongoException{
        db = client.getDatabase(myProps.getProperty(AppConstants.PROP_DB)).withCodecRegistry(pojoCodecRegistry);
        db.createCollection(myProps.getProperty(AppConstants.PROP_COLLECTION));
        System.out.println("Colecciones agregadas en " + myProps.getProperty(AppConstants.PROP_DB));
    }
    
    public boolean dropDatabase(String dbName){
        try{
            db.drop();
            System.out.println();
            return true;
        }
        catch(Exception e){
            return false;
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
    
    public ListDatabasesIterable<Document> listDatabases(){
        ListDatabasesIterable<Document> databases = client.listDatabases();
        return databases;
    }
    
    public void initPojo(){
    PojoCodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        pojoCodecRegistry = CodecRegistries.fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(), CodecRegistries.fromProviders(pojoCodecProvider));
    }
}
