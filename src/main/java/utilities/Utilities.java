package utilities;

import java.io.File;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import models.Song;

public class Utilities extends UnicastRemoteObject implements IUtilities {
    
    /**
    * Connessione del database
    * 
    * @param name Nome del DB
    * @param user Nome utente
    * @param password Password
    * @throws Exception
    */
   @Override
   public synchronized String tryFirstConnection(String host, String dbname, String port, String user, String password) {

       String output = "";
       
        try {
            DriverManager.getConnection("jdbc:postgresql:" + "//" + host + ":" + port + "/" + dbname, user, password);
            output = "Ok";
        } catch (SQLException e) {
            if (e.getMessage().contains(dbname)) {
                try {
                    Connection connection_createdb = DriverManager.getConnection("jdbc:postgresql:" + "//" + host + ":" + port + "/", user, password);
                    Statement stmt = connection_createdb.createStatement();
                    String sql = "CREATE DATABASE " + dbname;
                    stmt.execute(sql);
                    Connection connection_createtables = DriverManager.getConnection("jdbc:postgresql:" + "//" + host + ":" + port + "/" + dbname, user, password);
                    createAllTables(host, dbname, port, user, password, connection_createtables);
                    output = "Ok";
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } else {
                output = "Non è stato possibile comunicare con il tuo database server, ricontrollare le credenziali inserite.";
            }
        }
        
        return output;
   }
    
   @Override
   public synchronized void createAllTables(String host, String dbname, String port, String user, String password, Connection connection) {
       try {
            Statement stmt = connection.createStatement();
            String sql_utentiregistrati = """
                                        CREATE TABLE UtentiRegistrati (
                                        Nome VARCHAR NOT NULL,
                                        Cognome VARCHAR NOT NULL,
                                        CF VARCHAR NOT NULL,
                                        Indirizzo VARCHAR NOT NULL,
                                        Email VARCHAR NOT NULL,
                                        Username VARCHAR PRIMARY KEY,
                                        Password VARCHAR NOT NULL
                                        );""";
            stmt.execute(sql_utentiregistrati);
            
            String sql_canzoni = """
                                 CREATE TABLE Canzoni (
                                 Anno VARCHAR NOT NULL,
                                 Id VARCHAR PRIMARY KEY,
                                 Autore VARCHAR NOT NULL,
                                 Titolo VARCHAR NOT NULL
                                 );""";
            stmt.execute(sql_canzoni);
            
            String sql_emozioni = """
                                  CREATE TABLE Emozioni (
                                  Id SERIAL PRIMARY KEY,
                                  Nome VARCHAR NOT NULL,
                                  Valutazione INT NOT NULL,
                                  IdCanzone VARCHAR NOT NULL REFERENCES canzoni(id),
                                  IdUtente VARCHAR NOT NULL REFERENCES utentiregistrati(username)
                                  );""";
            stmt.execute(sql_emozioni);
            
            String sql_playlist = """
                                  CREATE TABLE Playlist (
                                  Id SERIAL PRIMARY KEY,
                                  Nome VARCHAR NOT NULL,
                                  Descrizione VARCHAR NOT NULL,
                                  Genere VARCHAR NOT NULL,
                                  IdUtente VARCHAR NOT NULL REFERENCES utentiregistrati(username)
                                  );""";
            stmt.execute(sql_playlist);
            
            String sql_playlistassociate = """
                                           CREATE TABLE PlaylistAssociate (
                                           Id SERIAL PRIMARY KEY,
                                           IdPlaylist INT NOT NULL REFERENCES playlist(id) ON DELETE CASCADE,
                                           IdCanzone VARCHAR NOT NULL REFERENCES canzoni(id)
                                           );""";
            stmt.execute(sql_playlistassociate);
       } catch (SQLException ex) {
           Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
   
    //Permette la connessione al database
    @Override
    public synchronized Connection connect() {
        
        Connection connection = null;
        
        try {
            connection = DriverManager.getConnection("jdbc:postgresql:" + "//" + Globals.DBHost + ":" + Globals.DBPort + "/" + Globals.DBName, Globals.DBuser, Globals.DBpassword);
            System.out.println("E' stata effettuata una comunicazione con il database '" + Globals.DBName + "'");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return connection; 
    }
    
    @Override
    public synchronized ArrayList<float[]> visualizzaEmozioneBrano(String idCanzone) {
        
        ArrayList<float[]> results = new ArrayList<>();
        
        try
        {
            Connection connection = connect();
            Statement stmt = connection.createStatement();
            
            ResultSet rs_potere = stmt.executeQuery("SELECT COUNT (*) AS total FROM public.emozioni WHERE nome = 'Potere' AND idcanzone = '" + idCanzone + "'");
            
            if (!rs_potere.isBeforeFirst()) {
                float[] potere = {0};
                results.add(potere);
            } else {
                float count = 0;
                float avg = 0;
                while (rs_potere.next()) {
                    count = rs_potere.getInt("total");
                }
                ResultSet rs_potere_avg = stmt.executeQuery("SELECT AVG(valutazione) FROM public.emozioni WHERE nome = 'Potere' AND idCanzone = '" + idCanzone + "'");
                while (rs_potere_avg.next()) {
                    avg = rs_potere_avg.getFloat(1);
                }
                float[] potere = {count, avg};
                results.add(potere);
            }
            
            ResultSet rs_gioia = stmt.executeQuery("SELECT COUNT (*) AS total FROM public.emozioni WHERE nome = 'Gioia' AND idcanzone = '" + idCanzone + "'");
            
            if (!rs_gioia.isBeforeFirst()) {
                float[] goia = {0};
                results.add(goia);
            } else {
                float count = 0;
                float avg = 0;
                while (rs_gioia.next()) {
                    count = rs_gioia.getInt("total");
                }
                ResultSet rs_gioia_avg = stmt.executeQuery("SELECT AVG(valutazione) FROM public.emozioni WHERE nome = 'Gioia' AND idCanzone = '" + idCanzone + "'");
                while (rs_gioia_avg.next()) {
                    avg = rs_gioia_avg.getFloat(1);
                }
                float[] goia = {count, avg};
                results.add(goia);
            }
            
            ResultSet rs_tensione = stmt.executeQuery("SELECT COUNT (*) AS total FROM public.emozioni WHERE nome = 'Tensione' AND idcanzone = '" + idCanzone + "'");
            
            if (!rs_tensione.isBeforeFirst()) {
                float[] tensione = {0};
                results.add(tensione);
            } else {
                float count = 0;
                float avg = 0;
                while (rs_tensione.next()) {
                    count = rs_tensione.getInt("total");
                }
                ResultSet rs_tensione_avg = stmt.executeQuery("SELECT AVG(valutazione) FROM public.emozioni WHERE nome = 'Tensione' AND idCanzone = '" + idCanzone + "'");
                while (rs_tensione_avg.next()) {
                    avg = rs_tensione_avg.getFloat(1);
                }
                float[] tensione = {count, avg};
                results.add(tensione);
            }
            
            ResultSet rs_nostalgia = stmt.executeQuery("SELECT COUNT (*) AS total FROM public.emozioni WHERE nome = 'Nostalgia' AND idcanzone = '" + idCanzone + "'");
            
            if (!rs_nostalgia.isBeforeFirst()) {
                float[] nostalgia = {0};
                results.add(nostalgia);
            } else {
                float count = 0;
                float avg = 0;
                while (rs_nostalgia.next()) {
                    count = rs_nostalgia.getInt("total");
                }
                ResultSet rs_nostalgia_avg = stmt.executeQuery("SELECT AVG(valutazione) FROM public.emozioni WHERE nome = 'Nostalgia' AND idCanzone = '" + idCanzone + "'");
                while (rs_nostalgia_avg.next()) {
                    avg = rs_nostalgia_avg.getFloat(1);
                }
                float[] nostalgia = {count, avg};
                results.add(nostalgia);
            }
            
            ResultSet rs_tristezza = stmt.executeQuery("SELECT COUNT (*) AS total FROM public.emozioni WHERE nome = 'Tristezza' AND idcanzone = '" + idCanzone + "'");
            
            if (!rs_tristezza.isBeforeFirst()) {
                float[] tristezza = {0};
                results.add(tristezza);
            } else {
                float count = 0;
                float avg = 0;
                while (rs_tristezza.next()) {
                    count = rs_tristezza.getInt("total");
                }
                ResultSet rs_tristezza_avg = stmt.executeQuery("SELECT AVG(valutazione) FROM public.emozioni WHERE nome = 'Tristezza' AND idCanzone = '" + idCanzone + "'");
                while (rs_tristezza_avg.next()) {
                    avg = rs_tristezza_avg.getFloat(1);
                }
                float[] tristezza = {count, avg};
                results.add(tristezza);
            }
            
            ResultSet rs_stupore = stmt.executeQuery("SELECT COUNT (*) AS total FROM public.emozioni WHERE nome = 'Stupore' AND idcanzone = '" + idCanzone + "'");
            
            if (!rs_stupore.isBeforeFirst()) {
                float[] stupore = {0};
                results.add(stupore);
            } else {
                float count = 0;
                float avg = 0;
                while (rs_stupore.next()) {
                    count = rs_stupore.getInt("total");
                }
                ResultSet rs_stupore_avg = stmt.executeQuery("SELECT AVG(valutazione) FROM public.emozioni WHERE nome = 'Stupore' AND idCanzone = '" + idCanzone + "'");
                while (rs_stupore_avg.next()) {
                    avg = rs_stupore_avg.getFloat(1);
                }
                float[] stupore = {count, avg};
                results.add(stupore);
            }
            
            ResultSet rs_solennita = stmt.executeQuery("SELECT COUNT (*) AS total FROM public.emozioni WHERE nome = 'Solennità' AND idcanzone = '" + idCanzone + "'");
            
            if (!rs_solennita.isBeforeFirst()) {
                float[] solennita = {0};
                results.add(solennita);
            } else {
                float count = 0;
                float avg = 0;
                while (rs_solennita.next()) {
                    count = rs_solennita.getInt("total");
                }
                ResultSet rs_solennita_avg = stmt.executeQuery("SELECT AVG(valutazione) FROM public.emozioni WHERE nome = 'Solennità' AND idCanzone = '" + idCanzone + "'");
                while (rs_solennita_avg.next()) {
                    avg = rs_solennita_avg.getFloat(1);
                }
                float[] solennita = {count, avg};
                results.add(solennita);
            }
            
            ResultSet rs_tenerezza = stmt.executeQuery("SELECT COUNT (*) AS total FROM public.emozioni WHERE nome = 'Tenerezza' AND idcanzone = '" + idCanzone + "'");
            
            if (!rs_tenerezza.isBeforeFirst()) {
                float[] tenerezza = {0};
                results.add(tenerezza);
            } else {
                float count = 0;
                float avg = 0;
                while (rs_tenerezza.next()) {
                    count = rs_tenerezza.getInt("total");
                }
                ResultSet rs_tenerezza_avg = stmt.executeQuery("SELECT AVG(valutazione) FROM public.emozioni WHERE nome = 'Tenerezza' AND idCanzone = '" + idCanzone + "'");
                while (rs_tenerezza_avg.next()) {
                    avg = rs_tenerezza_avg.getFloat(1);
                }
                float[] tenerezza = {count, avg};
                results.add(tenerezza);
            }
            
            ResultSet rs_calma = stmt.executeQuery("SELECT COUNT (*) AS total FROM public.emozioni WHERE nome = 'Calma' AND idcanzone = '" + idCanzone + "'");
            
            if (!rs_calma.isBeforeFirst()) {
                float[] calma = {0};
                results.add(calma);
            } else {
                float count = 0;
                float avg = 0;
                while (rs_calma.next()) {
                    count = rs_calma.getInt("total");
                }
                ResultSet rs_calma_avg = stmt.executeQuery("SELECT AVG(valutazione) FROM public.emozioni WHERE nome = 'Calma' AND idCanzone = '" + idCanzone + "'");
                while (rs_calma_avg.next()) {
                    avg = rs_calma_avg.getFloat(1);
                }
                float[] calma = {count, avg};
                results.add(calma);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return results;
    }
    
    @Override
    public synchronized void registraPlaylist(String nome, String descrizione, String genere, String username) {
        
        try {
            
            Connection connection = connect();
            
            PreparedStatement st = connection.prepareStatement("INSERT INTO playlist (nome, descrizione, genere, idutente)"
                                                               + "VALUES (?, ?, ?, ?)");
            
            st.setString(1, nome);
            st.setString(2, descrizione);
            st.setString(3, genere);
            st.setString(4, username);
            
            st.executeUpdate();
            st.close();
            
            Globals.setIsUserLogged(true);
            Globals.setCurrentUsername(username);
            
        } catch (SQLException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public synchronized void inserisciEmozioniBrano(String idutente, String idcanzone, int[] emotions_intensity) {
        
        String[] emotions = {"Calma", "Gioia", "Nostalgia", "Potere", "Solennità", "Stupore", "Tenerezza", "Tensione", "Tristezza"};
        
        try {
            
            Connection connection = connect();
            Statement stmt = connection.createStatement();
            
            for (int i = 0; i < emotions.length; i++) {
                ResultSet rs = stmt.executeQuery("SELECT * FROM public.emozioni WHERE nome = '" + emotions[i] + "' AND idutente = '" + idutente + "'");
                
                if (!rs.isBeforeFirst()) {
                    if (emotions_intensity[i] != 0) {
                        PreparedStatement st = connection.prepareStatement("INSERT INTO public.emozioni (nome, valutazione, idcanzone, idutente) VALUES (?, ?, ?, ?)");

                        st.setString(1, emotions[i]);
                        st.setInt(2, emotions_intensity[i]);
                        st.setString(3, idcanzone);
                        st.setString(4, idutente);

                        st.executeUpdate();
                        st.close();
                    }
                } else {
                    if (emotions_intensity[i] != 0) {
                        PreparedStatement st = connection.prepareStatement("UPDATE public.emozioni SET valutazione = ? WHERE nome = ? AND idcanzone = ? AND idutente = ?");
                    
                        st.setInt(1, emotions_intensity[i]);
                        st.setString(2, emotions[i]);
                        st.setString(3, idcanzone);
                        st.setString(4, idutente);

                        st.executeUpdate();
                        st.close();
                    } else {
                        PreparedStatement st = connection.prepareStatement("DELETE FROM public.emozioni WHERE nome = ? AND idcanzone = ? AND idutente = ?");
                        
                        st.setString(1, emotions[i]);
                        st.setString(2, idcanzone);
                        st.setString(3, idutente);

                        st.executeUpdate();
                        st.close();
                    }
                }
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Metodo per registrare un cliente
     * @param nome        Nome del cliente da registrare
     * @param cognome     Cognome del cliente da registrare
     * @param CF          Codice fiscale del cliente da registrare
     * @param indirizzo   Indirizzo di residenza del cliente da registrare
     * @param email       E-mail del cliente da registrare
     * @param username    Nickname scelto del cliente da registrare
     * @param password    Password scelta del cliente da registrare
     */
    @Override
    public synchronized void registrazione(String nome, String cognome, String CF, String indirizzo, String email, String username, String password) {
        
        try {
            
            Connection connection = connect();
            
            PreparedStatement st = connection.prepareStatement("INSERT INTO utentiregistrati (nome, cognome, CF, indirizzo, email, username, password)"
                                                               + "VALUES (?, ?, ?, ?, ?, ?, ?)");
            
            st.setString(1, nome);
            st.setString(2, cognome);
            st.setString(3, CF);
            st.setString(4, indirizzo);
            st.setString(5, email);
            st.setString(6, username);
            st.setString(7, password);
            
            st.executeUpdate();
            st.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public synchronized Boolean checkIfAlreadyRegistered(String column, String info) {
        
        Boolean checkIfAlreadyRegistered = false;
        
        try
        {
            Connection connection = connect();
            Statement stmt = connection.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM public.utentiregistrati WHERE " + column + " = '" + info + "'");
            
            if (!rs.isBeforeFirst())
            {
                return checkIfAlreadyRegistered;
            }
            
            checkIfAlreadyRegistered = true;
            
            return checkIfAlreadyRegistered;
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return checkIfAlreadyRegistered;
    }
    
    @Override
    public synchronized Boolean checkIfUserIsRegistered(String username, String password) {
        
        Boolean checkIfUserIsRegistered = false;
        
        try
        {
            Connection connection = connect();
            Statement stmt = connection.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM public.utentiregistrati WHERE username = '" + username + "' AND password = '" + password + "'");
            
            if (!rs.isBeforeFirst())
            {
                return checkIfUserIsRegistered;
            }
            
            checkIfUserIsRegistered = true;
            
            return checkIfUserIsRegistered;
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return checkIfUserIsRegistered;
    }
    
    @Override
    public synchronized ArrayList<Object[]> cercaBranoMusicaleDaAutoreAnno(String author, String year) {
        
        author = author.replace("'", "''");
        ArrayList<Object[]> results = new ArrayList<>();
        
        try
        {
            Connection connection = connect();
            Statement stmt = connection.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT Id, Anno, Autore, Titolo FROM public.canzoni WHERE LOWER(Autore) = LOWER('" + author + "') AND anno = '" + year + "'");
            
            if (!rs.isBeforeFirst())
            {
                return null;
            }
            
            while (rs.next()) {
               Object[] song = new Object[4];
               
               song[0] = rs.getString("id");
               song[1] = rs.getString("titolo");
               song[2] = rs.getString("autore");
               song[3] = rs.getString("anno");
              
               results.add(song);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return results;
    }
    
    @Override
    public synchronized ArrayList<Object[]> cercaBranoMusicaleDaTitolo(String title) {
        
        title = title.replace("'", "''");
        ArrayList<Object[]> results = new ArrayList<>();
        
        try
        {
            Connection connection = connect();
            Statement stmt = connection.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT Id, Anno, Autore, Titolo FROM public.canzoni WHERE LOWER(Titolo) LIKE LOWER('%" + title + "%')");
            
            if (!rs.isBeforeFirst())
            {
                return null;
            }
            
            while (rs.next()) {
               Object[] song = new Object[4];
               
               song[0] = rs.getString("id");
               song[1] = rs.getString("titolo");
               song[2] = rs.getString("autore");
               song[3] = rs.getString("anno");
              
               results.add(song);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return results;
    }
    
    @Override
    public synchronized Song getSongsById(String id) {

        Song song = null;
        
        try
        {
            Connection connection = connect();
            Statement stmt = connection.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM public.canzoni WHERE id = '" + id + "'");
            
            if (!rs.isBeforeFirst())
            {
                return null;
            }
            
            while (rs.next()) {
                song = new Song(rs.getString("id"), rs.getString("titolo"), rs.getString("autore"), rs.getString("anno"));
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return song;
    }
    
    @Override
    public synchronized ArrayList<Object[]> getUserPlaylists(String username) {
        
        ArrayList<Object[]> results = new ArrayList<>();
        
        try
        {
            Connection connection = connect();
            Statement stmt = connection.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM public.playlist WHERE idutente = '" + username + "'");
            
            if (!rs.isBeforeFirst())
            {
                return null;
            }
            
            while (rs.next()) {
               Object[] playlist = new Object[5];
               
               playlist[0] = rs.getInt("id");
               playlist[1] = rs.getString("nome");
               playlist[2] = rs.getString("descrizione");
               playlist[3] = rs.getString("genere");
               playlist[4] = rs.getString("idutente");
              
               results.add(playlist);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return results;
    }

    @Override
    public synchronized ArrayList<Object[]> getSongsByPlaylist(int id) {

        ArrayList<String> idcanzoni = new ArrayList<>();
        ArrayList<Object[]> results = new ArrayList<>();
        
        try
        {
            Connection connection = connect();
            Statement stmt = connection.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT idcanzone FROM public.playlistassociate WHERE idplaylist = '" + id + "'");
            
            if (!rs.isBeforeFirst())
            {
                return null;
            }
            
            while (rs.next()) {
                idcanzoni.add(rs.getString("idcanzone"));
            }
            
            for (String id_playlist : idcanzoni) {
                
                ResultSet rs_playlist = stmt.executeQuery("SELECT * FROM public.canzoni WHERE id = '" + id_playlist + "'");
                
                if (!rs_playlist.isBeforeFirst())
                {
                    return null;
                }

                while (rs_playlist.next()) {
                    Object[] song = new Object[4];
               
                    song[0] = rs_playlist.getString("id");
                    song[1] = rs_playlist.getString("titolo");
                    song[2] = rs_playlist.getString("autore");
                    song[3] = rs_playlist.getString("anno");

                    results.add(song);
                }
            }
            
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return results;
    }
    
    @Override
    public synchronized void deletePlaylist(int id) {
        
        try
        {
            Connection connection = connect();
        
            PreparedStatement st = connection.prepareStatement("DELETE FROM public.playlist WHERE id = ?");
                        
            st.setInt(1, id);

            st.executeUpdate();
            st.close();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public synchronized void deleteSinglePlaylistSong(String idcanzone, int idplaylist) {
        
        try
        {
            Connection connection = connect();
        
            PreparedStatement st = connection.prepareStatement("DELETE FROM public.playlistassociate WHERE idcanzone = ? AND idplaylist = ?");
                        
            st.setString(1, idcanzone);
            st.setInt(2, idplaylist);

            st.executeUpdate();
            st.close();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public synchronized ArrayList<String> getAllUserPlaylist(String id) {
        
        ArrayList<String> results = new ArrayList<>();
        
        try
        {
            Connection connection = connect();
            Statement stmt = connection.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT nome FROM public.playlist WHERE idutente = '" + id + "'");

            if (!rs.isBeforeFirst())
            {
                return null;
            }

            while (rs.next()) {
                results.add(rs.getString("nome"));
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return results;
    }
    
    @Override
    public synchronized Boolean checkIfSongInPlaylist(String idcanzone, int idplaylist) {
        
        Boolean checkIfSongInPlaylist = false;
        
        try
        {
            Connection connection = connect();
            Statement stmt = connection.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM public.playlistassociate WHERE idcanzone = '" + idcanzone + "' AND idplaylist = " + idplaylist + "");
            
            if (!rs.isBeforeFirst())
            {
                return checkIfSongInPlaylist;
            }
            
            checkIfSongInPlaylist = true;
            
            return checkIfSongInPlaylist;
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return checkIfSongInPlaylist;
    }
    
    @Override
    public synchronized void insertSongInPlaylist(String idcanzone, int idplaylist) {
        
        try {
            Connection connection = connect();
            PreparedStatement st = connection.prepareStatement("INSERT INTO playlistassociate (idcanzone, idplaylist)"
                                                               + "VALUES (?, ?)");
            
            st.setString(1, idcanzone);
            st.setInt(2, idplaylist);
            
            st.executeUpdate();
            st.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    @Override
    public synchronized int getPlaylistIdByName(String name, String idutente) {
        
        int id = -1;
        
        try
        {
            Connection connection = connect();
            Statement stmt = connection.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT id FROM public.playlist WHERE nome = '" + name + "' AND idutente = '" + idutente + "'");

            if (!rs.isBeforeFirst())
            {
                return -1;
            }

            while (rs.next()) {
                id = rs.getInt("id");
            }  
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return id;
    }
    
    @Override
    public synchronized Boolean checkIfPlaylistNameAlreadyExist(String name, String username) {
        
        Boolean checkIfAlreadyRegistered = false;
        
        try
        {
            Connection connection = connect();
            Statement stmt = connection.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM public.playlist WHERE nome = '" + name + "' AND idutente = '" + username + "'");
            
            if (!rs.isBeforeFirst())
            {
                return checkIfAlreadyRegistered;
            }
            
            checkIfAlreadyRegistered = true;
            
            return checkIfAlreadyRegistered;
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return checkIfAlreadyRegistered;
    }
    
    @Override
    public synchronized ArrayList<String> getAllUserInfo(String username) {

        ArrayList<String> results = new ArrayList<>();
        
        try
        {
            Connection connection = connect();
            Statement stmt = connection.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM public.utentiregistrati WHERE username = '" + username + "'");
            
            if (!rs.isBeforeFirst())
            {
                return null;
            }
            
            while (rs.next()) {
               results.add(rs.getString("nome"));
               results.add(rs.getString("cognome"));
               results.add(rs.getString("cf"));
               results.add(rs.getString("indirizzo"));
               results.add(rs.getString("email"));
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return results;
    }
    
    @Override
    public synchronized String getPlaylistNameById(int id) {
        
        String nome = "";
        
        try
        {
            Connection connection = connect();
            Statement stmt = connection.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT nome FROM public.playlist WHERE id= '" + id + "'");

            if (!rs.isBeforeFirst())
            {
                return null;
            }

            while (rs.next()) {
                nome = rs.getString("nome");
            }  
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return nome;
    }
    
    public synchronized static void setLogoES(JFrame jframe) {
        ImageIcon img = new ImageIcon(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "images" + File.separator + "logo.png");
        jframe.setIconImage(img.getImage());
    }
    
    public synchronized static void setLogoDB(JFrame jframe) {
        ImageIcon img = new ImageIcon(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "images" + File.separator + "database.png");
        jframe.setIconImage(img.getImage());
    }
    
}