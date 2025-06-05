package boutiqueProjet.org.Repository;

import boutiqueProjet.org.Entity.LcommService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LcommServiceRepository  extends JpaRepository<LcommService, Long> {

}
