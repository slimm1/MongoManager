package utilities;

import com.mongodb.client.ListDatabasesIterable;
import java.util.Scanner;
import org.bson.Document;

/**
 *
 * @author Martin Ramonda
 */
public class InputValidation {
    public static int checkAnswer(String answer, int limit, Scanner input){
        //digitos positivos menores de 4
        while(!answer.matches("\\d+$") || Integer.parseInt(answer)>limit){
            System.out.println("Por favor, Introduce una opcion valida:");
            answer = input.nextLine();
        }
        return Integer.parseInt(answer);
    }
    
    public static boolean validateDb(String answer, ListDatabasesIterable<Document> databases){
        for(Document d: databases){
            if(d.getString("name").equalsIgnoreCase(answer)){
                return true;
            }
        }
        return false;
    }
    
    public static boolean validateDeleteDb(String db){
        return switch (db) {
            case "admin" -> false;
            case "config" -> false;
            case "local" -> false;
            default -> true;
        };
    }
}
