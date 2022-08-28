package models;

public class Song implements java.io.Serializable {

    private String anno;
    private String autore;
    private String titolo;
    private String id;

    public Song(String id, String titolo, String autore, String anno) {
        this.anno = anno;
        this.autore = autore;
        this.titolo = titolo;
        this.id = id;
    }
    
    public Song() { }

    /**
     * @return anno
     */
    public String getYear() {
        return anno;
    }

    /**
     * @return autore
     */
    public String getAuthor() {
        return autore;
    }

    /**
     * @return titolo
     */
    public String getTitle() {
        return titolo;
    }
    
    /**
     * @return id
     */
    public String getId() {
        return id;
    }
    
    public void setYear(String anno) {
        this.anno = anno;
    }
    
    public void setAuthor(String autore) {
        this.autore = autore;
    }

    public void setTitle(String titolo) {
        this.titolo = titolo;
    }
    
    public void setId(String id) {
        this.id = id;
    }
}