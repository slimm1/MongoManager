package view;

import utilities.AppConstants;
import utilities.AppProperties;

/**
 * @author Martin Ramonda
 */
public class CollectionMenu {
    
    public void display(){
        System.out.println("***** " + AppProperties.getInstance().getProperty(AppConstants.PROP_DB) + " *****");
        System.out.println("1. Listar datos");
        System.out.println("2. Buscar por clave");
        System.out.println("3. Agregar registro");
        System.out.println("4. Eliminar registro");
        System.out.println("5. Actualizar registro");
        System.out.println("6. Volver");
    }
    
}
