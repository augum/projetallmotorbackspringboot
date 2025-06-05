package boutiqueProjet.org.Repository;

import boutiqueProjet.org.Entity.Bcomm;
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
public interface BcommRepository extends JpaRepository<Bcomm,Long> {
    @Query("select c from Bcomm c where c.mag= :mag and c.date= :date order by c.id desc")
    public List<Bcomm> chercherCommandparMagasinetDate(@Param("mag") String mag,@Param("date") LocalDate date);

    @Query("select c from Bcomm c where c.libelle= :libelle and c.date= :date order by c.id desc")
    public List<Bcomm> chercherCommandparMagasinetDateL(@Param("libelle") String libelle,@Param("date") LocalDate date);

    @Query("select SUM(c.totttc) as total from Bcomm c where c.mag= :mag and c.date = :date and c.modepayement= :modepayement ")
    public double sommeS(@Param("mag") String mag,@Param("date") LocalDate date,@Param("modepayement") String modepayement);

    @Query("select c from Bcomm c where c.mag= :mag and c.date between :date1 and :date2 and c.modepayement= :modepayement order by c.id desc ")
    public List<Bcomm> chercherCommandparMagasinetDateIn(@Param("mag") String mag,@Param("date1") LocalDate date1,@Param("date2") LocalDate date2,@Param("modepayement") String modepayement);

    @Query("select SUM(c.totttc) as total from Bcomm c where c.mag= :mag and c.date between :date1 and :date2 and c.modepayement= :modepayement ")
    public double somme(@Param("mag") String mag, @Param("date1") LocalDate date1, @Param("date2") LocalDate date2,@Param("modepayement") String modepayement);

    @RestResource(path="/bcommBymagDate")
    List<Bcomm> findByMagAndDate(@Param("mag")String mag,@Param("date")  LocalDate date);
}
