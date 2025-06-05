package boutiqueProjet.org.Repository;

import boutiqueProjet.org.Entity.Livr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LivrRepository extends JpaRepository<Livr, Long> {
    @Query("select c from Livr c where c.mag= :mag and c.date = :date order by c.id desc")
    public List<Livr> chercherCommandparMagasinetDate(@Param("mag") String mag, @Param("date") LocalDate date);

    @Query("select c from Livr c where c.mag= :mag and c.date = :date and c.idUtilisateur = :idUtilisateur order by c.id desc")
    public List<Livr> chercherCommandparMagasinetDateU(@Param("mag") String mag, @Param("date") LocalDate date,@Param("idUtilisateur") String idUtilisateur);

}
