package boutiqueProjet.org.Repository;

import boutiqueProjet.org.Entity.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("*")
@Repository
public interface CompteRepository extends JpaRepository<Compte, Long> {

}