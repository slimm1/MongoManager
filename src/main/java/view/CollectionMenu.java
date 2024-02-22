package view;

import utilities.AppConstants;
import utilities.AppProperties;

/**
 * @author Martin Ramonda
 */
public class CollectionMenu {
    
    public void display(){
        System.out.println("***** " + AppProperties.getInstance().getProperty(AppConstants.PROP_DB) + " *****");
        System.out.println("1. registrar nuevo usuario");
        System.out.println("2. registrar nuevo show");
        System.out.println("3. eilminar un usuario");
        System.out.println("4. eliminar un show");
        System.out.println("5. actualizar un usuario");
        System.out.println("6. actualizar un show");
        System.out.println("7. buscar usuarios");
        System.out.println("8. buscar shows");
        System.out.println("9. listar usuarios");
        System.out.println("10. listar shows");
        System.out.println("11. salir");
    }
}
