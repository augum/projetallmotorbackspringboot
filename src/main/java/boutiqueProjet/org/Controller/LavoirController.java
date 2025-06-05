package boutiqueProjet.org.Controller;


import boutiqueProjet.org.Entity.Lavoir;
import boutiqueProjet.org.Exception.ResourceNotFoundException;
import boutiqueProjet.org.Repository.LavoirRepository;
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
public class LavoirController {
	@Autowired
	LavoirRepository repository;
	
	 @GetMapping("/Lavoirs")
	  public List<Lavoir> getAllLavoirs() {
	    System.out.println("Get all Lavoirs...");
	    List<Lavoir> Lavoirs = new ArrayList<>();
	    repository.findAll().forEach(Lavoirs::add);
	    return Lavoirs;
	  }
	
	@GetMapping("/Lavoirs/{id}")
	public ResponseEntity<Lavoir> getLavoirById(@PathVariable(value = "id") Long LavoirId)
			throws ResourceNotFoundException {
		Lavoir Lavoir = repository.findById(LavoirId)
				.orElseThrow(() -> new ResourceNotFoundException("Lavoir not found for this id :: " + LavoirId));
		return ResponseEntity.ok().body(Lavoir);
	}

	@PostMapping("/Lavoirs")
	public  Lavoir createLavoir( @RequestBody Lavoir Lavoir) {
		
		return repository.save(Lavoir);
		 
	}

	@DeleteMapping("/Lavoirs/{id}")
	public Map<String, Boolean> deleteLavoir(@PathVariable(value = "id") Long LavoirId)
			throws ResourceNotFoundException {
		Lavoir Lavoir = repository.findById(LavoirId)
				.orElseThrow(() -> new ResourceNotFoundException("Lavoir not found  id :: " + LavoirId));
		repository.delete(Lavoir);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	 
	  @DeleteMapping("/Lavoirs/delete")
	  public ResponseEntity<String> deleteAllLavoirs() {
	    System.out.println("Delete All Lavoirs...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All Lavoirs have been deleted!", HttpStatus.OK);
	  }
	 
	  
}
