package control;

import dao.UserDao;
import java.util.List;
import java.util.Scanner;
import model.Show;
import model.User;
import utilities.InputValidation;
import view.CollectionMenu;

/**
 * @author Martin Ramonda
 */
public class CollectionMenuController {
    
    private CollectionMenu colMenu;
    
    private Scanner input;
    
    private UserDao userDao;
    
    private static CollectionMenuController instance;
    
    private CollectionMenuController(){
        this.colMenu = new CollectionMenu();
        this.input = new Scanner(System.in);
        userDao = new UserDao();
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
                addNewUser();
                launchMenu();
            }
            case 2 -> {
                User u = pickUserByName();
                addShowToUser(u);
                launchMenu();
            }
            case 3 -> {
                User u = pickUserByName();
                removeUser(u);
                launchMenu();
            }
            case 4 -> {
                User u = pickUserByName();
                updateName(u);
                launchMenu();
            }
            case 5 -> {
                User u = pickUserByName();
                updateAge(u);
                launchMenu();
            }
            case 6 -> {
                listAllUsers();
                System.out.println("\n");
                launchMenu();
            }
            case 7 -> {
                
            }
            case 8 -> {
                
            }
            case 9 -> {
            
            }
            case 10 -> DbMenuController.getInstance().launchMenu();
        }
    }
    
    private void addNewUser(){
        User newUser = new User();
        System.out.println("Introduce nombre de usuario: ");
        String uname = input.nextLine();
        newUser.setUsername(uname);
        System.out.println("Introduce edad de usuario: ");
        int edad = InputValidation.validateAge(input.nextLine(), input);
        newUser.setEdad(edad);
        if(userDao.add(newUser)) System.out.println("Usuario añadido con exito");
        else System.out.println("Error al añadir nuevo usuario");
    }
    
    private void listAllUsers(){
        System.out.println("Usuarios registrados:");
        for(User u : userDao.list()){
            System.out.println("\t"+u.getUsername());
        }
    }
    
    private User pickUserByName(){
        listAllUsers();
        System.out.println("Elige un usuario de la lista introduciendo su nombre: ");
        String username = input.nextLine();
        List<User> list = userDao.list();
        for(User u : list){
            if(u.getUsername().equals(username)){
                System.out.println("Usuario seleccionado --> " + u.getUsername());
                return u;
            }
        }
        System.out.println("No existe usuario con dicho nombre");
        return null;
    }
    
    private void removeUser(User u){
        if(u!=null){
            if(userDao.remove(u.getId())) System.out.println("Usuario eliminado con exito");
            else System.out.println("Error al eliminar el usuario");
        }
    }
    
    private void addShowToUser(User u){
        if(u!=null){
            Show newShow = new Show();
            System.out.println("Introduce titulo del show: ");
            String title = input.nextLine();
            newShow.setTitulo(title);
            System.out.println("Introduce precio de entrada: ");
            double precio = InputValidation.validatePrice(input.nextLine(), input);
            newShow.setPrecio(precio);
            u.getShows().add(newShow);
            if(userDao.update(u)) System.out.println("Show añadido con éxito:");
            else{System.out.println("Error al añadir el show");} 
        }       
    }
    
    private void updateName(User u){
        if(u!=null){
            System.out.println("Introduce el nuevo username para " + u.getUsername());
            String username = input.nextLine();
            u.setUsername(username);
            if(userDao.update(u)) System.out.println("El usuario se ha actualizado con exito");
            else System.out.println("Error al actualizar el usuario");
        }
    }
    
    private void updateAge(User u){
        if(u!=null){
            System.out.println("Introduce la nueva edad para "+ u.getUsername());
            int edad = InputValidation.validateAge(input.nextLine(), input);
            u.setEdad(edad);
            if(userDao.update(u)) System.out.println("El usuario se ha actualizado con exito");
            else System.out.println("Error al actualizar el usuario");
        }
    }
}
