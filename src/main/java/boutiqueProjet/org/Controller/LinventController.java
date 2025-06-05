package boutiqueProjet.org.Controller;


import boutiqueProjet.org.Entity.Linvent;
import boutiqueProjet.org.Exception.ResourceNotFoundException;
import boutiqueProjet.org.Repository.LinventRepository;
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
public class LinventController {
	@Autowired
	LinventRepository repository;
	 @GetMapping("/LLinvents")
	  public List<Linvent> getAllLinvents() {
	    System.out.println("Get all Linvents...");
	 
	    List<Linvent> Linvents = new ArrayList<>();
	    repository.findAll().forEach(Linvents::add);
	 
	    return Linvents;
	  }
	
	@GetMapping("/Linvents/{id}")
	public ResponseEntity<Linvent> getLinventById(@PathVariable(value = "id") Long LinventId)
			throws ResourceNotFoundException {
		Linvent Linvent = repository.findById(LinventId)
				.orElseThrow(() -> new ResourceNotFoundException("Linvent not found for this id :: " + LinventId));
		return ResponseEntity.ok().body(Linvent);
	}

	@PostMapping("/Linvents")
	public  Linvent createLinvent( @RequestBody Linvent Linvent) {
		
		return repository.save(Linvent);
		
		 
	}
	

	@DeleteMapping("/Linvents/{id}")
	public Map<String, Boolean> deleteLinvent(@PathVariable(value = "id") Long LinventId)
			throws ResourceNotFoundException {
		Linvent Linvent = repository.findById(LinventId)
				.orElseThrow(() -> new ResourceNotFoundException("Linvent not found  id :: " + LinventId));

		repository.delete(Linvent);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	 
	  @DeleteMapping("/Linvents/delete")
	  public ResponseEntity<String> deleteAllLinvents() {
	    System.out.println("Delete All Linvents...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All Linvents have been deleted!", HttpStatus.OK);
	  }
	 
	

	  
	  }


