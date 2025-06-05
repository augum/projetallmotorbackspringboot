package boutiqueProjet.org.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name ="boncommande")
public class Bcomm implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int annee;
    private String numero;
    private LocalDate date;
    private long code_client;
    private String libelle;
    private String lib_client;
    private double avance;
    private double totht;
    private double tottva;
    private double totttc;
    private String modepayement;
    private String mag;
    @JsonManagedReference
    @JsonIgnore
    @OneToMany(mappedBy = "comm", fetch=FetchType.EAGER)

    private List<BLcomm> lcomms = new ArrayList<>();

    public Bcomm() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Bcomm(long id, int annee, String numero, LocalDate date_comm, long code_client, String libelle, String lib_client, double avance, double totht, double tottva, double totttc, String modepayement, String mag, List<BLcomm> lcomms) {
        this.id = id;
        this.annee = annee;
        this.numero = numero;
        this.date = date_comm;
        this.code_client = code_client;
        this.libelle = libelle;
        this.lib_client = lib_client;
        this.avance = avance;
        this.totht = totht;
        this.tottva = tottva;
        this.totttc = totttc;
        this.modepayement = modepayement;
        this.mag = mag;
        this.lcomms = lcomms;
    }

    @Override
    public String toString() {
        return "Comm{" +
                "id=" + id +
                ", annee=" + annee +
                ", numero=" + numero +
                ", date_comm=" + date +
                ", code_client=" + code_client +
                ", libelle='" + libelle + '\'' +
                ", lib_client='" + lib_client + '\'' +
                ", avance=" + avance +
                ", totht=" + totht +
                ", tottva=" + tottva +
                ", totttc=" + totttc +
                ", modepayement='" + modepayement + '\'' +
                ", mag='" + mag + '\'' +
                ", lcomms=" + lcomms +
                '}';
    }

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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public long getCode_client() {
        return code_client;
    }

    public void setCode_client(long code_client) {
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

    public double getAvance() {
        return avance;
    }

    public void setAvance(double avance) {
        this.avance = avance;
    }

    public double getTotht() {
        return totht;
    }

    public void setTotht(double totht) {
        this.totht = totht;
    }

    public double getTottva() {
        return tottva;
    }

    public void setTottva(double tottva) {
        this.tottva = tottva;
    }

    public double getTotttc() {
        return totttc;
    }

    public void setTotttc(double totttc) {
        this.totttc = totttc;
    }

    public String getModepayement() {
        return modepayement;
    }

    public void setModepayement(String modepayement) {
        this.modepayement = modepayement;
    }

    public String getMag() {
        return mag;
    }

    public void setMag(String mag) {
        this.mag = mag;
    }

    public List<BLcomm> getLcomms() {
        return lcomms;
    }

    public void setLcomms(List<BLcomm> lcomms) {
        this.lcomms = lcomms;
    }
}
