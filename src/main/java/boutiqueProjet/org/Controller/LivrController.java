package boutiqueProjet.org.Controller;

import boutiqueProjet.org.Entity.Article;
import boutiqueProjet.org.Entity.Compteur;
import boutiqueProjet.org.Entity.Livr;
import boutiqueProjet.org.Entity.Llivr;
import boutiqueProjet.org.Exception.ResourceNotFoundException;
import boutiqueProjet.org.Repository.ArticleRepository;
import boutiqueProjet.org.Repository.CompteurRepository;
import boutiqueProjet.org.Repository.LivrRepository;
import boutiqueProjet.org.Repository.LlivrRepository;
import com.fasterxml.jackson.databind.JsonMappingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


@RestController
@RequestMapping("/api")
public class LivrController {
	@Autowired
	LivrRepository repository;
    @Autowired
	ArticleRepository articleRepository;
	@Autowired
	LlivrRepository repo;
	@Autowired
	CompteurRepository comptrepo;
	@Autowired
    ServletContext context;
	 @GetMapping("/Livrs")
	  public List<Livr> getAllLivrs() {
	    System.out.println("Get all Livrs...");
	    List<Livr> Livrs = new ArrayList<>();
	    repository.findAll().forEach(Livrs::add);
	    return Livrs;
	  }
	@GetMapping("/livrsd")
	public List<Livr> getAllCommsT(@RequestParam(name = "mag") String mag, @RequestParam(name = "date") String dateoString ) {
		System.out.println("Get all livraison...");
		List<Livr> Livrs = new ArrayList<>();
		LocalDate dateo = LocalDate.parse(dateoString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		repository.chercherCommandparMagasinetDate(mag,dateo).forEach(Livrs::add);
		return Livrs;
	}
	@GetMapping("/livrsdu")
	public List<Livr> getAllCommsT(@RequestParam(name = "mag") String mag, @RequestParam(name = "date") String dateoString,@RequestParam(name = "idutilisateur") String idutilisateur ) {
		System.out.println("Get all livraison...");
		List<Livr> Livrs = new ArrayList<>();
		LocalDate dateo = LocalDate.parse(dateoString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		repository.chercherCommandparMagasinetDateU(mag,dateo,idutilisateur).forEach(Livrs::add);
		return Livrs;
	}
	@GetMapping("/Livrs/{id}")
	public ResponseEntity<Livr> getLivrById(@PathVariable(value = "id") Long LivrId)
			throws ResourceNotFoundException {
		Livr Livr = repository.findById(LivrId)
				.orElseThrow(() -> new ResourceNotFoundException("Livr not found for this id :: " + LivrId));
		return ResponseEntity.ok().body(Livr);
	}

	@PostMapping("/Livrs")
	public ResponseEntity<Livr> createBs1016( @RequestBody Livr Livr)  throws JsonParseException, JsonMappingException , Exception{
		  repository.save(Livr);
		  List<Llivr> llivrs = Livr.getLcomms();
		    for (Llivr lc : llivrs) {
	          	lc.setNumero(Livr.getNumero());
	          	Optional<Article> ArticleInfo = articleRepository.findByCode(lc.getCode_article());
	          	if(ArticleInfo.isPresent()){
	          	    Article Art = ArticleInfo.get();
	          	    Art.setStock(Art.getStock()-lc.getQte());
	          	    Art = articleRepository.save(Art);
                }
	          	repo.save(lc);
		       }	 
		    Optional<Compteur> CompteurInfo = comptrepo.findByAnnee(Livr.getAnnee());
	     	/*if (CompteurInfo.isPresent()) {
		    	Compteur compteur = CompteurInfo.get();
		           compteur.setNumbl(compteur.getNumbl()+1);
		         compteur =   comptrepo.save(compteur);
	     	}*/
			 return new ResponseEntity<>(HttpStatus.OK);
		} 

	@DeleteMapping("/Livrs/{id}")
	public Map<String, Boolean> deleteLivr(@PathVariable(value = "id") Long LivrId)
			throws ResourceNotFoundException {
		Livr Livr = repository.findById(LivrId)
				.orElseThrow(() -> new ResourceNotFoundException("Livr not found  id :: " + LivrId));
		repository.delete(Livr);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	 
	  @DeleteMapping("/Livrs/delete")
	  public ResponseEntity<String> deleteAllLivrs() {
	    System.out.println("Delete All Livrs...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All Livrs have been deleted!", HttpStatus.OK);
	  }
	 
	  @PutMapping("/Livrs/{id}")
	  public ResponseEntity<Livr> updateLivr(@PathVariable("id") long id, @RequestBody Livr Livr) {
	    System.out.println("Update Livr with ID = " + id + "...");
	    Optional<Livr> LivrInfo = repository.findById(id);
	    if (LivrInfo.isPresent()) {
	    	Livr livr = LivrInfo.get();
	    	livr.setLibelle(Livr.getLibelle());
	      return new ResponseEntity<>(repository.save(Livr), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }	
}
