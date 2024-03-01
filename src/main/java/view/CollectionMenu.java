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
        System.out.println("2. añadir show a usuario");
        System.out.println("3. eliminar un usuario");
        System.out.println("4. actualizar nombre de usuario");
        System.out.println("5. actualizar edad de usuario");
        System.out.println("6. listar todos los usuarios");
        System.out.println("7. listar usuarios por show");
        System.out.println("8. buscar usuarios por nombre");
        System.out.println("9. buscar usuarios por edad");
        System.out.println("10. listar todos los shows");
        System.out.println("11. volver");
    }
}
