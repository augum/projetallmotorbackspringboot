package boutiqueProjet.org.Repository;

import boutiqueProjet.org.Entity.Recouv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecouvRepository extends JpaRepository<Recouv, Long> {

}
