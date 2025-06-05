package boutiqueProjet.org.Controller;


import boutiqueProjet.org.Entity.Lfcomm;
import boutiqueProjet.org.Exception.ResourceNotFoundException;
import boutiqueProjet.org.Repository.LfcommRepository;
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
public class LfcommController {
	@Autowired
	LfcommRepository repository;
	 @GetMapping("/LLfcomms")
	  public List<Lfcomm> getAllLfcomms() {
	    System.out.println("Get all Lfcomms...");
	 
	    List<Lfcomm> Lfcomms = new ArrayList<>();
	    repository.findAll().forEach(Lfcomms::add);
	 
	    return Lfcomms;
	  }
	
	@GetMapping("/Lfcomms/{id}")
	public ResponseEntity<Lfcomm> getLfcommById(@PathVariable(value = "id") Long LfcommId)
			throws ResourceNotFoundException {
		Lfcomm Lfcomm = repository.findById(LfcommId)
				.orElseThrow(() -> new ResourceNotFoundException("Lfcomm not found for this id :: " + LfcommId));
		return ResponseEntity.ok().body(Lfcomm);
	}

	@PostMapping("/Lfcomms")
	public  Lfcomm createLfcomm(@RequestBody Lfcomm Lfcomm) {
		
		return repository.save(Lfcomm);
		
		 
	}
	

	@DeleteMapping("/Lfcomms/{id}")
	public Map<String, Boolean> deleteLfcomm(@PathVariable(value = "id") Long LfcommId)
			throws ResourceNotFoundException {
		Lfcomm Lfcomm = repository.findById(LfcommId)
				.orElseThrow(() -> new ResourceNotFoundException("Lfcomm not found  id :: " + LfcommId));

		repository.delete(Lfcomm);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	 
	  @DeleteMapping("/Lfcomms/delete")
	  public ResponseEntity<String> deleteAllLfcomms() {
	    System.out.println("Delete All Lfcomms...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All Lfcomms have been deleted!", HttpStatus.OK);
	  }
	 
	

	

}
