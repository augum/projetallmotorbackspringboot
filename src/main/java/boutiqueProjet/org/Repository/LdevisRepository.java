package boutiqueProjet.org.Repository;

import boutiqueProjet.org.Entity.Ldevis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LdevisRepository extends JpaRepository<Ldevis, Long> {

}
