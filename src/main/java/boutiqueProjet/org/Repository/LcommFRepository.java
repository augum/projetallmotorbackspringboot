package boutiqueProjet.org.Repository;

import boutiqueProjet.org.Entity.LcommF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface LcommFRepository extends JpaRepository<LcommF, Long> {

    Iterable<LcommF> findAllByNumero(int numero);

    @Modifying
    @Transactional
    @Query("delete from LcommF e where numero = ?1")
    void deleteByNumero(int numero);

    List<LcommF> findAllBynumero(int numero);
}