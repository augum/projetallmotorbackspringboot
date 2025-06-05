package boutiqueProjet.org.Repository;

import boutiqueProjet.org.Entity.Lavoir;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LavoirRepository extends JpaRepository<Lavoir, Long> {

}
