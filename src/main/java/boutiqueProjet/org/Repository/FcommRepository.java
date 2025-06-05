package boutiqueProjet.org.Repository;

import boutiqueProjet.org.Entity.Fcomm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FcommRepository extends JpaRepository<Fcomm, Long> {

}
