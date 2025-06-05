package boutiqueProjet.org.Repository;

import boutiqueProjet.org.Entity.Residence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResidenceRepository extends JpaRepository<Residence, Long> {

	 @Query("SELECT t FROM Residence t where t.code_dir = :code_dir")
	    public List<Residence> findByResidence(@Param("code_dir") String code_dir);

}