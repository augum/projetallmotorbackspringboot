package boutiqueProjet.org.Repository;

import boutiqueProjet.org.Entity.CommService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("*")
@Repository
public interface CommServiceRepository extends JpaRepository<CommService, Long> {

}
