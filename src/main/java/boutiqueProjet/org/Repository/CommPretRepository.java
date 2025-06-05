package boutiqueProjet.org.Repository;

import boutiqueProjet.org.Entity.CommPret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin("*")
@RepositoryRestResource
public interface CommPretRepository extends JpaRepository<CommPret,Long> {
    @Query("select c from CommPret c where c.mag= :mag and c.date = :date")
    public List<CommPret> chercherCommandparMagasinetDateP(@Param("mag") String mag, @Param("date") LocalDate date);
}
