package boutiqueProjet.org.Repository;

import boutiqueProjet.org.Entity.Avoir;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvoirRepository extends JpaRepository<Avoir, Long> {

}