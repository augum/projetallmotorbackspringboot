package boutiqueProjet.org.Repository;

import boutiqueProjet.org.Entity.FausseF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("*")
@RepositoryRestResource
public interface FausseFRepository extends JpaRepository<FausseF,Long> {
}
