package boutiqueProjet.org.Controller;

import boutiqueProjet.org.Entity.Article;
import boutiqueProjet.org.Entity.HistoriqueStock;
import boutiqueProjet.org.Exception.ResourceNotFoundException;
import boutiqueProjet.org.Repository.ArticleRepository;
import boutiqueProjet.org.Repository.HistoriqueStockRepository;
import boutiqueProjet.org.Repository.ScategorieRepository;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import java.time.LocalDate;
import java.util.*;


@RestController
@RequestMapping("/api")
public class ArticleController {
	@Autowired
	ArticleRepository repository;
	@Autowired
	ScategorieRepository scatrepository;
	@Autowired
    ServletContext context;
	@Autowired
	HistoriqueStockRepository repositoryHistorie;
	@CrossOrigin("*")
	 @GetMapping("/article")
	  public List<Article> getAllarticles() {
	     System.out.println("Get all articles...");
	 
	    List<Article> articles = new ArrayList<>();
	    repository.findAll().forEach(articles::add);
	 
	    return articles;
	  }

    @GetMapping("/articlem/{numero}")
    public List<Article> getLlivrBynumero(@PathVariable(value = "numero") Long idMagasin)
    {
        List<Article> Lcomm = new ArrayList<>();
        return repository.findAllByidMagasin(idMagasin);
    }

	@GetMapping("/articlem12")
	public List<String> getLliM12()
	{
		List<String> Lcomm = new ArrayList<>();
		return repository.ArticleM12();
	}
	@GetMapping("/articlemag")
	public List<Article> getAllCommsCreditN(@RequestParam(name = "mag") Long mag,@RequestParam(name = "article") String article,@RequestParam(name = "local") String local) {
		System.out.println("Get all article by name and magasin");
		List<Article> Articles = new ArrayList<>();
		repository.ArticleM(mag,article,local).forEach(Articles::add);
		for (Article art: Articles
			 ) {
			/*LocalDate dateex= art.getCree();
            LocalDate dateNow= LocalDate.now();
			Long diff = ChronoUnit.DAYS.between(dateNow,dateex);*/

			//art.setExpiration(diff);
			art.setStock(art.getStock());
			art.setDispo(art.isDispo());
			art.setLibelle(art.getLibelle());
			art.setExpiration(art.getExpiration());
			art.setPv(art.getPv());
			art.setPa((art.getPa()));
			art.setPmax(art.getPmax());
			art.setPmin((art.getPmin()));
			art.setCodeCateg(art.getCodeCateg());

			repository.save(art);

		}
		return Articles;
	}
	@GetMapping("/articlemag2")
	public List<Article> getAllCommsCreditN2() {
	    /*affiche la liste des produit en ordre croissant
	     methode à tester
	     */
		System.out.println("Get all article by name and magasin");
		List<Article> Articles = new ArrayList<>();
		repository.ArticleM2().forEach(Articles::add);
		return Articles;
	}
	@GetMapping("/articlemag1")
	public List<Article> getAllCommsCreditN1(@RequestParam(name = "mag") Long mag,@RequestParam(name = "local") String local) {
		System.out.println("Get all article by name and magasin");
		List<Article> Articles = new ArrayList<>();
		repository.ArticleM1(mag,local).forEach(Articles::add);
		return Articles;
	}
	 @GetMapping("/getAll")
     public List<Article> getAllCategories() {
         System.out.println("Get all Categories...");
         List<Article> Article = new ArrayList<>();
         repository.findAll().forEach(Article::add);
         return Article;
     }
	@DeleteMapping("/articled")
	public List<Article> deleteArticles(@RequestParam(name = "article") String article)
			throws ResourceNotFoundException {
		List<Article> Articles = new ArrayList<>();
		repository.deleteLibelle(article);
		return Articles;
	}

	 @PostMapping("/article")
     public Article createCategorie(@RequestBody Article article) {
		
         return repository.save(article);
     }
    /*
    * modifie tous les prix de vente
    * en fonction de la modification du taux de change
    * */
	@PostMapping("/articletaux")
	public void createTaux(@RequestBody int taux) {
		// modification des prix de vente des articles par rapport au taux de change
	}

	 @GetMapping("/article/{id}")
		public ResponseEntity<Article> getArticleById(@PathVariable(value = "id") Long Id)
				throws ResourceNotFoundException {
			Article Article = repository.findById(Id)
					.orElseThrow(() -> new ResourceNotFoundException("Categorie not found for this id :: " + Id));
			return ResponseEntity.ok().body(Article);
		}


