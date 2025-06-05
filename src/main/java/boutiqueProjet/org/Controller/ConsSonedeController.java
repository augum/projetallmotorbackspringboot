package boutiqueProjet.org.Controller;

import boutiqueProjet.org.Entity.ConsSonede;
import boutiqueProjet.org.Entity.Cposte;
import boutiqueProjet.org.Entity.LconsSonede;
import boutiqueProjet.org.Exception.ResourceNotFoundException;
import boutiqueProjet.org.Repository.ConsSonedeRepository;
import boutiqueProjet.org.Repository.CposteRepository;
import boutiqueProjet.org.Repository.LconsSonedeRepository;
import com.fasterxml.jackson.databind.JsonMappingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;

import java.util.*;


@RestController
@RequestMapping("/api")
public class ConsSonedeController {
	@Autowired
	ConsSonedeRepository repository;
	@Autowired
	LconsSonedeRepository repo;
	@Autowired
	CposteRepository comptrepo;
	@Autowired
    ServletContext context;
	 @GetMapping("/ConsSonedes")
	  public List<ConsSonede> getAllConsSonedes() {
	    System.out.println("Get all ConsSonedes...");
	    List<ConsSonede> ConsSonedes = new ArrayList<>();
	    repository.findAll().forEach(ConsSonedes::add);
	    return ConsSonedes;
	  }
	
	@GetMapping("/ConsSonedes/{id}")
	public ResponseEntity<ConsSonede> getConsSonedeById(@PathVariable(value = "id") Long ConsSonedeId)
			throws ResourceNotFoundException {
		ConsSonede ConsSonede = repository.findById(ConsSonedeId)
				.orElseThrow(() -> new ResourceNotFoundException("ConsSonede not found for this id :: " + ConsSonedeId));
		return ResponseEntity.ok().body(ConsSonede);
	}

	@PostMapping("/ConsSonedes")
	public ResponseEntity<ConsSonede> createConsSonede( @RequestBody ConsSonede ConsSonede)  throws JsonParseException, JsonMappingException , Exception{
		repository.save(ConsSonede);
		List<LconsSonede> lconsSonedes = ConsSonede.getLconsSonedes();
	    for (LconsSonede lc : lconsSonedes) {
	        lc.setNumero(ConsSonede.getNumero());
       		repo.save(lc);
	       }
	    Optional<Cposte> CposteInfo = comptrepo.findByAnnee(ConsSonede.getAnnee());
     	if (CposteInfo.isPresent()) {
	    	Cposte Cposte = CposteInfo.get();
	           Cposte.setNumcsonede(Cposte.getNumcsonede()+1);
	         Cposte =   comptrepo.save(Cposte);
     	}
		 return new ResponseEntity<>(HttpStatus.OK);
	}
	

	@DeleteMapping("/ConsSonedes/{id}")
	public Map<String, Boolean> deleteConsSonede(@PathVariable(value = "id") Long ConsSonedeId)
			throws ResourceNotFoundException {
		ConsSonede ConsSonede = repository.findById(ConsSonedeId)
				.orElseThrow(() -> new ResourceNotFoundException("ConsSonede not found  id :: " + ConsSonedeId));
		repository.delete(ConsSonede);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	 
	  @DeleteMapping("/ConsSonedes/delete")
	  public ResponseEntity<String> deleteAllConsSonedes() {
	    System.out.println("Delete All ConsSonedes...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All ConsSonedes have been deleted!", HttpStatus.OK);
	  }
	 
	  @PutMapping("/ConsSonedes/{id}")
	  public ResponseEntity<ConsSonede> updateConsSonede(@PathVariable("id") long id, @RequestBody ConsSonede ConsSonede) {
	    System.out.println("Update ConsSonede with ID = " + id + "...");
	    Optional<ConsSonede> ConsSonedeInfo = repository.findById(id);
	    if (ConsSonedeInfo.isPresent()) {
	    	ConsSonede consSonede = ConsSonedeInfo.get();
	    	consSonede.setLibelle(ConsSonede.getLibelle());
	      return new ResponseEntity<>(repository.save(ConsSonede), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
}
