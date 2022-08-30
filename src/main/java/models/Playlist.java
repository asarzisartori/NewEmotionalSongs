package models;

/**
 * Classe che permette l'utilizzo di Playlist come modello
 * 
 * @author Andrea Sarzi Sartori 726694 Varese
 * @author Samuele Cervini 726624 Varese
 */
public class Playlist implements java.io.Serializable {
    
    private int id;
    private String nome;
    private String descrizione;
    private String genere;
    private String idutente;

    /**
     * Costruttore di Playlist
     * 
     * @param id Id della playlist
     * @param nome Nome della playlist
     * @param descrizione Descrizione della playlist
     * @param genere Genere della playlist
     * @param idutente IdUtente della playlist
     */
    public Playlist(int id, String nome, String descrizione, String genere, String idutente) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.genere = genere;
        this.id = id;
        this.idutente = idutente;
    }

    /**
     * Imposta l'id ad una playlist
     * 
     * @param id Id della playlist
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Imposta il nome ad una playlist
     * 
     * @param nome Nome di una playlist
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Imposta la descrizione ad una playlist
     * 
     * @param descrizione Descrizione di una playlist
     */
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    /**
     * Imposta il genere ad una playlist
     * 
     * @param genere Genere di una playlist
     */
    public void setGenere(String genere) {
        this.genere = genere;
    }

    /**
     * Imposta l'idutente ad una playlist
     * 
     * @param idutente IdUtente di una playlist
     */
    public void setIdutente(String idutente) {
        this.idutente = idutente;
    }

    /**
     * Recupera l'id di una playlist
     * 
     * @return id di una playlist
     */
    public int getId() {
        return id;
    }

    /**
     * Recupera il nome di una playlist
     * 
     * @return nome di una playlist
     */
    public String getNome() {
        return nome;
    }

    /**
     * Recupera la descrizione di una playlist
     * 
     * @return descrizione di una playlist
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Recupera il genere di una playlist
     * 
     * @return genere di una playlist
     */
    public String getGenere() {
        return genere;
    }

    /**
     * Recupera l'idutente di una playlist
     * 
     * @return idutente di una playlist
     */
    public String getIdutente() {
        return idutente;
    }
    
}