package boutiqueProjet.org.Controller;


import boutiqueProjet.org.Entity.Lrecouv;
import boutiqueProjet.org.Exception.ResourceNotFoundException;
import boutiqueProjet.org.Repository.LrecouvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class LrecouvController {
	@Autowired
	LrecouvRepository repository;
	 @GetMapping("/LLrecouvs")
	  public List<Lrecouv> getAllLrecouvs() {
	    System.out.println("Get all Lrecouvs...");
	 
	    List<Lrecouv> Lrecouvs = new ArrayList<>();
	    repository.findAll().forEach(Lrecouvs::add);
	 
	    return Lrecouvs;
	  }
	
	@GetMapping("/Lrecouvs/{id}")
	public ResponseEntity<Lrecouv> getLrecouvById(@PathVariable(value = "id") Long LrecouvId)
			throws ResourceNotFoundException {
		Lrecouv Lrecouv = repository.findById(LrecouvId)
				.orElseThrow(() -> new ResourceNotFoundException("Lrecouv not found for this id :: " + LrecouvId));
		return ResponseEntity.ok().body(Lrecouv);
	}

	@PostMapping("/Lrecouvs")
	public  Lrecouv createLrecouv( @RequestBody Lrecouv Lrecouv) {
		
		return repository.save(Lrecouv);
		
		 
	}
	

	@DeleteMapping("/Lrecouvs/{id}")
	public Map<String, Boolean> deleteLrecouv(@PathVariable(value = "id") Long LrecouvId)
			throws ResourceNotFoundException {
		Lrecouv Lrecouv = repository.findById(LrecouvId)
				.orElseThrow(() -> new ResourceNotFoundException("Lrecouv not found  id :: " + LrecouvId));

		repository.delete(Lrecouv);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	 
	  @DeleteMapping("/Lrecouvs/delete")
	  public ResponseEntity<String> deleteAllLrecouvs() {
	    System.out.println("Delete All Lrecouvs...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All Lrecouvs have been deleted!", HttpStatus.OK);
	  }
	 
	

	 

}
