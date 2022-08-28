package models;

public class RegisteredUser implements java.io.Serializable {

    private String nome;
    private String cognome;
    private String CF;
    private String indirizzo;
    private String email;
    private String username;
    private String password;

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
     * @return nome
     */
    public String getName() {
        return nome;
    }

    /**
     * @return cognome
     */
    public String getSurname() {
        return cognome;
    }

    /**
     * @return CF
     */
    public String getCF() {
        return CF;
    }

    /**
     * @return indirizzo
     */
    public String getAddress() {
        return indirizzo;
    }
    
    /**
     * @return email
     */
    public String getemail() {
        return email;
    }
    
    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }
}