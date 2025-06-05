package boutiqueProjet.org.Controller;


import boutiqueProjet.org.Entity.LcommService;
import boutiqueProjet.org.Exception.ResourceNotFoundException;
import boutiqueProjet.org.Repository.LcommServiceRepository;
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
public class LcommServiceController {
	@Autowired
	LcommServiceRepository repository;
	 @GetMapping("/LLcommServices")
	  public List<LcommService> getAllLcommServices() {
	    System.out.println("Get all LcommServices...");
	 
	    List<LcommService> LcommServices = new ArrayList<>();
	    repository.findAll().forEach(LcommServices::add);
	 
	    return LcommServices;
	  }
	
	@GetMapping("/LcommServices/{id}")
	public ResponseEntity<LcommService> getLcommServiceById(@PathVariable(value = "id") Long LcommServiceId)
			throws ResourceNotFoundException {
		LcommService LcommService = repository.findById(LcommServiceId)
				.orElseThrow(() -> new ResourceNotFoundException("LcommService not found for this id :: " + LcommServiceId));
		return ResponseEntity.ok().body(LcommService);
	}

	@PostMapping("/LcommServices")
	public  LcommService createLcommService( @RequestBody LcommService LcommService) {
		
		return repository.save(LcommService);
		
		 
	}
	

	@DeleteMapping("/LcommServices/{id}")
	public Map<String, Boolean> deleteLcommService(@PathVariable(value = "id") Long LcommServiceId)
			throws ResourceNotFoundException {
		LcommService LcommService = repository.findById(LcommServiceId)
				.orElseThrow(() -> new ResourceNotFoundException("LcommService not found  id :: " + LcommServiceId));

		repository.delete(LcommService);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	 
	  @DeleteMapping("/LcommServices/delete")
	  public ResponseEntity<String> deleteAllLcommServices() {
	    System.out.println("Delete All LcommServices...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All LcommServices have been deleted!", HttpStatus.OK);
	  }
	 
	

	 

}
