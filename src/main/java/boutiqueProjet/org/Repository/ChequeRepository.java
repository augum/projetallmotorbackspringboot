package boutiqueProjet.org.Repository;

import boutiqueProjet.org.Entity.Cheque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChequeRepository extends JpaRepository<Cheque, Long> {

}
