package utilities;

import java.io.File;
import java.io.IOException;

/**
 * @author Martin Ramonda
 */
public class FileManager {
    
    public static void createFile(String path, String fileName) {
        File file = new File(path+fileName);
        try {
            createPath(path);
            if(file.createNewFile()) {
                System.out.println("Archivo creado exitosamente --> " + fileName);
            }
        } catch (IOException e) {
            System.err.println("Error al crear el archivo: " + e.getMessage());
        }
        System.out.println("Archivo de configuración listo");
    }
    
    private static void createPath(String path){
        File dirs = new File(path);
        if(dirs.mkdirs()){
            System.out.println("Creando directorios para --> " + path);
        }
    }
}
