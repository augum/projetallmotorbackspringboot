package boutiqueProjet.org.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Entity
public class CommPret {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int annee;
    private int numero;
    private LocalDate date;
    private long code_mag;
    private String libelle;
    private String lib_mag;
    private double avance;
    private double totht;
    private double tottva;
    private double totttc;
    private String modepayement;
    private String mag;
    @JsonManagedReference
    @JsonIgnore
    @OneToMany(mappedBy = "comm", fetch= FetchType.EAGER)

    private List<LcommPret> lcomms = new ArrayList<>();

    public CommPret() {
    }

    public CommPret(long id, int annee, int numero, LocalDate date, long code_mag, String libelle, String lib_mag, double avance, double totht, double tottva, double totttc, String modepayement, String mag, List<LcommPret> lcomms) {
        this.id = id;
        this.annee = annee;
        this.numero = numero;
        this.date = date;
        this.code_mag = code_mag;
        this.libelle = libelle;
        this.lib_mag = lib_mag;
        this.avance = avance;
        this.totht = totht;
        this.tottva = tottva;
        this.totttc = totttc;
        this.modepayement = modepayement;
        this.mag = mag;
        this.lcomms = lcomms;
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

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public long getCode_mag() {
        return code_mag;
    }

    public void setCode_mag(long code_mag) {
        this.code_mag = code_mag;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getLib_mag() {
        return lib_mag;
    }

    public void setLib_mag(String lib_mag) {
        this.lib_mag = lib_mag;
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

    public List<LcommPret> getLcomms() {
        return lcomms;
    }

    public void setLcomms(List<LcommPret> lcomms) {
        this.lcomms = lcomms;
    }

    @Override
    public String toString() {
        return "CommPret{" +
                "id=" + id +
                ", annee=" + annee +
                ", numero=" + numero +
                ", date=" + date +
                ", code_mag=" + code_mag +
                ", libelle='" + libelle + '\'' +
                ", lib_mag='" + lib_mag + '\'' +
                ", avance=" + avance +
                ", totht=" + totht +
                ", tottva=" + tottva +
                ", totttc=" + totttc +
                ", modepayement='" + modepayement + '\'' +
                ", mag='" + mag + '\'' +
                ", lcomms=" + lcomms +
                '}';
    }
}
