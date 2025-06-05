package boutiqueProjet.org.Repository;

import boutiqueProjet.org.Entity.Fact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactRepository extends JpaRepository<Fact, Long> {

}
