package models;

/**
 *
 * @author andre
 */
public class Playlist implements java.io.Serializable {
    
    private int id;
    private String nome;
    private String descrizione;
    private String genere;
    private String idutente;

    public Playlist(int id, String nome, String descrizione, String genere, String idutente) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.genere = genere;
        this.id = id;
        this.idutente = idutente;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public void setIdutente(String idutente) {
        this.idutente = idutente;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public String getGenere() {
        return genere;
    }

    public String getIdutente() {
        return idutente;
    }
    
}