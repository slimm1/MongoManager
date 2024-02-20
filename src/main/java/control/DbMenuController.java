package control;

import db.MongoConnector;
import java.util.Scanner;
import view.DatabaseMenu;
import utilities.InputValidation;

/**
 * @author Martin Ramonda
 */
public class DbMenuController {
    
    private DatabaseMenu dbMenu;
    
    private Scanner input;
    
    public static DbMenuController instance;
    
    private DbMenuController(){
        this.input = new Scanner(System.in);
        dbMenu = new DatabaseMenu();
    }
    
    public static DbMenuController getInstance(){
        if(instance == null){
            instance = new DbMenuController();
        }
        return instance;
    }
    
    public void launchMenu(){
        //checks if there is connection
        MongoConnector.getInstance();
        dbMenu.display();
        if(input == null) input = new Scanner(System.in);
        int answer = InputValidation.checkAnswer(input.nextLine(), 4, input);
        switch(answer){
            case 1 -> {
                CollectionMenuController.getInstance().launchMenu();
            }
            case 2 -> {
                
            }
            case 3 -> {
                
            }
            case 4 -> System.out.println("Hasta pronto");
        }
    }
    
    public static void main(String[] args) {
        DbMenuController.getInstance().launchMenu();
    }
}
