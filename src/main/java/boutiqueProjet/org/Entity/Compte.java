package boutiqueProjet.org.Entity;

import javax.persistence.*;

@Table(name = "compte")
@Entity
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String code;
    private String libelle;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getLibelle() {
        return libelle;
    }
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    public Compte(long id, String code, String libelle) {
        super();
        this.id = id;
        this.code = code;
        this.libelle = libelle;
    }
    public Compte() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    public String toString() {
        return "Compte [id=" + id + ", code=" + code + ", libelle=" + libelle + "]";
    }

}

