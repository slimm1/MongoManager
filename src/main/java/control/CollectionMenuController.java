package control;

import java.util.Scanner;
import utilities.InputValidation;
import view.CollectionMenu;

/**
 *
 * @author Martin Ramonda
 */
public class CollectionMenuController {
    
    private CollectionMenu colMenu;
    
    private Scanner input;
    
    private static CollectionMenuController instance;
    
    private CollectionMenuController(){
        this.colMenu = new CollectionMenu();
        this.input = new Scanner(System.in);
    }
    
    public static CollectionMenuController getInstance(){
        if(instance == null){
            instance = new CollectionMenuController();
        }
        return instance;
    }
    
    public void launchMenu(){
        colMenu.display();
        if(input == null) input = new Scanner(System.in);
        int answer = InputValidation.checkAnswer(input.nextLine(), 6, input);
        switch(answer){
            case 1 -> {
                
            }
            case 2 -> {
                
            }
            case 3 -> {
                
            }
            case 4 -> {
            
            }
            case 5 -> {
            
            }
            case 6 -> DbMenuController.getInstance().launchMenu();
        }
    }
}
