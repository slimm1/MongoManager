package utilities;

import java.util.Scanner;

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
}
