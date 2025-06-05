package boutiqueProjet.org.Repository;

import boutiqueProjet.org.Entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
@CrossOrigin("*")
public interface ArticleRepository extends JpaRepository<Article, Long> {

    Optional<Article> findByCode(String code_article);

    List<Article> findAllByidMagasin(Long idMagasin);
    List<Article> findAllBycodeA(String libelle);

    @Query("select a from Article a where a.idMagasin= :mag and a.libelle like %:article% and a.local=:local ")
    public List<Article> ArticleM(@Param("mag") Long mag, @Param("article") String article,@Param("local") String local);

    @Query("select a from Article a where a.idMagasin= :mag and a.local=:local")
    public List<Article> ArticleM1(@Param("mag") Long mag, @Param("local") String local);

    @Query("select distinct a.libelle from Article a order by a.libelle ")
    public List<String> ArticleM12();
    @Query("select a from Article a  order by a.libelle asc")
    public List<Article> ArticleM2();

    @Query("delete from Article a where a.libelle = : article")
    List<Article> deleteLibelle( @Param("article") String article);

    @Query("select SUM(a.pv * a.stock) as total from Article a ")
    public double somme();

}
