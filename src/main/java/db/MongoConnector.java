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
    
    // clase cliente de mongo. se usa para instanciar la base de datos.
    private MongoClient client;
    
    // clase de base de datos de mongo. se usa para acceder a las colecciones
    private MongoDatabase db;
    
    private AppProperties myProps;
    
    // coded mongo. "serializa" las clases para escribirlas en la base de datos.
    private CodecRegistry pojoCodecRegistry;
    
    private MongoConnector(){
        // inicia el codec pojo 
        initPojo();
        myProps = AppProperties.getInstance();
    }
    
    public static MongoConnector getInstance(){
        if(instance == null){
            instance = new MongoConnector();
        }
        return instance;
    }
    
    // calcula el conectionstring a través del host y el puerto 
    private String getConnectionString(){
        return AppConstants.CONNECTION_STRING + myProps.getProperty(AppConstants.PROP_HOST)+":" + myProps.getProperty(AppConstants.PROP_PORT);
    }
    
    /***
     * prueba la conexion a la base de datos mongo. La clase connectionString se crea a partir de la cadena de conexion.
     * MongoClientSettings setermina unos ajustes para iniciar el cliente, que intenta conectarse a la base de datos desde el metodo
     * tryConnectConnection(MongoClient)
     * @return 
     */
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
    
    // Instancia la base de datos con el codec pojo especificado en el constructor de esta clase. Además crea la coleccion almacenada en props
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
    
    // inicia el codecregistry a traves del codec provider. estas instanciaciones son las que permiten que las clases se escriban automáticamente
    //en la base de datos.
    public void initPojo(){
    PojoCodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        pojoCodecRegistry = CodecRegistries.fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(), CodecRegistries.fromProviders(pojoCodecProvider));
    }
}
