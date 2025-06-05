package boutiqueProjet.org.Repository;

import boutiqueProjet.org.Entity.ConsSteg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsStegRepository extends JpaRepository<ConsSteg, Long> {

}

