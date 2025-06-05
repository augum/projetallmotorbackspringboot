package boutiqueProjet.org.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "commande")
public class Comm {
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
    private double mt;
    private double reste;
    private String modepayement;
    private String mag;
    private String idUtilisateur;
    @JsonManagedReference
    @JsonIgnore
    @OneToMany(mappedBy = "comm", fetch=FetchType.EAGER)

    private List<Lcomm> lcomms = new ArrayList<>();

    public Comm() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Comm(long id, int annee, String numero, LocalDate date, long code_client, String libelle, String lib_client, double avance, double totht, double tottva, double totttc, double mt, double reste, String modepayement, String mag, String idUtilisateur, List<Lcomm> lcomms) {
        this.id = id;
        this.annee = annee;
        this.numero = numero;
        this.date = date;
        this.code_client = code_client;
        this.libelle = libelle;
        this.lib_client = lib_client;
        this.avance = avance;
        this.totht = totht;
        this.tottva = tottva;
        this.totttc = totttc;
        this.mt = mt;
        this.reste = reste;
        this.modepayement = modepayement;
        this.mag = mag;
        this.idUtilisateur = idUtilisateur;
        this.lcomms = lcomms;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAnnee() {
        return this.annee;
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

    public double getMt() {
        return mt;
    }

    public void setMt(double mt) {
        this.mt = mt;
    }

    public double getReste() {
        return reste;
    }

    public void setReste(double reste) {
        this.reste = reste;
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

    public List<Lcomm> getLcomms() {
        return lcomms;
    }

    public void setLcomms(List<Lcomm> lcomms) {
        this.lcomms = lcomms;
    }
    public String getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(String idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    @Override
    public String toString() {
        return "Comm{" +
                "id=" + id +
                ", annee=" + annee +
                ", numero=" + numero +
                ", date=" + date +
                ", code_client=" + code_client +
                ", libelle='" + libelle + '\'' +
                ", lib_client='" + lib_client + '\'' +
                ", avance=" + avance +
                ", totht=" + totht +
                ", tottva=" + tottva +
                ", totttc=" + totttc +
                ", mt=" + mt +
                ", reste=" + reste +
                ", modepayement='" + modepayement + '\'' +
                ", mag='" + mag + '\'' +
                ", lcomms=" + lcomms +
                ", idUtilisateur='" + idUtilisateur + '\'' +
                '}';
    }


}
