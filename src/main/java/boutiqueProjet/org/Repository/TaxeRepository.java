package boutiqueProjet.org.Repository;

import boutiqueProjet.org.Entity.Taxe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin("*")
public interface TaxeRepository extends JpaRepository<Taxe, Long> {
}
