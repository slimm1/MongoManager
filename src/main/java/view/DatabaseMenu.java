package view;

import utilities.AppConstants;
import utilities.AppProperties;

/**
 * @author Martin Ramonda
 */
public class DatabaseMenu {
    
    public DatabaseMenu(){
    }
    
    public void display(){
        if(!AppProperties.getInstance().getProperty(AppConstants.PROP_DB).isBlank()){
            System.out.println("Ahora estas conectado a --> " + AppProperties.getInstance().getProperty(AppConstants.PROP_DB));            
        }
        else{
            System.out.println("No estas conectado a ninguna base de datos");
        }
        System.out.println("Qué deseas hacer?");
        System.out.println("1. operar");
        System.out.println("2. cambiar de base de datos");
        System.out.println("3. eliminar esta base de datos");
        System.out.println("4. crear base de datos");
        System.out.println("5. SALIR");
    }
}
