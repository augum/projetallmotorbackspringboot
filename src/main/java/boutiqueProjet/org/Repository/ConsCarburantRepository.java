package boutiqueProjet.org.Repository;

import boutiqueProjet.org.Entity.ConsCarburant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ConsCarburantRepository extends JpaRepository<ConsCarburant, Long> {

	List<ConsCarburant> findByCode(int code_dir);
	
}