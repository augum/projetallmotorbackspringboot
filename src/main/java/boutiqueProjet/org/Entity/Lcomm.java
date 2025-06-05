package boutiqueProjet.org.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "lcommande")
public class Lcomm {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name="numero")
    private String numero ;
    private String code_article;
    private String Libart;
    private double pu;
    private int qte;
    private int tva;
    private double totht;
    private double tottva;
    private double totttc;
    private String local;
    private String idMagasin;
    private LocalDate date;
    private String modePayement;
    private String client;
    @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference
    private Comm comm;

    public Lcomm() {
    }

    public Lcomm(long id, String numero, String code_article, String libart, double pu, int qte, int tva, double totht, double tottva, double totttc, String local, String idMagasin, LocalDate date, String modePayement, String client, Comm comm) {
        this.id = id;
        this.numero = numero;
        this.code_article = code_article;
        this.Libart = libart;
        this.pu = pu;
        this.qte = qte;
        this.tva = tva;
        this.totht = totht;
        this.tottva = tottva;
        this.totttc = totttc;
        this.local = local;
        this.idMagasin = idMagasin;
        this.date = date;
        this.modePayement = modePayement;
        this.client = client;
        this.comm = comm;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCode_article() {
        return code_article;
    }

    public void setCode_article(String code_article) {
        this.code_article = code_article;
    }

    public String getLibart() {
        return Libart;
    }

    public void setLibart(String libart) {
        Libart = libart;
    }

    public double getPu() {
        return pu;
    }

    public void setPu(double pu) {
        this.pu = pu;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public int getTva() {
        return tva;
    }

    public void setTva(int tva) {
        this.tva = tva;
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

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getIdMagasin() {
        return idMagasin;
    }

    public void setIdMagasin(String idMagasin) {
        this.idMagasin = idMagasin;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getModePayement() {
        return modePayement;
    }

    public void setModePayement(String modePayement) {
        this.modePayement = modePayement;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Comm getComm() {
        return comm;
    }

    public void setComm(Comm comm) {
        this.comm = comm;
    }

    @Override
    public String toString() {
        return "Lcomm{" +
                "id=" + id +
                ", numero=" + numero +
                ", code_article='" + code_article + '\'' +
                ", Libart='" + Libart + '\'' +
                ", pu=" + pu +
                ", qte=" + qte +
                ", tva=" + tva +
                ", totht=" + totht +
                ", tottva=" + tottva +
                ", totttc=" + totttc +
                ", local='" + local + '\'' +
                ", idMagasin='" + idMagasin + '\'' +
                ", date=" + date +
                ", modePayement='" + modePayement + '\'' +
                ", client='" + client + '\'' +
                ", comm=" + comm +
                '}';
    }
}
