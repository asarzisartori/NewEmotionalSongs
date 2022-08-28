package utilities;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author andre
 */
public class Globals {
   
   //Variabili per gestire il login dell'utente
   public static boolean isUserLogged;
   public static String currentUsername;
   
   //Credenziali database
   public static String DBHost;
   public static String DBName;
   public static String DBPort;
   public static String DBuser;
   public static String DBpassword;
   
   //Variabile per poter richiamare i metodi remoti
   public static IUtilities server = null;
   
   //Metodo statico per permettere il funzionamento dell'RMI
   public static String connectToRegistry() {
       
       String check = "";
       
       try {
            Registry registry = LocateRegistry.getRegistry(1099);
            server = (IUtilities) registry.lookup("Utilities"); 
            check = "Ok";
        } catch (NotBoundException | RemoteException e) {
            check = "Error";
        }
       
       return check;
   }
   
   public synchronized static void setIsUserLogged(Boolean condition) {
       isUserLogged = condition;
   }
       
   public synchronized static void setCurrentUsername(String username) {
       currentUsername = username;
   }
    
}