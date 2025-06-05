package boutiqueProjet.org.Repository;

import boutiqueProjet.org.Entity.LfausseF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.transaction.Transactional;
import java.util.List;

@RepositoryRestResource
public interface LfaussefRepository extends JpaRepository<LfausseF,Long> {
    Iterable<LfausseF> findAllByNumero(int numero);

    @Modifying
    @Transactional
    @Query("delete from Lcomm e where numero = ?1")
    void deleteByNumero(int numero);

    List<LfausseF> findAllBynumero(int numero);
}
