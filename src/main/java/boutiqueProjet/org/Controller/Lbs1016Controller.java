package boutiqueProjet.org.Controller;


import boutiqueProjet.org.Entity.Lbs1016;
import boutiqueProjet.org.Exception.ResourceNotFoundException;
import boutiqueProjet.org.Repository.Lbs1016Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Lbs1016Controller {
	@Autowired
	Lbs1016Repository repository;
	
	 @GetMapping("/Lbs1016s")
	  public List<Lbs1016> getAllLbs1016s() {
	    System.out.println("Get all Lbs1016s...");
	    List<Lbs1016> Lbs1016s = new ArrayList<>();
	    repository.findAll().forEach(Lbs1016s::add);
	    return Lbs1016s;
	  }
	
	@GetMapping("/Lbs1016s/{id}")
	public ResponseEntity<Lbs1016> getLbs1016ById(@PathVariable(value = "id") Long Lbs1016Id)
			throws ResourceNotFoundException {
		Lbs1016 Lbs1016 = repository.findById(Lbs1016Id)
				.orElseThrow(() -> new ResourceNotFoundException("Lbs1016 not found for this id :: " + Lbs1016Id));
		return ResponseEntity.ok().body(Lbs1016);
	}

	@PostMapping("/Lbs1016s")
	public Lbs1016 createLbs1016( @RequestBody Lbs1016 Lbs1016) {
		
		return repository.save(Lbs1016);
		 
	}

	@DeleteMapping("/Lbs1016s/{id}")
	public Map<String, Boolean> deleteLbs1016(@PathVariable(value = "id") Long Lbs1016Id)
			throws ResourceNotFoundException {
		Lbs1016 Lbs1016 = repository.findById(Lbs1016Id)
				.orElseThrow(() -> new ResourceNotFoundException("Lbs1016 not found  id :: " + Lbs1016Id));
		repository.delete(Lbs1016);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	 
	  @DeleteMapping("/Lbs1016s/delete")
	  public ResponseEntity<String> deleteAllLbs1016s() {
	    System.out.println("Delete All Lbs1016s...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All Lbs1016s have been deleted!", HttpStatus.OK);
	  }
	 
	
}
