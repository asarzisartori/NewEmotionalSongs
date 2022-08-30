package utilities;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.util.ArrayList;
import models.Song;

/**
 * Classe che permette la gestione di tutti i metodi utilizzati in EmotionalSongs
 * 
 * @author Andrea Sarzi Sartori 726624 Varese
 * @author Samuele Cervini 726624 Varese
 */
public interface IUtilities extends Remote {
    
    /**
     * Metodo che permette di effettuare la connessione con il db ed il server RMI
     * 
     * @param host Host del database
     * @param dbname Nome del database
     * @param port Porta del database
     * @param user Username del database PostgreSQL
     * @param password Password del database PostgreSQL
     * @return Stringa di controllo avvenuta connessione
     * @throws RemoteException 
     */
    public String tryFirstConnection(String host, String dbname, String port, String user, String password) throws RemoteException;
    
    /**
     * Metodo che permette di creare tutte le tabelle necessarie per EmotionalSongs
     * 
     * @param host Host del database
     * @param dbname Nome del database
     * @param port Porta del database
     * @param user Username del database
     * @param password Password del database
     * @param connection Connessione attualmente esistente
     * @throws RemoteException 
     */
    public void createAllTables(String host, String dbname, String port, String user, String password, Connection connection) throws RemoteException;
    
    /**
     * Metodo che permette di pololare la tabella Canzoni
     * 
     * @param connection Connessione attualmente esistente
     * @throws RemoteException 
     */
    public void populateDBWithSongs(Connection connection) throws RemoteException;
    
    /**
     * Metodo che permette di connette al database
     * 
     * @return Connessione al database
     * @throws RemoteException 
     */
    public Connection connect() throws RemoteException;
    
    /**
     * Metodo che permette di visualizzare le emozioni dei brani
     * 
     * @param idCanzone Id della canzone
     * @return Una lista di valori delle emozioni
     * @throws RemoteException 
     */
    public ArrayList<float[]> visualizzaEmozioneBrano(String idCanzone) throws RemoteException;
    
    /**
     * Metodo che permette di registrare una playlist
     * 
     * @param nome Nome della playlist
     * @param descrizione Descrizione della playlist
     * @param genere Genere della playlist
     * @param username Username dell'utente
     * @throws RemoteException 
     */
    public void registraPlaylist(String nome, String descrizione, String genere, String username) throws RemoteException;
                    
    /**
     * Metodo che permette di inserire la valutazione delle emozioni
     * 
     * @param idutente Id dell'utente
     * @param idcanzone Id della canzone
     * @param emotions_intensity Intensità dell'emozione
     * @throws RemoteException 
     */
    public void inserisciEmozioniBrano(String idutente, String idcanzone, int[] emotions_intensity) throws RemoteException;
            
    public void registrazione(String nome, String cognome, String CF, String indirizzo, String email, String username, String password) throws RemoteException;
            
    public Boolean checkIfAlreadyRegistered(String column, String info) throws RemoteException;
            
    public Boolean checkIfUserIsRegistered(String username, String password) throws RemoteException;
            
    public ArrayList<Object[]> cercaBranoMusicaleDaAutoreAnno(String author, String year) throws RemoteException;
            
    public ArrayList<Object[]> cercaBranoMusicaleDaTitolo(String title) throws RemoteException;
            
    public Song getSongsById(String id) throws RemoteException;
            
    public ArrayList<Object[]> getUserPlaylists(String username) throws RemoteException;
            
    public ArrayList<Object[]> getSongsByPlaylist(int id) throws RemoteException;
            
    public void deletePlaylist(int id) throws RemoteException;
            
    public void deleteSinglePlaylistSong(String idcanzone, int idplaylist) throws RemoteException;
            
    public ArrayList<String> getAllUserPlaylist(String id) throws RemoteException;
            
    public Boolean checkIfSongInPlaylist(String idcanzone, int idplaylist) throws RemoteException;
            
    public void insertSongInPlaylist(String idcanzone, int idplaylist) throws RemoteException;
            
    public int getPlaylistIdByName(String name, String username) throws RemoteException;
            
    public Boolean checkIfPlaylistNameAlreadyExist(String name, String username) throws RemoteException;
            
    public ArrayList<String> getAllUserInfo(String username) throws RemoteException;
            
    public String getPlaylistNameById(int id) throws RemoteException;
    
}