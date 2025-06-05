package boutiqueProjet.org.Repository;

import boutiqueProjet.org.Entity.LconsSonede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LconsSonedeRepository extends JpaRepository<LconsSonede, Long> {

}