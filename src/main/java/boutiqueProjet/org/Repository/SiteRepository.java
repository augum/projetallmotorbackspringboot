package boutiqueProjet.org.Repository;

import boutiqueProjet.org.Entity.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SiteRepository extends JpaRepository<Site,Long> {
}
