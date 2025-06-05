package boutiqueProjet.org.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "devis")
public class Devis {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int annee;
    private int numero;
    private Date date_mvt;
    private int code_client;
    private String libelle;
    private String lib_client;
    private float totht;
    private float tottva;
    private float totttc;
    @JsonManagedReference
    @OneToMany(mappedBy = "devis")

    private List<Ldevis> ldeviss = new ArrayList<>();
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public int getAnnee() {
        return annee;
    }
    public void setAnnee(int annee) {
        this.annee = annee;
    }
    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
    public Date getDate_mvt() {
        return date_mvt;
    }
    public void setDate_mvt(Date date_mvt) {
        this.date_mvt = date_mvt;
    }
    public int getCode_client() {
        return code_client;
    }
    public void setCode_client(int code_client) {
        this.code_client = code_client;
    }
    public String getLibelle() {
        return libelle;
    }
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    public String getLib_client() {
        return lib_client;
    }
    public void setLib_client(String lib_client) {
        this.lib_client = lib_client;
    }
    public float getTotht() {
        return totht;
    }
    public void setTotht(float totht) {
        this.totht = totht;
    }
    public float getTottva() {
        return tottva;
    }
    public void setTottva(float tottva) {
        this.tottva = tottva;
    }
    public float getTotttc() {
        return totttc;
    }
    public void setTotttc(float totttc) {
        this.totttc = totttc;
    }
    public List<Ldevis> getLdeviss() {
        return ldeviss;
    }
    public void setLdeviss(List<Ldevis> ldeviss) {
        this.ldeviss = ldeviss;
    }
    public Devis(long id, int annee, int numero, Date date_mvt, int code_client, String libelle, String lib_client,
                 float totht, float tottva, float totttc,  List<Ldevis> ldeviss) {
        super();
        this.id = id;
        this.annee = annee;
        this.numero = numero;
        this.date_mvt = date_mvt;
        this.code_client = code_client;
        this.libelle = libelle;
        this.lib_client = lib_client;
        this.totht = totht;
        this.tottva = tottva;
        this.totttc = totttc;
        this.ldeviss = ldeviss;
    }
    public Devis() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    public String toString() {
        return "Devis [id=" + id + ", annee=" + annee + ", numero=" + numero + ", date_mvt=" + date_mvt
                + ", code_client=" + code_client + ", libelle=" + libelle + ", lib_client=" + lib_client + ", totht="
                + totht + ", tottva=" + tottva + ", totttc=" + totttc + ", ldeviss=" + ldeviss + "]";
    }

}

