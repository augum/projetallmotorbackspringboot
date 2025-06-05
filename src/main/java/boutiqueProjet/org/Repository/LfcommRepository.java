package boutiqueProjet.org.Repository;

import boutiqueProjet.org.Entity.Lfcomm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LfcommRepository extends JpaRepository<Lfcomm, Long> {

}

