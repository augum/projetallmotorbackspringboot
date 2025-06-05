package boutiqueProjet.org.Repository;

import boutiqueProjet.org.Entity.BLcomm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.transaction.Transactional;
import java.util.List;
@CrossOrigin("*")
@RepositoryRestResource
public interface BlcommRepository extends JpaRepository<BLcomm,Long> {
    Iterable<BLcomm> findAllByNumero(int numero);

    @Modifying
    @Transactional
    @Query("delete from Lcomm e where numero = ?1")
    void deleteByNumero(String numero);

    List<BLcomm> findAllBynumero(String numero);
}
