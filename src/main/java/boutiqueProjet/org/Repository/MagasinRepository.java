package boutiqueProjet.org.Repository;

import boutiqueProjet.org.Entity.Magasin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin("*")
public interface MagasinRepository extends JpaRepository<Magasin,Long> {
    @Query("select libelle from Magasin m where m.id= :id")
    public Magasin lib(@Param("id") Long id);

}
