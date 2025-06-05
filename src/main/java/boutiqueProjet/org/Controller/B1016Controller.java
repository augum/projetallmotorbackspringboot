package boutiqueProjet.org.Controller;

import boutiqueProjet.org.Entity.B1016;
import boutiqueProjet.org.Entity.Cposte;
import boutiqueProjet.org.Entity.Lb1016;
import boutiqueProjet.org.Exception.ResourceNotFoundException;
import boutiqueProjet.org.Repository.B1016Repository;
import boutiqueProjet.org.Repository.CposteRepository;
import boutiqueProjet.org.Repository.Lb1016Repository;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;

import java.util.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class B1016Controller {
	@Autowired
	B1016Repository repository;
	@Autowired
	CposteRepository cposterepo;
	@Autowired
	Lb1016Repository repo;
	@Autowired
    ServletContext context;
	 @GetMapping("/b1016s")
	  public List<B1016> getAllB1016s() {
	    System.out.println("Get all B1016s...");
	    List<B1016> B1016s = new ArrayList<>();
	    repository.findAll().forEach(B1016s::add);
	    return B1016s;
	  }
	
	@GetMapping("/b1016s/{id}")
	public ResponseEntity<B1016> getB1016ById(@PathVariable(value = "id") Long B1016Id)
			throws ResourceNotFoundException {
		B1016 B1016 = repository.findById(B1016Id)
				.orElseThrow(() -> new ResourceNotFoundException("B1016 not found for this id :: " + B1016Id));
		return ResponseEntity.ok().body(B1016);
	}

	

	@DeleteMapping("/b1016s/{id}")
	public Map<String, Boolean> deleteB1016(@PathVariable(value = "id") Long B1016Id)
			throws ResourceNotFoundException {
		B1016 B1016 = repository.findById(B1016Id)
				.orElseThrow(() -> new ResourceNotFoundException("B1016 not found  id :: " + B1016Id));
		repository.delete(B1016);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	 
	  @DeleteMapping("/b1016s/delete")
	  public ResponseEntity<String> deleteAllB1016s() {
	    System.out.println("Delete All B1016s...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All B1016s have been deleted!", HttpStatus.OK);
	  }
	 
	  @PutMapping("/b1016s/{id}")
	  public ResponseEntity<B1016> updateB1016(@PathVariable("id") long id, @RequestBody B1016 B1016) {
	    System.out.println("Update B1016 with ID = " + id + "...");
	    Optional<B1016> B1016Info = repository.findById(id);
	    if (B1016Info.isPresent()) {
	    	B1016 b1016 = B1016Info.get();
	    	b1016.setLibelle(B1016.getLibelle());
	      return new ResponseEntity<>(repository.save(B1016), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	  
	
	  @PostMapping("/b1016s")
		public ResponseEntity<B1016> createB1016( @RequestBody B1016 B1016)  throws JsonParseException, JsonMappingException , Exception{
		  repository.save(B1016);
		  List<Lb1016> lb1016s = B1016.getLb1016s();
		    for (Lb1016 lc : lb1016s) {
	          	lc.setNumero(B1016.getNumero());
	          	repo.save(lc);
		       }	 
		    Optional<Cposte> CposteInfo = cposterepo.findByAnnee(B1016.getAnnee());
	     	if (CposteInfo.isPresent()) {
		    	Cposte Cposte = CposteInfo.get();
		           Cposte.setNumbon(Cposte.getNumbon()+1);
		         Cposte =   cposterepo.save(Cposte);
	     	}
			 return new ResponseEntity<>(HttpStatus.OK);
			 
		}
	  
	  
	  
	
	  
}
