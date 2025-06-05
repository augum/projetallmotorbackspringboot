package boutiqueProjet.org.Repository;

import boutiqueProjet.org.Entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin("*")
public interface ClientRepository extends JpaRepository<Client, Long> {

}

