package boutiqueProjet.org.Repository;

import boutiqueProjet.org.Entity.Favoir;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoirRepository extends JpaRepository<Favoir, Long> {

}
