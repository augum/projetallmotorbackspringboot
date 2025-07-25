package boutiqueProjet.org.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "lrecouv")
public class Lrecouv {
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	  private int numero;
	  private String mode_reg;
	  private String nump;
	  private String banque;
	  private Date echeance;
	  private Date date_reg;
	  private float montant;
	  @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	  @JsonBackReference
	  private Recouv recouv;
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
	public String getMode_reg() {
		return mode_reg;
	}
	public void setMode_reg(String mode_reg) {
		this.mode_reg = mode_reg;
	}
	public String getNump() {
		return nump;
	}
	public void setNump(String nump) {
		this.nump = nump;
	}
	public String getBanque() {
		return banque;
	}
	public void setBanque(String banque) {
		this.banque = banque;
	}
	public Date getEcheance() {
		return echeance;
	}
	public void setEcheance(Date echeance) {
		this.echeance = echeance;
	}
	public Date getDate_reg() {
		return date_reg;
	}
	public void setDate_reg(Date date_reg) {
		this.date_reg = date_reg;
	}
	public float getMontant() {
		return montant;
	}
	public void setMontant(float montant) {
		this.montant = montant;
	}
	public Recouv getRecouv() {
		return recouv;
	}
	public void setRecouv(Recouv recouv) {
		this.recouv = recouv;
	}
	public Lrecouv(long id, int numero, String mode_reg, String nump, String banque, Date echeance, Date date_reg,
			float montant, Recouv recouv) {
		super();
		this.id = id;
		this.numero = numero;
		this.mode_reg = mode_reg;
		this.nump = nump;
		this.banque = banque;
		this.echeance = echeance;
		this.date_reg = date_reg;
		this.montant = montant;
		this.recouv = recouv;
	}
	public Lrecouv() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Lrecouv [id=" + id + ", numero=" + numero + ", mode_reg=" + mode_reg + ", nump=" + nump + ", banque="
				+ banque + ", echeance=" + echeance + ", date_reg=" + date_reg + ", montant=" + montant + ", recouv="
				+ recouv + "]";
	}

}
