package boutiqueProjet.org.Repository;

import boutiqueProjet.org.Entity.Comm;
import boutiqueProjet.org.Entity.Lcomm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RepositoryRestResource
public interface CommRepository extends JpaRepository<Comm, Long> {
   @Query("select c from Comm c where c.mag= :mag and c.date = :date and c.modepayement= :modepayement order by c.id desc")
    public List<Comm> chercherCommandparMagasinetDate(@Param("mag") String mag,@Param("date") LocalDate date,@Param("modepayement") String modepayement);

   @Query("select c from Comm c where c.mag= :mag and c.date = :date and c.modepayement= :modepayement and c.idUtilisateur= :idUtilisateur order by c.id desc")
    public List<Comm> chercherCommandparMagasinetDateU(@Param("mag") String mag,@Param("date") LocalDate date,@Param("modepayement") String modepayement,@Param("idUtilisateur") String idUtilisateur);

   @Query("select SUM(c.mt) as total from Comm  c where c.mag= :mag and c.date = :date and c.modepayement= :modepayement")
   public double sommeS(@Param("mag") String mag,@Param("date") LocalDate date,@Param("modepayement") String modepayement);

   @Query("select SUM(c.mt) as total from Comm  c where c.mag= :mag and c.date = :date and c.modepayement= :modepayement and c.idUtilisateur= :idUtilisateur")
    public double sommeSC(@Param("mag") String mag,@Param("date") LocalDate date,@Param("modepayement") String modepayement,@Param("idUtilisateur") String idUtilisateur);

    @Query("select SUM(c.totht) as totalE from Lcomm c where c.local= :local and c.date = :date and c.idMagasin= :mag and c.modePayement=:mode ")
    public double sommeSE(@Param("local") String local,@Param("date") LocalDate date,@Param("mag") String mag,@Param("mode") String mode);

    @Query("select c from Comm c where c.mag= :mag ")
    public List<Comm> listedette(@Param("mag") String mag);

   @Query("select c from Comm c where c.numero= :numero ")
   public List<Comm> listeNumero(@Param("numero") String numero);

    @Query("select c from Comm c where c.lib_client like %:lib_client% ")
    public List<Comm> listeNom(@Param("lib_client") String lib_client);

    @Query("select c from Comm c where c.date= :date ")
    public List<Comm> listeDate(@Param("date") LocalDate date);

    @Query("select c from Comm c where c.mag= :mag and c.lib_client like %:client% ")
    public List<Comm> listedetteN(@Param("mag") String mag,@Param("client") String client);

    @Query("select c from Comm c where c.mag= :mag and c.date between :date1 and :date2 and c.modepayement= :modepayement order by c.id desc ")
    public List<Comm> chercherCommandparMagasinetDateIn(@Param("mag") String mag,@Param("date1") LocalDate date1,@Param("date2") LocalDate date2,@Param("modepayement") String modepayement);

    @Query("select SUM(c.mt) as total from Comm c  where c.mag= :mag and c.date between :date1 and :date2 and c.modepayement= :modepayement")
    public double somme(@Param("mag") String mag, @Param("date1") LocalDate date1, @Param("date2") LocalDate date2,@Param("modepayement") String modepayement);

    @Query("select SUM(c.totht) as totalE from Lcomm c where c.local= :local and c.date between :date1 and :date2 and c.idMagasin= :mag and c.modePayement=:mode ")
    public double sommeSEd(@Param("local") String local,@Param("date1") LocalDate date1, @Param("date2") LocalDate date2,@Param("mag") String mag,@Param("mode") String mode);

    @Query("select SUM(c.totttc) as totalT from Lcomm c where c.client= :client and c.date between :date1 and :date2")
    public double sommeparclient(@Param("client") String local,@Param("date1") LocalDate date1, @Param("date2") LocalDate date2);

    @Query("select c from Lcomm c where c.client= :client and c.date between :date1 and :date2 ")
    public List<Lcomm> listeparclient(@Param("client") String client, @Param("date1") LocalDate date1, @Param("date2") LocalDate date2);


    @RestResource(path="/commBymagDate")
    List<Comm> findByMagAndDate(@Param("mag")String mag,@Param("date")  LocalDate date);

    //synchro
    @Query("select c from Comm c where c.mag= :mag and c.date between :date1 and :date2 order by c.id desc ")
    public List<Comm> chercherCommandparMagasinetDateInSycro(@Param("mag") String mag,@Param("date1") LocalDate date1,@Param("date2") LocalDate date2);

    Optional<Comm> findByNumero(String numero);
}
