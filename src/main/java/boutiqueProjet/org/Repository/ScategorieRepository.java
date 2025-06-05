package boutiqueProjet.org.Repository;

import boutiqueProjet.org.Entity.Scategorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
@CrossOrigin("*")
public interface ScategorieRepository extends JpaRepository<Scategorie, Long> {
	 @Query("SELECT t FROM Scategorie t where t.code_categ = :code")
	    public List<Scategorie> findByCateg(@Param("code") String code);
	public Optional<Scategorie> findByCode(String code);
}