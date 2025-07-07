package boutiqueProjet.org.Entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@JsonInclude(value=Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Article {
 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 private long id;
 private String code;
 private String codeA;
 private String code_b;
 private String libelle;
 private float pa;
 private float pmin;
 private float pmax;
 private float pv;
 private float pvd;
 private int tva;
 private int fodec;
 private int stock;
 private int stkinit;
 private int stkajouter;
 private String codeCateg;
 private String codeScateg;
 private Long idMagasin;
 private boolean dispo = true;
 private LocalDate cree;
 private String  local;
 private Long  expiration ;
 private float mb;

    public Article() {
    }

    public Article(long id, String code, String codeA, String code_b, String libelle, float pa, float pmin, float pmax, float pv,float pvd, int tva, int fodec, int stock, int stkinit, int stkajouter, String codeCateg, String codeScateg, Long idMagasin, boolean dispo, LocalDate cree, String local, Long expiration, float mb) {
        this.id = id;
        this.code = code;
        this.codeA = codeA;
        this.code_b = code_b;
        this.libelle = libelle;
        this.pa = pa;
        this.pmin = pmin;
        this.pmax = pmax;
        this.pv = pv;
        this.pvd = pvd;
        this.tva = tva;
        this.fodec = fodec;
        this.stock = stock;
        this.stkinit = stkinit;
        this.stkajouter = stkajouter;
        this.codeCateg = codeCateg;
        this.codeScateg = codeScateg;
        this.idMagasin = idMagasin;
        this.dispo = dispo;
        this.cree = cree;
        this.local = local;
        this.expiration = expiration;
        this.mb = mb;
    }

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

    public String getCodeA() {
        return codeA;
    }

    public void setCodeA(String codeA) {
        this.codeA = codeA;
    }

    public String getCode_b() {
        return code_b;
    }

    public void setCode_b(String code_b) {
        this.code_b = code_b;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public float getPa() {
        return pa;
    }

    public void setPa(float pa) {
        this.pa = pa;
    }

    public float getPmin() {
        return pmin;
    }

    public void setPmin(float pmin) {
        this.pmin = pmin;
    }

    public float getPmax() {
        return pmax;
    }

    public void setPmax(float pmax) {
        this.pmax = pmax;
    }

    public float getPv() {
        return pv;
    }

    public void setPv(float pv) {
        this.pv = pv;
    }

    public float getPvd() {
        return pvd;
    }
    public void setPvd(float pvd) {
        this.pvd = pvd;
    }

    public int getTva() {
        return tva;
    }

    public void setTva(int tva) {
        this.tva = tva;
    }

    public int getFodec() {
        return fodec;
    }

    public void setFodec(int fodec) {
        this.fodec = fodec;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStkinit() {
        return stkinit;
    }

    public void setStkinit(int stkinit) {
        this.stkinit = stkinit;
    }

    public int getStkajouter() {
        return stkajouter;
    }

    public void setStkajouter(int stkajouter) {
        this.stkajouter = stkajouter;
    }

    public String getCodeCateg() {
        return codeCateg;
    }

    public void setCodeCateg(String codeCateg) {
        this.codeCateg = codeCateg;
    }

    public String getCodeScateg() {
        return codeScateg;
    }

    public void setCodeScateg(String codeScateg) {
        this.codeScateg = codeScateg;
    }

    public Long getIdMagasin() {
        return idMagasin;
    }

    public void setIdMagasin(Long idMagasin) {
        this.idMagasin = idMagasin;
    }

    public boolean isDispo() {
        return dispo;
    }

    public void setDispo(boolean dispo) {
        this.dispo = dispo;
    }

    public LocalDate getCree() {
        return cree;
    }

    public void setCree(LocalDate cree) {
        this.cree = cree;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Long getExpiration() {
        return expiration;
    }

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }

    public float getMb() {
        return mb;
    }

    public void setMb(float mb) {
        this.mb = mb;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", codeA='" + codeA + '\'' +
                ", code_b='" + code_b + '\'' +
                ", libelle='" + libelle + '\'' +
                ", pa=" + pa +
                ", pmin=" + pmin +
                ", pmax=" + pmax +
                ", pv=" + pv +
                ", tva=" + tva +
                ", fodec=" + fodec +
                ", stock=" + stock +
                ", stkinit=" + stkinit +
                ", stkajouter=" + stkajouter +
                ", codeCateg='" + codeCateg + '\'' +
                ", codeScateg='" + codeScateg + '\'' +
                ", idMagasin=" + idMagasin +
                ", dispo=" + dispo +
                ", cree=" + cree +
                ", local='" + local + '\'' +
                ", expiration=" + expiration +
                ", mb=" + mb +
                '}';
    }
}
