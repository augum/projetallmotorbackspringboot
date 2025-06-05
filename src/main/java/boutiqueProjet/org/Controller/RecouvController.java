package boutiqueProjet.org.Controller;

import boutiqueProjet.org.Entity.Compteur;
import boutiqueProjet.org.Entity.Lrecouv;
import boutiqueProjet.org.Entity.Recouv;
import boutiqueProjet.org.Exception.ResourceNotFoundException;
import boutiqueProjet.org.Repository.CompteurRepository;
import boutiqueProjet.org.Repository.LrecouvRepository;
import boutiqueProjet.org.Repository.RecouvRepository;
import com.fasterxml.jackson.databind.JsonMappingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;

import java.util.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class RecouvController {
	@Autowired
	RecouvRepository repository;
	LrecouvRepository repo;
	@Autowired
	CompteurRepository comptrepo;
	@Autowired
    ServletContext context;
	 @GetMapping("/Recouvs")
	  public List<Recouv> getAllRecouvs() {
	    System.out.println("Get all Recouvs...");
	    List<Recouv> Recouvs = new ArrayList<>();
	    repository.findAll().forEach(Recouvs::add);
	    return Recouvs;
	  }
	
	@GetMapping("/Recouvs/{id}")
	public ResponseEntity<Recouv> getRecouvById(@PathVariable(value = "id") Long RecouvId)
			throws ResourceNotFoundException {
		Recouv Recouv = repository.findById(RecouvId)
				.orElseThrow(() -> new ResourceNotFoundException("Recouv not found for this id :: " + RecouvId));
		return ResponseEntity.ok().body(Recouv);
	}

	@PostMapping("/Recouvs")
	public ResponseEntity<Recouv> createRecouf( @RequestBody Recouv Recouv)  throws JsonParseException, JsonMappingException , Exception{
		  repository.save(Recouv);
		  List<Lrecouv> lrecouvs = Recouv.getLrecouvs();
		    for (Lrecouv lc : lrecouvs) {
	          	lc.setNumero(Recouv.getNumero());
	          	repo.save(lc);
		       }	 
		    Optional<Compteur> CompteurInfo = comptrepo.findByAnnee(Recouv.getAnnee());
	     	if (CompteurInfo.isPresent()) {
		    	Compteur compteur = CompteurInfo.get();
		           compteur.setNumregf(compteur.getNumregf()+1);
		         compteur =   comptrepo.save(compteur);
	     	}
			 return new ResponseEntity<>(HttpStatus.OK);
		} 

	@DeleteMapping("/Recouvs/{id}")
	public Map<String, Boolean> deleteRecouv(@PathVariable(value = "id") Long RecouvId)
			throws ResourceNotFoundException {
		Recouv Recouv = repository.findById(RecouvId)
				.orElseThrow(() -> new ResourceNotFoundException("Recouv not found  id :: " + RecouvId));
		repository.delete(Recouv);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	 
	  @DeleteMapping("/Recouvs/delete")
	  public ResponseEntity<String> deleteAllRecouvs() {
	    System.out.println("Delete All Recouvs...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All Recouvs have been deleted!", HttpStatus.OK);
	  }
	 
	  @PutMapping("/Recouvs/{id}")
	  public ResponseEntity<Recouv> updateRecouv(@PathVariable("id") long id, @RequestBody Recouv Recouv) {
	    System.out.println("Update Recouv with ID = " + id + "...");
	    Optional<Recouv> RecouvInfo = repository.findById(id);
	    if (RecouvInfo.isPresent()) {
	    	Recouv recouv = RecouvInfo.get();
	    	recouv.setLibelle(Recouv.getLibelle());
	      return new ResponseEntity<>(repository.save(Recouv), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }		
}
