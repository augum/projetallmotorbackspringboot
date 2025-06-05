package boutiqueProjet.org.Repository;

import boutiqueProjet.org.Entity.Lrecouv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LrecouvRepository extends JpaRepository<Lrecouv, Long> {

}
