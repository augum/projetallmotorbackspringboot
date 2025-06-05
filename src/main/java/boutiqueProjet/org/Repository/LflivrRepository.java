package boutiqueProjet.org.Repository;

import boutiqueProjet.org.Entity.Lflivr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LflivrRepository extends JpaRepository<Lflivr, Long> {

}