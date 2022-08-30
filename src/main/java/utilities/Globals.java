package utilities;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Classe che permette di gestire le variabili globali
 * 
 * @author Andrea Sarzi Sartori 726694 Varese
 * @author Samuele Cervini 726624 Varese
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
   
   /**
    * Permette la connesione al registro RMI
    * 
    * @return Stringa di controllo
    */
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
   
   /**
    * Imposta se l'utente è loggato o no
    * 
    * @param condition Variabile di controllo
    */
   public synchronized static void setIsUserLogged(Boolean condition) {
       isUserLogged = condition;
   }
       
   /**
    * Imposta lo username dell'utente loggato
    * 
    * @param username Username dell'utente
    */
   public synchronized static void setCurrentUsername(String username) {
       currentUsername = username;
   }
    
}