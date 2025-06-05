package boutiqueProjet.org.Repository;

import boutiqueProjet.org.Entity.Lcomm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
@CrossOrigin("*")
@RepositoryRestResource
public interface LcommRepository extends JpaRepository<Lcomm, Long> {

	Iterable<Lcomm> findAllByNumero(String numero);
	
	@Modifying
    @Transactional
    @Query("delete from Lcomm e where numero = ?1")
    void deleteByNumero(String numero);

    List<Lcomm> findAllBynumero(String numero);
    List<Lcomm> findAllByclient(String client);

    @Query("select c.Libart,SUM(c.qte) as total from Lcomm c Group by c.Libart")
    List<Object[]> listeArt();

    @Query("select c.Libart, SUM(c.qte) as total from Lcomm c where c.date between :date1 and :date2 group by c.Libart")
    List<Object[]> chercherCommD(@Param("date1") LocalDate date1, @Param("date2") LocalDate date2);

    @Query("select c from Lcomm c where c.idMagasin= :mag and c.date between :date1 and :date2  order by c.id desc ")
    public List<Lcomm> chercherLCommandparMagasinetDateIn(@Param("mag") String mag,@Param("date1") LocalDate date1,@Param("date2") LocalDate date2);


    @Query("select c from Lcomm c where c.date between :date1 and :date2")
    public List<Lcomm> chercherCommDetail(@Param("date1") LocalDate date1, @Param("date2") LocalDate date2);

}