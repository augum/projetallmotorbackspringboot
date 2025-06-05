package boutiqueProjet.org.Repository;


import boutiqueProjet.org.Entity.UserPoste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPosteRepository extends JpaRepository<UserPoste, Long> {
	
	
	
}