package boutiqueProjet.org.Repository;

import boutiqueProjet.org.Entity.CommF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDate;
import java.util.List;
@CrossOrigin("*")
@RepositoryRestResource
public interface CommFRepository extends JpaRepository<CommF, Long> {
    @Query("select c from CommF c where c.mag= :mag and c.date = :date and c.modepayement= :modepayement")
    public List<CommF> chercherCommandparMagasinetDate(@Param("mag") String mag,@Param("date") LocalDate date,@Param("modepayement") String modepayement);

    @Query("select SUM(c.totttc) as total from CommF c where c.mag= :mag and c.date = :date and c.modepayement= :modepayement ")
    public double sommeS(@Param("mag") String mag,@Param("date") LocalDate date,@Param("modepayement") String modepayement);

    @Query("select c from CommF c where c.mag= :mag ")
    public List<CommF> listedette(@Param("mag") String mag);

    @Query("select c from CommF c where c.mag= :mag and c.lib_client like %:client% ")
    public List<CommF> listedetteN(@Param("mag") String mag,@Param("client") String client);


    @Query("select c from CommF c where c.mag= :mag and c.date between :date1 and :date2 and c.modepayement= :modepayement ")
    public List<CommF> chercherCommandparMagasinetDateIn(@Param("mag") String mag,@Param("date1") LocalDate date1,@Param("date2") LocalDate date2,@Param("modepayement") String modepayement);

    @Query("select SUM(c.totttc) as total from CommF c where c.mag= :mag and c.date between :date1 and :date2 and c.modepayement= :modepayement ")
    public double somme(@Param("mag") String mag, @Param("date1") LocalDate date1, @Param("date2") LocalDate date2,@Param("modepayement") String modepayement);

    @RestResource(path="/commFBymagDate")
    List<CommF> findByMagAndDate(@Param("mag")String mag,@Param("date")  LocalDate date);

    @Query("select c from CommF c where c.numero=:num")
    List<CommF> findBynum(@Param("num")int num);
}
