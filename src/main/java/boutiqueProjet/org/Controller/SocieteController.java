package boutiqueProjet.org.Controller;


import boutiqueProjet.org.Entity.Societe;
import boutiqueProjet.org.Exception.ResourceNotFoundException;
import boutiqueProjet.org.Repository.SocieteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@CrossOrigin("*")
public class SocieteController {
	@Autowired
	SocieteRepository repository;
	
	 @GetMapping("/Societes")
	  public List<Societe> getAllSocietes() {
	    System.out.println("Get all Societes...");
	 
	    List<Societe> Societes = new ArrayList<>();
	    repository.findAll().forEach(Societes::add);
	 
	    return Societes;
	  }
	
	@GetMapping("/Societes/{id}")
	public ResponseEntity<Societe> getSocieteById(@PathVariable(value = "id") Long SocieteId)
			throws ResourceNotFoundException {
		Societe Societe = repository.findById(SocieteId)
				.orElseThrow(() -> new ResourceNotFoundException("Societe not found for this id :: " + SocieteId));
		return ResponseEntity.ok().body(Societe);
	}

	@PostMapping("/Societes")
	public Societe createSociete( @RequestBody Societe Societe) {
		return repository.save(Societe);
	}
	

	@DeleteMapping("/Societes/{id}")
	public Map<String, Boolean> deleteSociete(@PathVariable(value = "id") Long SocieteId)
			throws ResourceNotFoundException {
		Societe Societe = repository.findById(SocieteId)
				.orElseThrow(() -> new ResourceNotFoundException("Societe not found  id :: " + SocieteId));

		repository.delete(Societe);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	 
	  @DeleteMapping("/Societes/delete")
	  public ResponseEntity<String> deleteAllSocietes() {
	    System.out.println("Delete All Societes...");
	 
	    repository.deleteAll();
	 
	    return new ResponseEntity<>("All Societes have been deleted!", HttpStatus.OK);
	  }
	 
	

	  @PutMapping("/Societes/{id}")
	  public ResponseEntity<Societe> updateSociete(@PathVariable("id") long id, @RequestBody Societe Societe) {
	    System.out.println("Update Societe with ID = " + id + "...");
	 
	    Optional<Societe> SocieteInfo = repository.findById(id);
	 
	    if (SocieteInfo.isPresent()) {
	    	Societe societe = SocieteInfo.get();
	          
	           Societe.setLibelle(Societe.getLibelle());
	          
	      return new ResponseEntity<>(repository.save(Societe), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

}
