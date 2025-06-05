package boutiqueProjet.org.Repository;

import boutiqueProjet.org.Entity.LconsSteg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LconsStegRepository extends JpaRepository<LconsSteg, Long> {

}
