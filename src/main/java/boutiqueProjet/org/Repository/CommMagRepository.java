package boutiqueProjet.org.Repository;

import boutiqueProjet.org.Entity.CommMag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDate;
import java.util.List;
@CrossOrigin("*")
@RepositoryRestResource
public interface CommMagRepository extends JpaRepository<CommMag,Long> {
    @Query("select c from CommMag c where c.mag= :mag and c.date = :date order by c.id desc")
    public List<CommMag> chercherCommandparMagasinetDate(@Param("mag") String mag, @Param("date") LocalDate date);
}
