package boutiqueProjet.org.Entity;

import javax.persistence.*;

@Entity
@Table(name = "Taxe")
public class Taxe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int taux;

    public Taxe() {
    }

    public Taxe(long id, int taux) {
        this.id = id;
        this.taux = taux;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getTaux() {
        return taux;
    }

    public void setTaux(int taux) {
        this.taux = taux;
    }

    @Override
    public String toString() {
        return "Taxe{" +
                "id=" + id +
                ", taux=" + taux +
                '}';
    }
}
