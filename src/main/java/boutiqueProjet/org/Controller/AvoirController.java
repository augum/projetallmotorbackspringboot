package boutiqueProjet.org.Controller;

import boutiqueProjet.org.Entity.Avoir;
import boutiqueProjet.org.Entity.Compteur;
import boutiqueProjet.org.Entity.Lavoir;
import boutiqueProjet.org.Exception.ResourceNotFoundException;
import boutiqueProjet.org.Repository.AvoirRepository;
import boutiqueProjet.org.Repository.CompteurRepository;
import boutiqueProjet.org.Repository.LavoirRepository;
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
public class AvoirController {
	@Autowired
	AvoirRepository repository;
	@Autowired
	LavoirRepository repo;
	@Autowired
	CompteurRepository comptrepo;
	@Autowired
    ServletContext context;
	 @GetMapping("/Avoirs")
	  public List<Avoir> getAllAvoirs() {
	    System.out.println("G et all Avoirs...");
	    List<Avoir> Avoirs = new ArrayList<>();
	    repository.findAll().forEach(Avoirs::add);
	    return Avoirs;
	  }
	
	@GetMapping("/Avoirs/{id}")
	public ResponseEntity<Avoir> getAvoirById(@PathVariable(value = "id") Long AvoirId)
			throws ResourceNotFoundException {
		Avoir Avoir = repository.findById(AvoirId)
				.orElseThrow(() -> new ResourceNotFoundException("Avoir not found for this id :: " + AvoirId));
		return ResponseEntity.ok().body(Avoir);
	}

	@PostMapping("/Avoirs")
		public ResponseEntity<Avoir> createAvoir( @RequestBody Avoir Avoir)  throws JsonParseException, JsonMappingException , Exception{
			repository.save(Avoir);
			List<Lavoir> lavoirs = Avoir.getLavoirs();
		    for (Lavoir lc : lavoirs) {
		        lc.setNumero(Avoir.getNumero());
	       		repo.save(lc);
		       }	 
	     Optional<Compteur> CompteurInfo = comptrepo.findByAnnee(Avoir.getAnnee());
	     	if (CompteurInfo.isPresent()) {
		    	Compteur compteur = CompteurInfo.get();
		           compteur.setNumav(compteur.getNumav()+1);
		         compteur =   comptrepo.save(compteur);
	     	}
			 return new ResponseEntity<>(HttpStatus.OK);
		}

	@DeleteMapping("/Avoirs/{id}")
	public Map<String, Boolean> deleteAvoir(@PathVariable(value = "id") Long AvoirId)
			throws ResourceNotFoundException {
		Avoir Avoir = repository.findById(AvoirId)
				.orElseThrow(() -> new ResourceNotFoundException("Avoir not found  id :: " + AvoirId));
		repository.delete(Avoir);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	 
	  @DeleteMapping("/Avoirs/delete")
	  public ResponseEntity<String> deleteAllAvoirs() {
	    System.out.println("Delete All Avoirs...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All Avoirs have been deleted!", HttpStatus.OK);
	  }
	 
	  @PutMapping("/Avoirs/{id}")
	  public ResponseEntity<Avoir> updateAvoir(@PathVariable("id") long id, @RequestBody Avoir Avoir) {
	    System.out.println("Update Avoir with ID = " + id + "...");
	    Optional<Avoir> AvoirInfo = repository.findById(id);
	    if (AvoirInfo.isPresent()) {
	    	Avoir avoir = AvoirInfo.get();
	    	avoir.setLibelle(Avoir.getLibelle());
	      return new ResponseEntity<>(repository.save(Avoir), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

}
