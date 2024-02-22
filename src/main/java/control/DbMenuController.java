package control;

import com.mongodb.client.ListDatabasesIterable;
import db.MongoConnector;
import java.util.Scanner;
import org.bson.Document;
import utilities.AppConstants;
import utilities.AppProperties;
import view.DatabaseMenu;
import utilities.InputValidation;

/**
 * @author Martin Ramonda
 */
public class DbMenuController {
    
    private DatabaseMenu dbMenu;
    
    private Scanner input;
    
    private boolean firstConnection;
    
    public static DbMenuController instance;
    
    private DbMenuController(){
        this.input = new Scanner(System.in);
        dbMenu = new DatabaseMenu();
        firstConnection = MongoConnector.getInstance().tryConnect();
    }
    
    public static DbMenuController getInstance(){
        if(instance == null){
            instance = new DbMenuController();
        }
        return instance;
    }
    
    public void launchMenu(){   
        if(firstConnection){
            dbMenu.display();
            if(input == null) input = new Scanner(System.in);
            int answer = InputValidation.checkAnswer(input.nextLine(), 5, input);
            switch(answer){
                case 1 -> {
                    if(!AppProperties.getInstance().getProperty(AppConstants.PROP_DB).isBlank()){
                        CollectionMenuController.getInstance().launchMenu();
                    }
                    else{System.out.println("Debes seleccionar una base de datos para continuar"); launchMenu();}
                }
                case 2 -> {
                    switchDatabase();
                    CollectionMenuController.getInstance().launchMenu();
                }
                case 3 -> {
                    if(!AppProperties.getInstance().getProperty(AppConstants.PROP_DB).isBlank()){
                        removeDatabase();
                        launchMenu();
                    }
                    else{System.out.println("Debes seleccionar una base de datos para continuar"); launchMenu();}
                }
                case 4 -> {
                    createDatabase();
                    CollectionMenuController.getInstance().launchMenu();
                }
                case 5 ->{                     
                    if(AppProperties.getInstance().getProperty(AppConstants.PROP_DB).isBlank()){
                        AppProperties.getInstance().setProperty(AppConstants.PROP_DB, AppConstants.DEFAULT_DBNAME);
                    }
                    System.out.println("Hasta pronto");}
            }
        }
    }
    
    private void switchDatabase(){
        int count=1;
        ListDatabasesIterable<Document> databases = MongoConnector.getInstance().listDatabases();
        System.out.println("Bases de datos disponibles: ");
        for (Document database : databases) {
            System.out.println("\t" + count + ". " +database.getString("name"));
            count++;
        }
        System.out.println("Introduzca el nombre de una de las bd disponibles:");
        String answer =input.nextLine();
        while(!InputValidation.validateDb(answer, databases) || answer.equalsIgnoreCase(AppProperties.getInstance().getProperty(AppConstants.PROP_DB))){
            if(!InputValidation.validateDb(answer, databases)) System.out.println("El nombre introducido no coincide con ningun registro");
            else System.out.println("Ya estás conectado a esa base de datos");
            answer =input.nextLine();
        }
        AppProperties.getInstance().setProperty(AppConstants.PROP_DB, answer);
        MongoConnector.getInstance().tryConnect();
    }
    
    private void removeDatabase(){
        System.out.println("Estas seguro de que quieres borrar la base de datos con nombre --> " + AppProperties.getInstance().getProperty(AppConstants.PROP_DB) + "? (si/no)");
        String answer = input.nextLine();
        while(!(answer.equals("si") || answer.equals("no"))){
            System.out.println("Por favor, introduzca si o no");
            answer = input.nextLine();
        }
        if(!InputValidation.validateDeleteDb(AppProperties.getInstance().getProperty(AppConstants.PROP_DB)) && answer.equals("si")){
            System.out.println("No tienes permisos para eliminar esta base de datos");
        }
        else if(InputValidation.validateDeleteDb(AppProperties.getInstance().getProperty(AppConstants.PROP_DB)) && answer.equals("si")){
            MongoConnector.getInstance().dropDatabase(AppProperties.getInstance().getProperty(AppConstants.PROP_DB));
            AppProperties.getInstance().setProperty(AppConstants.PROP_DB, "");
            System.out.println("Base de datos eliminada con exito");
        }
    }   
    
    private void createDatabase(){
        System.out.println("Introduce el nombre de la nueva base de datos: ");
        String answer = input.nextLine();
        ListDatabasesIterable<Document> databases = MongoConnector.getInstance().listDatabases();
        while(InputValidation.validateDb(answer, databases)){
            System.out.println("Ya existe una base de datos con este nombre, por favor, introduce otro nombre:");
            answer = input.nextLine();
        }
        System.out.println("Base de datos registrada con éxito"); 
        AppProperties.getInstance().setProperty(AppConstants.PROP_DB, answer);
        MongoConnector.getInstance().tryConnect();
    }
}
