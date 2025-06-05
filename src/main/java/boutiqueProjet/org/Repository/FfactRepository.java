package boutiqueProjet.org.Repository;

import boutiqueProjet.org.Entity.Ffact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FfactRepository extends JpaRepository<Ffact, Long> {

}
