package boutiqueProjet.org.Controller;

import boutiqueProjet.org.Entity.Lrecouf;
import boutiqueProjet.org.Exception.ResourceNotFoundException;
import boutiqueProjet.org.Repository.LrecoufRepository;

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
public class LrecoufController {
	@Autowired
	LrecoufRepository repository;
	 @GetMapping("/LLrecoufs")
	  public List<Lrecouf> getAllLrecoufs() {
	    System.out.println("Get all Lrecoufs...");
	 
	    List<Lrecouf> Lrecoufs = new ArrayList<>();
	    repository.findAll().forEach(Lrecoufs::add);
	 
	    return Lrecoufs;
	  }
	
	@GetMapping("/Lrecoufs/{id}")
	public ResponseEntity<Lrecouf> getLrecoufById(@PathVariable(value = "id") Long LrecoufId)
			throws ResourceNotFoundException {
		Lrecouf Lrecouf = repository.findById(LrecoufId)
				.orElseThrow(() -> new ResourceNotFoundException("Lrecouf not found for this id :: " + LrecoufId));
		return ResponseEntity.ok().body(Lrecouf);
	}

	@PostMapping("/Lrecoufs")
	public  Lrecouf createLrecouf( @RequestBody Lrecouf Lrecouf) {
		
		return repository.save(Lrecouf);
		
		 
	}
	

	@DeleteMapping("/Lrecoufs/{id}")
	public Map<String, Boolean> deleteLrecouf(@PathVariable(value = "id") Long LrecoufId)
			throws ResourceNotFoundException {
		Lrecouf Lrecouf = repository.findById(LrecoufId)
				.orElseThrow(() -> new ResourceNotFoundException("Lrecouf not found  id :: " + LrecoufId));

		repository.delete(Lrecouf);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	 
	  @DeleteMapping("/Lrecoufs/delete")
	  public ResponseEntity<String> deleteAllLrecoufs() {
	    System.out.println("Delete All Lrecoufs...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All Lrecoufs have been deleted!", HttpStatus.OK);
	  }
	 
	

	

}
