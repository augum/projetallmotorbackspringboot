package boutiqueProjet.org.Repository;

import boutiqueProjet.org.Entity.Compteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;
@CrossOrigin("*")
@Repository
public interface CompteurRepository extends JpaRepository<Compteur, Long> {

	
	Optional<Compteur> findByAnnee(int annee);

}