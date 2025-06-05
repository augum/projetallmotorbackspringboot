package boutiqueProjet.org.Controller;

import boutiqueProjet.org.Entity.Compteur;
import boutiqueProjet.org.Entity.Invent;
import boutiqueProjet.org.Entity.Linvent;
import boutiqueProjet.org.Exception.ResourceNotFoundException;
import boutiqueProjet.org.Repository.CompteurRepository;
import boutiqueProjet.org.Repository.InventRepository;
import boutiqueProjet.org.Repository.LinventRepository;
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
public class InventController {
	@Autowired
	InventRepository repository;
	@Autowired
	LinventRepository repo;
	@Autowired
	CompteurRepository comptrepo;
	@Autowired
    ServletContext context;
	 @GetMapping("/invents")
	  public List<Invent> getAllInvents() {
	    System.out.println("Get all Invents...");
	    List<Invent> Invents = new ArrayList<>();
	    repository.findAll().forEach(Invents::add);
	    return Invents;
	  }
	
	@GetMapping("/invents/{id}")
	public ResponseEntity<Invent> getInventById(@PathVariable(value = "id") Long InventId)
			throws ResourceNotFoundException {
		Invent Invent = repository.findById(InventId)
				.orElseThrow(() -> new ResourceNotFoundException("Invent not found for this id :: " + InventId));
		return ResponseEntity.ok().body(Invent);
	}

	@PostMapping("/invents")
	public ResponseEntity<Invent> createInvent( @RequestBody Invent Invent)  throws JsonParseException, JsonMappingException , Exception{
		  repository.save(Invent);
		  List<Linvent> linvents = Invent.getLinvents();
		    for (Linvent lc : linvents) {
	          	lc.setNumero(Invent.getNumero());
	          	repo.save(lc);
		       }	 
		    Optional<Compteur> CompteurInfo = comptrepo.findByAnnee(Invent.getAnnee());
	     	if (CompteurInfo.isPresent()) {
		    	Compteur compteur = CompteurInfo.get();
		           compteur.setNuminv(compteur.getNuminv()+1);
		         compteur =   comptrepo.save(compteur);
	     	}
			 return new ResponseEntity<>(HttpStatus.OK);
		} 

	@DeleteMapping("/invents/{id}")
	public Map<String, Boolean> deleteInvent(@PathVariable(value = "id") Long InventId)
			throws ResourceNotFoundException {
		Invent Invent = repository.findById(InventId)
				.orElseThrow(() -> new ResourceNotFoundException("Invent not found  id :: " + InventId));
		repository.delete(Invent);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	 
	  @DeleteMapping("/invents/delete")
	  public ResponseEntity<String> deleteAllInvents() {
	    System.out.println("Delete All Invents...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All Invents have been deleted!", HttpStatus.OK);
	  }
	 
	  @PutMapping("/invents/{id}")
	  public ResponseEntity<Invent> updateInvent(@PathVariable("id") long id, @RequestBody Invent Invent) {
	    System.out.println("Update Invent with ID = " + id + "...");
	    Optional<Invent> InventInfo = repository.findById(id);
	    if (InventInfo.isPresent()) {
	    	Invent invent = InventInfo.get();
	    	invent.setLibelle(Invent.getLibelle());
	      return new ResponseEntity<>(repository.save(Invent), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
}
