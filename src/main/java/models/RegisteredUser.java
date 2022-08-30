package models;

/**
 * Classe che permette l'utilizzo di RegisteredUser come modello
 * 
 * @author Andrea Sarzi Sartori 726694 Varese
 * @author Samuele Cervini 726624 Varese
 */
public class RegisteredUser implements java.io.Serializable {

    private String nome;
    private String cognome;
    private String CF;
    private String indirizzo;
    private String email;
    private String username;
    private String password;

    /**
     * Costruttore di RegisteredUser
     * 
     * @param nome Nome dell'utente registrato
     * @param cognome Cognome dell'utente registrato
     * @param CF Codice Fiscale dell'utente registrato
     * @param indirizzo Indirizzo dell'utente registrato
     * @param email Email dell'utente registrato
     * @param username Username dell'utente registrato
     * @param password Password dell'utente registrato
     */
    public RegisteredUser(String nome, String cognome, String CF, String indirizzo, String email, String username, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.CF = CF;
        this.indirizzo = indirizzo;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    /**
     * Recupera il nome dell'utente registrato
     * 
     * @return nome Nome dell'utente registrato
     */
    public String getName() {
        return nome;
    }

    /**
     * Recupera il cognome dell'utente registrato
     * 
     * @return cognome Cognome dell'utente registrato
     */
    public String getSurname() {
        return cognome;
    }

    /**
     * Recupera il codice fiscale dell'utente registrato
     * 
     * @return CF Codice fiscale dell'utente registrato
     */
    public String getCF() {
        return CF;
    }

    /**
     * Recupera l'indirizzo dell'utente registrato
     * 
     * @return indirizzo Indirizzo dell'utente registrato
     */
    public String getAddress() {
        return indirizzo;
    }
    
    /**
     * Recupera l'email dell'utente registrato
     * 
     * @return email Email dell'utente registrato
     */
    public String getemail() {
        return email;
    }
    
    /**
     * Recupera lo username dell'utente registrato
     * 
     * @return username Username dell'utente registrato
     */
    public String getUsername() {
        return username;
    }
}