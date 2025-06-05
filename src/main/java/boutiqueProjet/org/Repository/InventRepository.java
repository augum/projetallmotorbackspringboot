package boutiqueProjet.org.Repository;

import boutiqueProjet.org.Entity.Invent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventRepository extends JpaRepository<Invent, Long> {

}
