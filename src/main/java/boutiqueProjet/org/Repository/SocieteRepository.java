package boutiqueProjet.org.Repository;

import boutiqueProjet.org.Entity.Societe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocieteRepository extends JpaRepository<Societe, Long> {

}