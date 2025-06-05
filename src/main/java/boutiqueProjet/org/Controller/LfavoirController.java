package boutiqueProjet.org.Controller;

import boutiqueProjet.org.Entity.Lfavoir;
import boutiqueProjet.org.Exception.ResourceNotFoundException;
import boutiqueProjet.org.Repository.LfavoirRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin("*")
public class LfavoirController {
	@Autowired
	LfavoirRepository repository;
	 @GetMapping("/lfavoirs")
	  public List<Lfavoir> getAllLfavoirs() {
	    System.out.println("Get all Lfavoirs...");
	 
	    List<Lfavoir> Lfavoirs = new ArrayList<>();
	    repository.findAll().forEach(Lfavoirs::add);
	 
	    return Lfavoirs;
	  }
	
	@GetMapping("/Lfavoirs/{id}")
	public ResponseEntity<Lfavoir> getLfavoirById(@PathVariable(value = "id") Long LfavoirId)
			throws ResourceNotFoundException {
		Lfavoir Lfavoir = repository.findById(LfavoirId)
				.orElseThrow(() -> new ResourceNotFoundException("Lfavoir not found for this id :: " + LfavoirId));
		return ResponseEntity.ok().body(Lfavoir);
	}

	@PostMapping("/Lfavoirs")
	public  Lfavoir createLfavoir( @RequestBody Lfavoir Lfavoir) {
		
		return repository.save(Lfavoir);
		
		 
	}
	

	@DeleteMapping("/Lfavoirs/{id}")
	public Map<String, Boolean> deleteLfavoir(@PathVariable(value = "id") Long LfavoirId)
			throws ResourceNotFoundException {
		Lfavoir Lfavoir = repository.findById(LfavoirId)
				.orElseThrow(() -> new ResourceNotFoundException("Lfavoir not found  id :: " + LfavoirId));

		repository.delete(Lfavoir);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	 
	  @DeleteMapping("/Lfavoirs/delete")
	  public ResponseEntity<String> deleteAllLfavoirs() {
	    System.out.println("Delete All Lfavoirs...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All Lfavoirs have been deleted!", HttpStatus.OK);
	  }
	 
	

	

}
