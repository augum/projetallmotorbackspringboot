package boutiqueProjet.org.Controller;


import boutiqueProjet.org.Entity.Cposte;
import boutiqueProjet.org.Exception.ResourceNotFoundException;
import boutiqueProjet.org.Repository.CposteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.*;



@RestController
@RequestMapping("/api")
public class CposteControlleur {
	@Autowired
	CposteRepository repository;
	
	 @GetMapping("/Cpostes")
	  public List<Cposte> getAllCpostes() {
	    System.out.println("Get all Cpostes...");
	 
	    List<Cposte> Cpostes = new ArrayList<>();
	    repository.findAll().forEach(Cpostes::add);
	 
	    return Cpostes;
	  }
	
	@GetMapping("/Cpostes/{annee}")
	public ResponseEntity<Cposte> getCposteByAnnee(@PathVariable(value = "annee") int annee)
			throws ResourceNotFoundException {
		Cposte Cposte = repository.findByAnnee(annee)
				.orElseThrow(() -> new ResourceNotFoundException("Cposte not found for this id :: " + annee));
		return ResponseEntity.ok().body(Cposte);
	}

	@PostMapping("/Cpostes")
	public Cposte createCposte( @RequestBody Cposte Cposte) {
		return repository.save(Cposte);
	}
	

	@DeleteMapping("/Cpostes/{id}")
	public Map<String, Boolean> deleteCposte(@PathVariable(value = "id") Long CposteId)
			throws ResourceNotFoundException {
		Cposte Cposte = repository.findById(CposteId)
				.orElseThrow(() -> new ResourceNotFoundException("Cposte not found  id :: " + CposteId));

		repository.delete(Cposte);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	 
	  @DeleteMapping("/Cpostes/delete")
	  public ResponseEntity<String> deleteAllCpostes() {
	    System.out.println("Delete All Cpostes...");
	 
	    repository.deleteAll();
	 
	    return new ResponseEntity<>("All Cpostes have been deleted!", HttpStatus.OK);
	  }
	 
	

	  @PutMapping("/Cpostes/{id}")
	  public ResponseEntity<Cposte> updateCposte(@PathVariable("id") long id, @RequestBody Cposte Cposte) {
	    System.out.println("Update Cposte with ID = " + id + "...");
	 
	    Optional<Cposte> CposteInfo = repository.findById(id);
	 
	    if (CposteInfo.isPresent()) {
	    	Cposte cposte = CposteInfo.get();
	          
	           cposte.setNumbon(Cposte.getNumbon());
	           cposte.setNumbs(Cposte.getNumbs());
	           cposte.setNumbs(Cposte.getNumbs());
	      return new ResponseEntity<>(repository.save(Cposte), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
}
