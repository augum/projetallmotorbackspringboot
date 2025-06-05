package boutiqueProjet.org.Controller;

import boutiqueProjet.org.Entity.Compteur;
import boutiqueProjet.org.Entity.Favoir;
import boutiqueProjet.org.Entity.Lfavoir;
import boutiqueProjet.org.Exception.ResourceNotFoundException;
import boutiqueProjet.org.Repository.CompteurRepository;
import boutiqueProjet.org.Repository.FavoirRepository;
import boutiqueProjet.org.Repository.LfavoirRepository;
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
public class FavoirController {
	@Autowired
	FavoirRepository repository;
	LfavoirRepository repo;
	@Autowired
	CompteurRepository comptrepo;
	@Autowired
    ServletContext context;
	 @GetMapping("/favoirs")
	  public List<Favoir> getAllFavoirs() {
	    System.out.println("Get all Favoirs...");
	    List<Favoir> Favoirs = new ArrayList<>();
	    repository.findAll().forEach(Favoirs::add);
	    return Favoirs;
	  }
	
	@GetMapping("/favoirs/{id}")
	public ResponseEntity<Favoir> getFavoirById(@PathVariable(value = "id") Long FavoirId)
			throws ResourceNotFoundException {
		Favoir Favoir = repository.findById(FavoirId)
				.orElseThrow(() -> new ResourceNotFoundException("Favoir not found for this id :: " + FavoirId));
		return ResponseEntity.ok().body(Favoir);
	}

	@PostMapping("/favoirs")
	public ResponseEntity<Favoir> createAvoir( @RequestBody Favoir Favoir)  throws JsonParseException, JsonMappingException , Exception{
		repository.save(Favoir);
		List<Lfavoir> lfavoirs = Favoir.getLfavoirs();
	    for (Lfavoir lc : lfavoirs) {
	        lc.setNumero(Favoir.getNumero());
       		repo.save(lc);
	       }	 
     Optional<Compteur> CompteurInfo = comptrepo.findByAnnee(Favoir.getAnnee());
     	if (CompteurInfo.isPresent()) {
	    	Compteur compteur = CompteurInfo.get();
	           compteur.setNumav(compteur.getNumavf()+1);
	         compteur =   comptrepo.save(compteur);
     	}
		 return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/favoirs/{id}")
	public Map<String, Boolean> deleteFavoir(@PathVariable(value = "id") Long FavoirId)
			throws ResourceNotFoundException {
		Favoir Favoir = repository.findById(FavoirId)
				.orElseThrow(() -> new ResourceNotFoundException("Favoir not found  id :: " + FavoirId));
		repository.delete(Favoir);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	 
	  @DeleteMapping("/favoirs/delete")
	  public ResponseEntity<String> deleteAllFavoirs() {
	    System.out.println("Delete All Favoirs...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All Favoirs have been deleted!", HttpStatus.OK);
	  }
	 
	  @PutMapping("/favoirs/{id}")
	  public ResponseEntity<Favoir> updateFavoir(@PathVariable("id") long id, @RequestBody Favoir Favoir) {
	    System.out.println("Update Favoir with ID = " + id + "...");
	    Optional<Favoir> FavoirInfo = repository.findById(id);
	    if (FavoirInfo.isPresent()) {
	    	Favoir favoir = FavoirInfo.get();
	    	favoir.setLibelle(Favoir.getLibelle());
	      return new ResponseEntity<>(repository.save(Favoir), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
}
