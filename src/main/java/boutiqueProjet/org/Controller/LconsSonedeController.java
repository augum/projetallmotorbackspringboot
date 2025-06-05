package boutiqueProjet.org.Controller;


import boutiqueProjet.org.Entity.LconsSonede;
import boutiqueProjet.org.Exception.ResourceNotFoundException;
import boutiqueProjet.org.Repository.LconsSonedeRepository;
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
public class LconsSonedeController {
	@Autowired
	LconsSonedeRepository repository;
	 @GetMapping("/LLconsSonedes")
	  public List<LconsSonede> getAllLconsSonedes() {
	    System.out.println("Get all LconsSonedes...");
	 
	    List<LconsSonede> LconsSonedes = new ArrayList<>();
	    repository.findAll().forEach(LconsSonedes::add);
	 
	    return LconsSonedes;
	  }
	
	@GetMapping("/LconsSonedes/{id}")
	public ResponseEntity<LconsSonede> getLconsSonedeById(@PathVariable(value = "id") Long LconsSonedeId)
			throws ResourceNotFoundException {
		LconsSonede LconsSonede = repository.findById(LconsSonedeId)
				.orElseThrow(() -> new ResourceNotFoundException("LconsSonede not found for this id :: " + LconsSonedeId));
		return ResponseEntity.ok().body(LconsSonede);
	}

	@PostMapping("/LconsSonedes")
	public LconsSonede createLconsSonede( @RequestBody LconsSonede LconsSonede) {
		
		return repository.save(LconsSonede);
		
		 
	}
	

	@DeleteMapping("/LconsSonedes/{id}")
	public Map<String, Boolean> deleteLconsSonede(@PathVariable(value = "id") Long LconsSonedeId)
			throws ResourceNotFoundException {
		LconsSonede LconsSonede = repository.findById(LconsSonedeId)
				.orElseThrow(() -> new ResourceNotFoundException("LconsSonede not found  id :: " + LconsSonedeId));

		repository.delete(LconsSonede);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	 
	  @DeleteMapping("/LconsSonedes/delete")
	  public ResponseEntity<String> deleteAllLconsSonedes() {
	    System.out.println("Delete All LconsSonedes...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All LconsSonedes have been deleted!", HttpStatus.OK);
	  }
	 
	

	

}
