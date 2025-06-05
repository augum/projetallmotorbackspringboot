package boutiqueProjet.org.Controller;

import boutiqueProjet.org.Entity.Bs1016;
import boutiqueProjet.org.Entity.Compteur;
import boutiqueProjet.org.Entity.Lbs1016;
import boutiqueProjet.org.Exception.ResourceNotFoundException;
import boutiqueProjet.org.Repository.Bs1016Repository;
import boutiqueProjet.org.Repository.CompteurRepository;
import boutiqueProjet.org.Repository.Lbs1016Repository;
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
public class Bs1016Controller {
	@Autowired
	Bs1016Repository repository;
	@Autowired
	Lbs1016Repository repo;
	@Autowired
	CompteurRepository comptrepo;
	@Autowired
    ServletContext context;
	 @GetMapping("/Bs1016s")
	  public List<Bs1016> getAllBs1016s() {
	    System.out.println("Get all Bs1016s...");
	    List<Bs1016> Bs1016s = new ArrayList<>();
	    repository.findAll().forEach(Bs1016s::add);
	    return Bs1016s;
	  }
	
	@GetMapping("/Bs1016s/{id}")
	public ResponseEntity<Bs1016> getBs1016ById(@PathVariable(value = "id") Long Bs1016Id)
			throws ResourceNotFoundException {
		Bs1016 Bs1016 = repository.findById(Bs1016Id)
				.orElseThrow(() -> new ResourceNotFoundException("Bs1016 not found for this id :: " + Bs1016Id));
		return ResponseEntity.ok().body(Bs1016);
	}

	@PostMapping("/Bs1016s")
	public ResponseEntity<Bs1016> createBs1016( @RequestBody Bs1016 Bs1016)  throws JsonParseException, JsonMappingException , Exception{
	  repository.save(Bs1016);
	  List<Lbs1016> lbs1016s = Bs1016.getLbs1016s();
	    for (Lbs1016 lc : lbs1016s) {
          	lc.setNumero(Bs1016.getNumero());
          	repo.save(lc);
	       }	 
	    Optional<Compteur> CompteurInfo = comptrepo.findByAnnee(Bs1016.getAnnee());
     	if (CompteurInfo.isPresent()) {
	    	Compteur compteur = CompteurInfo.get();
	           compteur.setNumbs(compteur.getNumbs()+1);
	         compteur =   comptrepo.save(compteur);
     	}
		 return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/Bs1016s/{id}")
	public Map<String, Boolean> deleteBs1016(@PathVariable(value = "id") Long Bs1016Id)
			throws ResourceNotFoundException {
		Bs1016 Bs1016 = repository.findById(Bs1016Id)
				.orElseThrow(() -> new ResourceNotFoundException("Bs1016 not found  id :: " + Bs1016Id));
		repository.delete(Bs1016);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	 
	  @DeleteMapping("/Bs1016s/delete")
	  public ResponseEntity<String> deleteAllBs1016s() {
	    System.out.println("Delete All Bs1016s...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All Bs1016s have been deleted!", HttpStatus.OK);
	  }
	 
	  @PutMapping("/Bs1016s/{id}")
	  public ResponseEntity<Bs1016> updateBs1016(@PathVariable("id") long id, @RequestBody Bs1016 Bs1016) {
	    System.out.println("Update Bs1016 with ID = " + id + "...");
	    Optional<Bs1016> Bs1016Info = repository.findById(id);
	    if (Bs1016Info.isPresent()) {
	    	Bs1016 bs1016 = Bs1016Info.get();
	    	bs1016.setLibelle(Bs1016.getLibelle());
	      return new ResponseEntity<>(repository.save(Bs1016), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
}
