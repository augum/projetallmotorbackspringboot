package boutiqueProjet.org.Controller;

import boutiqueProjet.org.Entity.ConsSteg;
import boutiqueProjet.org.Entity.Cposte;
import boutiqueProjet.org.Entity.LconsSteg;
import boutiqueProjet.org.Exception.ResourceNotFoundException;
import boutiqueProjet.org.Repository.ConsStegRepository;
import boutiqueProjet.org.Repository.CposteRepository;
import boutiqueProjet.org.Repository.LconsStegRepository;
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
public class ConsStegController {
	@Autowired
	ConsStegRepository repository;
	LconsStegRepository repo;
    @Autowired
	CposteRepository comptrepo;
	@Autowired
    ServletContext context;
	 @GetMapping("/consStegs")
	  public List<ConsSteg> getAllConsStegs() {
	    System.out.println("Get all ConsStegs...");
	    List<ConsSteg> ConsStegs = new ArrayList<>();
	    repository.findAll().forEach(ConsStegs::add);
	    return ConsStegs;
	  }
	
	@GetMapping("/consStegs/{id}")
	public ResponseEntity<ConsSteg> getConsStegById(@PathVariable(value = "id") Long ConsStegId)
			throws ResourceNotFoundException {
		ConsSteg ConsSteg = repository.findById(ConsStegId)
				.orElseThrow(() -> new ResourceNotFoundException("ConsSteg not found for this id :: " + ConsStegId));
		return ResponseEntity.ok().body(ConsSteg);
	}

	@PostMapping("/consStegs")
	
	public ResponseEntity<ConsSteg> createConsSteg( @RequestBody ConsSteg ConsSteg)  throws JsonParseException, JsonMappingException , Exception{
		repository.save(ConsSteg);
		List<LconsSteg> lconsStegs = ConsSteg.getLconsStegs();
		
	    for (LconsSteg lc : lconsStegs) {
	        lc.setNumero(ConsSteg.getNumero());
       		repo.save(lc);
	       }
	    Optional<Cposte> CposteInfo = comptrepo.findByAnnee(ConsSteg.getAnnee());
     	if (CposteInfo.isPresent()) {
	    	Cposte Cposte = CposteInfo.get();
	           Cposte.setNumcsonede(Cposte.getNumcsteg()+1);
	         Cposte =   comptrepo.save(Cposte);
     	}
		 return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/consStegs/{id}")
	public Map<String, Boolean> deleteConsSteg(@PathVariable(value = "id") Long ConsStegId)
			throws ResourceNotFoundException {
		ConsSteg ConsSteg = repository.findById(ConsStegId)
				.orElseThrow(() -> new ResourceNotFoundException("ConsSteg not found  id :: " + ConsStegId));
		repository.delete(ConsSteg);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	 
	  @DeleteMapping("/consStegs/delete")
	  public ResponseEntity<String> deleteAllConsStegs() {
	    System.out.println("Delete All ConsStegs...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All ConsStegs have been deleted!", HttpStatus.OK);
	  }
	 
	  @PutMapping("/consStegs/{id}")
	  public ResponseEntity<ConsSteg> updateConsSteg(@PathVariable("id") long id, @RequestBody ConsSteg ConsSteg) {
	    System.out.println("Update ConsSteg with ID = " + id + "...");
	    Optional<ConsSteg> ConsStegInfo = repository.findById(id);
	    if (ConsStegInfo.isPresent()) {
	    	ConsSteg consSteg = ConsStegInfo.get();
	    	consSteg.setLibelle(ConsSteg.getLibelle());
	      return new ResponseEntity<>(repository.save(ConsSteg), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
}
