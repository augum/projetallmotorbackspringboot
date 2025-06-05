package boutiqueProjet.org.Repository;

import boutiqueProjet.org.Entity.Lrecouf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LrecoufRepository extends JpaRepository<Lrecouf, Long> {

}
