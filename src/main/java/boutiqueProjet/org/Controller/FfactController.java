package boutiqueProjet.org.Controller;


import boutiqueProjet.org.Entity.Ffact;
import boutiqueProjet.org.Exception.ResourceNotFoundException;
import boutiqueProjet.org.Repository.CompteurRepository;
import boutiqueProjet.org.Repository.FfactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;

import java.util.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class FfactController {
	@Autowired
	FfactRepository repository;
	@Autowired
	CompteurRepository comptrepo;
	@Autowired
    ServletContext context;
	
	 @GetMapping("/ffacts")
	  public List<Ffact> getAllFfacts() {
	    System.out.println("Get all Ffacts...");
	    List<Ffact> Ffacts = new ArrayList<>();
	    repository.findAll().forEach(Ffacts::add);
	    return Ffacts;
	  }
	
	@GetMapping("/ffacts/{id}")
	public ResponseEntity<Ffact> getFfactById(@PathVariable(value = "id") Long FfactId)
			throws ResourceNotFoundException {
		Ffact Ffact = repository.findById(FfactId)
				.orElseThrow(() -> new ResourceNotFoundException("Ffact not found for this id :: " + FfactId));
		return ResponseEntity.ok().body(Ffact);
	}

	@PostMapping("/ffacts")
	public  Ffact createFfact(@RequestBody Ffact Ffact) {
		
		return repository.save(Ffact);
		 
	}

	@DeleteMapping("/ffacts/{id}")
	public Map<String, Boolean> deleteFfact(@PathVariable(value = "id") Long FfactId)
			throws ResourceNotFoundException {
		Ffact Ffact = repository.findById(FfactId)
				.orElseThrow(() -> new ResourceNotFoundException("Ffact not found  id :: " + FfactId));
		repository.delete(Ffact);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	 
	  @DeleteMapping("/ffacts/delete")
	  public ResponseEntity<String> deleteAllFfacts() {
	    System.out.println("Delete All Ffacts...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All Ffacts have been deleted!", HttpStatus.OK);
	  }
	 
	  @PutMapping("/ffacts/{id}")
	  public ResponseEntity<Ffact> updateFfact(@PathVariable("id") long id, @RequestBody Ffact Ffact) {
	    System.out.println("Update Ffact with ID = " + id + "...");
	    Optional<Ffact> FfactInfo = repository.findById(id);
	    if (FfactInfo.isPresent()) {
	    	Ffact ffact = FfactInfo.get();
	    	ffact.setLibelle(Ffact.getLibelle());
	      return new ResponseEntity<>(repository.save(Ffact), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
}
