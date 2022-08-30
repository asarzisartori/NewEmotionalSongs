package models;

/**
 * Classe che permette l'utilizzo di Song come modello
 * 
 * @author Andrea Sarzi Sartori 726694 Varese
 * @author Samuele Cervini 726624 Varese
 */
public class Song implements java.io.Serializable {

    private String anno;
    private String autore;
    private String titolo;
    private String id;

    /**
     * Costruttore di Song
     * 
     * @param id Id della canzone selezionata
     * @param titolo Titolo della canzone selezionata
     * @param autore Autore della canzone selezionata
     * @param anno Anno della canzone selezionata
     */
    public Song(String id, String titolo, String autore, String anno) {
        this.anno = anno;
        this.autore = autore;
        this.titolo = titolo;
        this.id = id;
    }
    
    public Song() { }

    /**
     * Recupera l'anno della canzone selezionata
     * 
     * @return anno Anno della canzone selezionata
     */
    public String getYear() {
        return anno;
    }

    /**
     * Recupera l'autore della canzone selezionata
     * 
     * @return autore Autore della canzone selezionata
     */
    public String getAuthor() {
        return autore;
    }

    /**
     * Recupera il titolo della canzone selezionata
     * 
     * @return titolo Titolo della canzone selezionata
     */
    public String getTitle() {
        return titolo;
    }
    
    /**
     * Recupera l'id della canzone selezionata
     * 
     * @return id Id della canzone selezionata
     */
    public String getId() {
        return id;
    }
    
    /**
     * Imposta l'anno della canzone selezionata
     * 
     * @param anno Anno della canzone selezionata
     */
    public void setYear(String anno) {
        this.anno = anno;
    }
    
    /**
     * Imposta l'autore della canzone selezionata
     * 
     * @param autore Autore della canzone selezionata
     */
    public void setAuthor(String autore) {
        this.autore = autore;
    }

    /**
     * Imposta il titolo della canzone selezionata
     * 
     * @param titolo Titolo della canzone selezionata
     */
    public void setTitle(String titolo) {
        this.titolo = titolo;
    }
    
    /**
     * Imposta l'id della canzone selezionata
     * 
     * @param id Id della canzone selezionata
     */
    public void setId(String id) {
        this.id = id;
    }
}