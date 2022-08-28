package utilities;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.util.ArrayList;
import models.Song;

/**
 *
 * @author andre
 */
public interface IUtilities extends Remote {
    
    public String tryFirstConnection(String host, String dbname, String port, String user, String password) throws RemoteException;
    
    public void createAllTables(String host, String dbname, String port, String user, String password, Connection connection) throws RemoteException;
    
    public Connection connect() throws RemoteException;
    
    public ArrayList<float[]> visualizzaEmozioneBrano(String idCanzone) throws RemoteException;
    
    public void registraPlaylist(String nome, String descrizione, String genere, String username) throws RemoteException;
                    
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