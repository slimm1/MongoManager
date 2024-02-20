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
        System.out.println("Ahora estas conectado a --> " + AppProperties.getInstance().getProperty(AppConstants.PROP_DB));
        System.out.println("Qué deseas hacer?");
        System.out.println("1. operar");
        System.out.println("2. cambiar de base de datos");
        System.out.println("3. eliminar esta base de datos");
        System.out.println("4. SALIR");
    }
}
