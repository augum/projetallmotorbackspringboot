package boutiqueProjet.org.Controller;

import boutiqueProjet.org.Entity.Compteur;
import boutiqueProjet.org.Entity.Flivr;
import boutiqueProjet.org.Entity.Lflivr;
import boutiqueProjet.org.Exception.ResourceNotFoundException;
import boutiqueProjet.org.Repository.CompteurRepository;
import boutiqueProjet.org.Repository.FlivrRepository;
import boutiqueProjet.org.Repository.LflivrRepository;
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
public class FlivrController {
	@Autowired
	FlivrRepository repository;
	LflivrRepository repo;
	@Autowired
	CompteurRepository comptrepo;
	@Autowired
    ServletContext context;
	 @GetMapping("/flivrs")
	  public List<Flivr> getAllFlivrs() {
	    System.out.println("Get all Flivrs...");
	    List<Flivr> Flivrs = new ArrayList<>();
	    repository.findAll().forEach(Flivrs::add);
	    return Flivrs;
	  }
	
	@GetMapping("/flivrs/{id}")
	public ResponseEntity<Flivr> getFlivrById(@PathVariable(value = "id") Long FlivrId)
			throws ResourceNotFoundException {
		Flivr Flivr = repository.findById(FlivrId)
				.orElseThrow(() -> new ResourceNotFoundException("Flivr not found for this id :: " + FlivrId));
		return ResponseEntity.ok().body(Flivr);
	}

	@PostMapping("/flivrs")
	public ResponseEntity<Flivr> createBs1016( @RequestBody Flivr Flivr)  throws JsonParseException, JsonMappingException , Exception{
		  repository.save(Flivr);
		  List<Lflivr> lflivrs = Flivr.getLflivrs();
		    for (Lflivr lc : lflivrs) {
	          	lc.setNumero(Flivr.getNumero());
	          	repo.save(lc);
		       }	 
		    Optional<Compteur> CompteurInfo = comptrepo.findByAnnee(Flivr.getAnnee());
	     	if (CompteurInfo.isPresent()) {
		    	Compteur compteur = CompteurInfo.get();
		           compteur.setNumbs(compteur.getNumbs()+1);
		         compteur =   comptrepo.save(compteur);
	     	}
			 return new ResponseEntity<>(HttpStatus.OK);
		} 

	@DeleteMapping("/flivrs/{id}")
	public Map<String, Boolean> deleteFlivr(@PathVariable(value = "id") Long FlivrId)
			throws ResourceNotFoundException {
		Flivr Flivr = repository.findById(FlivrId)
				.orElseThrow(() -> new ResourceNotFoundException("Flivr not found  id :: " + FlivrId));
		repository.delete(Flivr);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	 
	  @DeleteMapping("/flivrs/delete")
	  public ResponseEntity<String> deleteAllFlivrs() {
	    System.out.println("Delete All Flivrs...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All Flivrs have been deleted!", HttpStatus.OK);
	  }
	 
	  @PutMapping("/flivrs/{id}")
	  public ResponseEntity<Flivr> updateFlivr(@PathVariable("id") long id, @RequestBody Flivr Flivr) {
	    System.out.println("Update Flivr with ID = " + id + "...");
	    Optional<Flivr> FlivrInfo = repository.findById(id);
	    if (FlivrInfo.isPresent()) {
	    	Flivr flivr = FlivrInfo.get();
	    	flivr.setLibelle(Flivr.getLibelle());
	      return new ResponseEntity<>(repository.save(Flivr), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
}
