package boutiqueProjet.org.Entity;

import javax.persistence.*;

@Entity
@Table(name = "Utilisateur")
public class Utilisateur {
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	  private String nom;
	  private String role;
	  private String login;
	  private String pwd;
	  private String libelleMagasin;
	  private Long idMagasin;
	public Utilisateur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Utilisateur(long id, String nom, String role, String login, String pwd, String libelleMagasin, Long idMagasin) {
		this.id = id;
		this.nom = nom;
		this.role = role;
		this.login = login;
		this.pwd = pwd;
		this.libelleMagasin = libelleMagasin;
		this.idMagasin = idMagasin;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getLibelleMagasin() {
		return libelleMagasin;
	}

	public void setLibelleMagasin(String libelleMagasin) {
		this.libelleMagasin = libelleMagasin;
	}

	public Long getIdMagasin() {
		return idMagasin;
	}

	public void setIdMagasin(Long idMagasin) {
		this.idMagasin = idMagasin;
	}

	@Override
	public String toString() {
		return "Utilisateur{" +
				"id=" + id +
				", nom='" + nom + '\'' +
				", role='" + role + '\'' +
				", login='" + login + '\'' +
				", pwd='" + pwd + '\'' +
				", libelleMagasin='" + libelleMagasin + '\'' +
				", idMagasin=" + idMagasin +
				'}';
	}
}