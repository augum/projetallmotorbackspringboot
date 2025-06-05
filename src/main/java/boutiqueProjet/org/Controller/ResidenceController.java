package boutiqueProjet.org.Controller;


import boutiqueProjet.org.Entity.Residence;
import boutiqueProjet.org.Exception.ResourceNotFoundException;
import boutiqueProjet.org.Repository.ResidenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class ResidenceController {
	@Autowired
	ResidenceRepository repository;
	
	 @GetMapping("/residences")
	  public List<Residence> getAllResidences() {
	    System.out.println("Get all Residences...");
	 
	    List<Residence> Residences = new ArrayList<>();
	    repository.findAll().forEach(Residences::add);
	 
	    return Residences;
	  }

	 @GetMapping("/residences/{id}")
		public ResponseEntity<Residence> getResidenceById(@PathVariable(value = "id") Long ResidenceId)
				throws ResourceNotFoundException {
			Residence Residence = repository.findById(ResidenceId)
					.orElseThrow(() -> new ResourceNotFoundException("Article not found for this id :: " + ResidenceId));
			return ResponseEntity.ok().body(Residence);
		}

	 @GetMapping("/lresidences/{code_dir}")
		
	    public ResponseEntity<List<Residence>> listDirection(@PathVariable String code_dir) {
	        
			List<Residence> residences = repository.findByResidence(code_dir);
	       
	        return new ResponseEntity<List<Residence>>(residences, HttpStatus.OK);
	    }
	
	@PostMapping("/residences")
	public Residence createResidence( @RequestBody Residence Residence) {
		return repository.save(Residence);
	}
	

	@DeleteMapping("/residences/{id}")
	public Map<String, Boolean> deleteResidence(@PathVariable(value = "id") Long ResidenceId)
			throws ResourceNotFoundException {
		Residence Residence = repository.findById(ResidenceId)
				.orElseThrow(() -> new ResourceNotFoundException("Categorie not found  id :: " + ResidenceId));

		repository.delete(Residence);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	 
	  @DeleteMapping("/residences/delete")
	  public ResponseEntity<String> deleteAllResidences() {
	    System.out.println("Delete All Residences...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All Residences have been deleted!", HttpStatus.OK);
	  }
	 
	

	  @PutMapping("/residences/{id}")
	  public ResponseEntity<Residence> updateResidence(@PathVariable("id") long id, @RequestBody Residence Residence) {
	    System.out.println("Update Residence with ID = " + id + "...");
	 
	    Optional<Residence> ResidenceInfo = repository.findById(id);
	 
	    if (ResidenceInfo.isPresent()) {
	    	Residence residence = ResidenceInfo.get();
	           residence.setLibelle(Residence.getLibelle());
	           residence.setCode_dir(Residence.getCode_dir()); 
	      return new ResponseEntity<>(repository.save(Residence), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	  
	

}
