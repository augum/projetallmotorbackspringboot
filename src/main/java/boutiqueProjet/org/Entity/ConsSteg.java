package boutiqueProjet.org.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ConsSteg")
public class ConsSteg {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int numero;
    private int annee;
    private int mois;
    private int code_direction;
    private String lib_direction;
    private String libelle;
    @JsonManagedReference
    @JsonIgnore
    @OneToMany(mappedBy = "consSteg", fetch=FetchType.EAGER)

    private List<LconsSteg> lconsStegs = new ArrayList<>();
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
    public int getAnnee() {
        return annee;
    }
    public void setAnnee(int annee) {
        this.annee = annee;
    }
    public int getMois() {
        return mois;
    }
    public void setMois(int mois) {
        this.mois = mois;
    }
    public int getCode_direction() {
        return code_direction;
    }
    public void setCode_direction(int code_direction) {
        this.code_direction = code_direction;
    }
    public String getLib_direction() {
        return lib_direction;
    }
    public void setLib_direction(String lib_direction) {
        this.lib_direction = lib_direction;
    }
    public String getLibelle() {
        return libelle;
    }
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    public List<LconsSteg> getLconsStegs() {
        return lconsStegs;
    }
    public void setLconsStegs(List<LconsSteg> lconsStegs) {
        this.lconsStegs = lconsStegs;
    }
    public ConsSteg(long id, int numero, int annee, int mois, int code_direction, String lib_direction, String libelle,
                     List<LconsSteg> lconsStegs) {
        super();
        this.id = id;
        this.numero = numero;
        this.annee = annee;
        this.mois = mois;
        this.code_direction = code_direction;
        this.lib_direction = lib_direction;
        this.libelle = libelle;
        this.lconsStegs = lconsStegs;
    }
    public ConsSteg() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    public String toString() {
        return "ConsSteg [id=" + id + ", numero=" + numero + ", annee=" + annee + ", mois=" + mois + ", code_direction="
                + code_direction + ", lib_direction=" + lib_direction + ", libelle=" + libelle + ", lconsStegs="
                + lconsStegs + "]";
    }

}

