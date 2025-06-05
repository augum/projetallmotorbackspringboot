package boutiqueProjet.org.Repository;

import boutiqueProjet.org.Entity.LcommMag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.transaction.Transactional;
import java.util.List;

@RepositoryRestResource
public interface lcommMagRepository extends JpaRepository<LcommMag,Long> {
    Iterable<LcommMag> findAllByNumero(String numero);

    @Modifying
    @Transactional
    @Query("delete from Lcomm e where numero = ?1")
    void deleteByNumero(String numero);

    List<LcommMag> findAllBynumero(String numero);
}
