package boutiqueProjet.org.Repository;

import boutiqueProjet.org.Entity.Cposte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CposteRepository extends JpaRepository<Cposte, Long> {
	Optional<Cposte> findByAnnee(int annee);

}
