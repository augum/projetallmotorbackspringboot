package boutiqueProjet.org.Controller;


import boutiqueProjet.org.Entity.Compteur;
import boutiqueProjet.org.Exception.ResourceNotFoundException;
import boutiqueProjet.org.Repository.CompteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.*;



@RestController
@RequestMapping("/api")
public class CompteurController {
	@Autowired
	CompteurRepository repository;
	
	 @GetMapping("/compteurs")
	  public List<Compteur> getAllCompteurs() {
	    System.out.println("Get all Compteurs...");
	 
	    List<Compteur> Compteurs = new ArrayList<>();
	    repository.findAll().forEach(Compteurs::add);
	 
	    return Compteurs;
	  }
	
	@GetMapping("/compteurs/{id}")
	public ResponseEntity<Compteur> getCompteurByAnnee(@PathVariable(value = "id") int id)
			throws ResourceNotFoundException {
		Compteur Compteur = repository.findByAnnee(id)
				.orElseThrow(() -> new ResourceNotFoundException("Compteur not found for this id :: " + id));
		return ResponseEntity.ok().body(Compteur);
	}

	@PostMapping("/compteurs")
	public Compteur createCompteur( @RequestBody Compteur Compteur) {
		return repository.save(Compteur);
	}
	

	@DeleteMapping("/compteurs/{id}")
	public Map<String, Boolean> deleteCompteur(@PathVariable(value = "id") Long CompteurId)
			throws ResourceNotFoundException {
		Compteur Compteur = repository.findById(CompteurId)
				.orElseThrow(() -> new ResourceNotFoundException("Compteur not found  id :: " + CompteurId));

		repository.delete(Compteur);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	 
	  @DeleteMapping("/compteurs/delete")
	  public ResponseEntity<String> deleteAllCompteurs() {
	    System.out.println("Delete All Compteurs...");
	 
	    repository.deleteAll();
	 
	    return new ResponseEntity<>("All Compteurs have been deleted!", HttpStatus.OK);
	  }
	 
	

	  @PutMapping("/compteurs/{id}")
	  public ResponseEntity<Compteur> updateCompteur(@PathVariable("id") long id, @RequestBody Compteur Compteur) {
	    System.out.println("Update Compteur with ID = " + id + "...");
	 
	    Optional<Compteur> CompteurInfo = repository.findById(id);
	 
	    if (CompteurInfo.isPresent()) {
	    	Compteur compteur = CompteurInfo.get();
	          
	           Compteur.setNumbl(Compteur.getNumbl());
	           Compteur.setNumblf(Compteur.getNumblf());
	           Compteur.setNumcomm(Compteur.getNumcomm());
	           Compteur.setNumcommf(Compteur.getNumcommf());
	           
	      return new ResponseEntity<>(repository.save(compteur), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

}