	@DeleteMapping("/article/{id}")
	public Map<String, Boolean> deleteArticle(@PathVariable(value = "id") Long ArticleId)
			throws ResourceNotFoundException {
		Article Article = repository.findById(ArticleId)
				.orElseThrow(() -> new ResourceNotFoundException("Article not found  id :: " + ArticleId));
		repository.delete(Article);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	@DeleteMapping("/article/delete")
	  public ResponseEntity<String> deleteAllarticles() {
	    System.out.println("Delete All articles...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All articles have been deleted!", HttpStatus.OK);
	}

	  @PutMapping("/article/{id}")
	  public ResponseEntity<Article> updateArticle(@PathVariable("id") long id, @RequestBody Article Article) {
	    System.out.println("Update Article with ID = " + id + "...");
	    Optional<Article> ArticleInfo = repository.findById(id);
	    if (ArticleInfo.isPresent()) {
	    	Article article = ArticleInfo.get();
	           article.setLibelle(Article.getLibelle());
	           article.setCode_b(Article.getCode_b());
	           article.setCodeCateg(Article.getCodeCateg());
	           article.setCodeScateg(Article.getCodeScateg());
	           article.setPa(Article.getPa());
	           article.setPv(Article.getPv());
	           article.setTva(Article.getTva());
	           article.setStkinit(Article.getStkinit());
	           article.setStock(Article.getStock());
			   article.setFodec(Article.getFodec());
	           article.setDispo(Article.isDispo());
			   article.setPvd(Article.getPvd());
			HistoriqueStock historie = new HistoriqueStock();
			historie.setDate(LocalDate.now());
			historie.setArticle(Article.getLibelle());
			historie.setQte(Article.getStkajouter());
			repositoryHistorie.save(historie);

	      return new ResponseEntity<>(repository.save(Article), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	////////////

    @PutMapping("/articlel/{codea}")
    public ResponseEntity<Article> updateArticleLIb(@PathVariable("codea") String codea, @RequestBody Article Article) {
        System.out.println("Update Article with libelle = " + codea + "...");
        /* il faut remplacer findAllBylibelle(libelle) par FindAllcodeA(codea)*/
        List<Article> ArticleInfo = repository.findAllBycodeA(codea);
        for(Article ar:ArticleInfo){
			ar.setPv(Article.getPv());
			ar.setPmax(Article.getPmax());
			ar.setPmin(Article.getPmin());
			/* à corriger apres la modification des produits en ajoutant codeA*/
			ar.setLibelle(Article.getLibelle());
			repository.save(ar);
		}
        System.out.println(Article);
		return new ResponseEntity<>((Article), HttpStatus.OK);
    }

	@GetMapping("/sommeStockv")
	public double getSommeInv() {
		return repository.somme();
	}
//pour la synchronisation
	@PostMapping("/articlesynchro")
	public List<Article> save(@RequestBody List<Article> articles) throws JsonParseException, JsonMappingException, Exception
	{

		for(Article sychro: articles){
			Article article = new Article();
			Optional<Article> ArticleInfo = repository.findByCode(sychro.getCode());
			if (ArticleInfo.isPresent()) {
				Article Art = ArticleInfo.get();
				Art.setStock(sychro.getStock());
				Art.setStock(sychro.getStock());
				Art.setStkinit(sychro.getStkinit());
				Art.setStkajouter(sychro.getStkajouter());
				Art = repository.save(Art);
				System.out.println(Art.isDispo());
			}else {


				article.setCode(sychro.getCode());
				article.setLibelle(sychro.getLibelle());
				article.setPa(sychro.getPa());
				article.setPmin(sychro.getPmin());
				article.setPmax(sychro.getPmax());
				article.setPv(sychro.getPv());
				article.setTva(sychro.getTva());
				article.setFodec(sychro.getFodec());
				article.setStock(sychro.getStock());
				article.setStkajouter(sychro.getStkajouter());
				article.setStkinit(sychro.getStkinit());
				article.setStkajouter(sychro.getStkajouter());
				article.setCodeCateg(sychro.getCodeCateg());
				article.setIdMagasin(sychro.getIdMagasin());
				article.setDispo(sychro.isDispo());
				article.setCree(sychro.getCree());
				article.setLocal(sychro.getLocal());
				article.setExpiration(sychro.getExpiration());
				article.setMb(sychro.getMb());

				repository.save(article);
			}

			/*Optional<Article> oneArticle = repository.findByCode(sychro.getCode());
			if(oneArticle.isPresent()){
				repository.save(article);

			}else{
				repository.save(article);
			}*/


		}


		return null;
	}
}
