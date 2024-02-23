package utilities;

import com.mongodb.client.ListDatabasesIterable;
import java.util.Scanner;
import org.bson.Document;

/**
 * @author Martin Ramonda
 */
public class InputValidation {
    
    public static double validatePrice(String precio, Scanner input){
        while(!precio.matches("^[0-9]*\\.?[0-9]+$")){
            System.out.println("Valor incorrecto para precio, introducelo de nuevo:");
            precio = input.nextLine();
        }
        return Double.parseDouble(precio);
    }
    
    public static int validateAge(String age, Scanner input){
        while(!age.matches("\\d+$")){
            System.out.println("Valor incorrecto para edad, introducelo de nuevo:");
            age = input.nextLine();
        }
        return Integer.parseInt(age);
    }
    
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
