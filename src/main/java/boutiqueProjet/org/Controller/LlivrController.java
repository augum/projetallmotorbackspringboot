package boutiqueProjet.org.Controller;


import boutiqueProjet.org.Entity.Llivr;
import boutiqueProjet.org.Exception.ResourceNotFoundException;
import boutiqueProjet.org.Repository.LlivrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class LlivrController {
	@Autowired
	LlivrRepository repository;
	 @GetMapping("/LLlivrs")
	  public List<Llivr> getAllLlivrs() {
	    System.out.println("Get all Llivrs...");
	 
	    List<Llivr> Llivrs = new ArrayList<>();
	    repository.findAll().forEach(Llivrs::add);
	 
	    return Llivrs;
	  }
	
	@GetMapping("/Llivrs/{id}")
	public ResponseEntity<Llivr> getLlivrById(@PathVariable(value = "id") Long LlivrId)
			throws ResourceNotFoundException {
		Llivr Llivr = repository.findById(LlivrId)
				.orElseThrow(() -> new ResourceNotFoundException("Llivr not found for this id :: " + LlivrId));
		return ResponseEntity.ok().body(Llivr);
	}
	@GetMapping("/lnums/{numero}")
	public List<Llivr> getLlivrBynumero(@PathVariable(value = "numero") String numero)
	{
		List<Llivr> Llivr = new ArrayList<>();
		return repository.findAllBynumero(numero);
	}
	@PostMapping("/Llivrs")
	public  Llivr createLlivr( @RequestBody Llivr Llivr) {
		
		return repository.save(Llivr);
		
		 
	}
	

	@DeleteMapping("/Llivrs/{id}")
	public Map<String, Boolean> deleteLlivr(@PathVariable(value = "id") Long LlivrId)
			throws ResourceNotFoundException {
		Llivr Llivr = repository.findById(LlivrId)
				.orElseThrow(() -> new ResourceNotFoundException("Llivr not found  id :: " + LlivrId));

		repository.delete(Llivr);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	 
	  @DeleteMapping("/Llivrs/delete")
	  public ResponseEntity<String> deleteAllLlivrs() {
	    System.out.println("Delete All Llivrs...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All Llivrs have been deleted!", HttpStatus.OK);
	  }
	 
	

	 

}
