package boutiqueProjet.org.Repository;

import boutiqueProjet.org.Entity.Llivr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface LlivrRepository extends JpaRepository<Llivr, Long> {
    Iterable<Llivr> findAllByNumero(String numero);

    @Modifying
    @Transactional
    @Query("delete from Llivr e where numero = ?1")
    void deleteByNumero(String numero);

    List<Llivr> findAllBynumero(String numero);
}
