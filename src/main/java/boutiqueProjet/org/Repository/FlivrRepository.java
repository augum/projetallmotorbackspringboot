package boutiqueProjet.org.Repository;

import boutiqueProjet.org.Entity.Flivr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlivrRepository extends JpaRepository<Flivr, Long> {

}