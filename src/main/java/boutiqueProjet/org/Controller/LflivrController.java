package boutiqueProjet.org.Controller;


import boutiqueProjet.org.Entity.Lflivr;
import boutiqueProjet.org.Exception.ResourceNotFoundException;
import boutiqueProjet.org.Repository.LflivrRepository;
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
public class LflivrController {
	@Autowired
	LflivrRepository repository;
	 @GetMapping("/LLflivrs")
	  public List<Lflivr> getAllLflivrs() {
	    System.out.println("Get all Lflivrs...");
	 
	    List<Lflivr> Lflivrs = new ArrayList<>();
	    repository.findAll().forEach(Lflivrs::add);
	 
	    return Lflivrs;
	  }
	
	@GetMapping("/Lflivrs/{id}")
	public ResponseEntity<Lflivr> getLflivrById(@PathVariable(value = "id") Long LflivrId)
			throws ResourceNotFoundException {
		Lflivr Lflivr = repository.findById(LflivrId)
				.orElseThrow(() -> new ResourceNotFoundException("Lflivr not found for this id :: " + LflivrId));
		return ResponseEntity.ok().body(Lflivr);
	}

	@PostMapping("/Lflivrs")
	public Lflivr createLflivr( @RequestBody Lflivr Lflivr) {
		
		return repository.save(Lflivr);
		
		 
	}
	

	@DeleteMapping("/Lflivrs/{id}")
	public Map<String, Boolean> deleteLflivr(@PathVariable(value = "id") Long LflivrId)
			throws ResourceNotFoundException {
		Lflivr Lflivr = repository.findById(LflivrId)
				.orElseThrow(() -> new ResourceNotFoundException("Lflivr not found  id :: " + LflivrId));

		repository.delete(Lflivr);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	 
	  @DeleteMapping("/Lflivrs/delete")
	  public ResponseEntity<String> deleteAllLflivrs() {
	    System.out.println("Delete All Lflivrs...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All Lflivrs have been deleted!", HttpStatus.OK);
	  }
	 
	

	 

}
