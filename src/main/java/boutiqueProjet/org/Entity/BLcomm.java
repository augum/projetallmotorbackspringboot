package boutiqueProjet.org.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "blcommande")
public class BLcomm implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name="numero")
    private String numero ;
    private String code_article;
    private String Libart;
    private int pu;
    private int qte;
    private int tva;
    private double totht;
    private double tottva;
    private double totttc;
    @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference
    private Bcomm comm;
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
    public int getPu() {
        return pu;
    }
    public void setPu(int pu) {
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
    public Bcomm getComm() {
        return comm;
    }
    public void setComm(Bcomm comm) {
        this.comm = comm;
    }
    public BLcomm(long id, String numero, String code_article, String libart, int pu, int qte, int tva, double totht,
                 double tottva, double totttc, Bcomm comm) {
        super();
        this.id = id;
        this.numero = numero;
        this.code_article = code_article;
        Libart = libart;
        this.pu = pu;
        this.qte = qte;
        this.tva = tva;
        this.totht = totht;
        this.tottva = tottva;
        this.totttc = totttc;
        this.comm = comm;
    }
    public BLcomm() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    public String toString() {
        return "Lcomm [id=" + id + ", numero=" + numero + ", code_article=" + code_article + ", Libart=" + Libart
                + ", pu=" + pu + ", qte=" + qte + ", tva=" + tva + ", totht=" + totht + ", tottva=" + tottva
                + ", totttc=" + totttc + ", comm=" + comm + "]";
    }

}
