package boutiqueProjet.org.Repository;

import boutiqueProjet.org.Entity.Lfavoir;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LfavoirRepository extends JpaRepository<Lfavoir, Long> {

}
