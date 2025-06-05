package boutiqueProjet.org.Controller;


import boutiqueProjet.org.Entity.Lb1016;
import boutiqueProjet.org.Exception.ResourceNotFoundException;
import boutiqueProjet.org.Repository.Lb1016Repository;
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
public class Lb1016Controller {
	@Autowired
	Lb1016Repository repository;
	
	 @GetMapping("/Lb1016s")
	  public List<Lb1016> getAllLb1016s() {
	    System.out.println("Get all Lb1016s...");
	    List<Lb1016> Lb1016s = new ArrayList<>();
	    repository.findAll().forEach(Lb1016s::add);
	    return Lb1016s;
	  }
	
	@GetMapping("/Lb1016s/{id}")
	public ResponseEntity<Lb1016> getLb1016ById(@PathVariable(value = "id") Long Lb1016Id)
			throws ResourceNotFoundException {
		Lb1016 Lb1016 = repository.findById(Lb1016Id)
				.orElseThrow(() -> new ResourceNotFoundException("Lb1016 not found for this id :: " + Lb1016Id));
		return ResponseEntity.ok().body(Lb1016);
	}

	@PostMapping("/Lb1016s")
	public  Lb1016 createLb1016( @RequestBody Lb1016 Lb1016) {
		
		return repository.save(Lb1016);
		 
	}

	@DeleteMapping("/Lb1016s/{id}")
	public Map<String, Boolean> deleteLb1016(@PathVariable(value = "id") Long Lb1016Id)
			throws ResourceNotFoundException {
		Lb1016 Lb1016 = repository.findById(Lb1016Id)
				.orElseThrow(() -> new ResourceNotFoundException("Lb1016 not found  id :: " + Lb1016Id));
		repository.delete(Lb1016);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	 
	  @DeleteMapping("/Lb1016s/delete")
	  public ResponseEntity<String> deleteAllLb1016s() {
	    System.out.println("Delete All Lb1016s...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All Lb1016s have been deleted!", HttpStatus.OK);
	  }
	 
	
}
