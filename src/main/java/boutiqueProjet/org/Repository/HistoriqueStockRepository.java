package boutiqueProjet.org.Repository;

import boutiqueProjet.org.Entity.HistoriqueStock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoriqueStockRepository extends JpaRepository<HistoriqueStock, Long> {
    List<HistoriqueStock> findAllByarticle(String libelle);
}
