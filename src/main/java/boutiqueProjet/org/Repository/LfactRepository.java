package boutiqueProjet.org.Repository;

import boutiqueProjet.org.Entity.Lfact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LfactRepository extends JpaRepository<Lfact, Long> {

}

