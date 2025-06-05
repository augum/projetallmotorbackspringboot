package boutiqueProjet.org.Controller;


import boutiqueProjet.org.Entity.Scategorie;
import boutiqueProjet.org.Exception.ResourceNotFoundException;
import boutiqueProjet.org.Repository.ScategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.*;


@RestController
@RequestMapping("/api")
public class ScategorieController {
	@Autowired
	ScategorieRepository repository;

	 @GetMapping("/scategories")
	  public List<Scategorie> getAllScategories() {
	    System.out.println("Get all Scategories...");
	 
	    List<Scategorie> Scategories = new ArrayList<>();
	    repository.findAll().forEach(Scategories::add);
	 
	    return Scategories;
	  }
	
	 
	 
	 
	 @GetMapping("/scategories/5/{id_categ}")
	
	    public ResponseEntity<List<Scategorie>> listCateg(@PathVariable String id_categ) {
	        
			List<Scategorie> scategories = repository.findByCateg(id_categ);
	       
	        return new ResponseEntity<List<Scategorie>>(scategories, HttpStatus.OK);
	    }

	@GetMapping("/scategories/{code}")
	public ResponseEntity<Scategorie> getScategorieByCode(@PathVariable String code)
			throws ResourceNotFoundException {
		Scategorie Scategorie = repository.findByCode(code)
				.orElseThrow(() -> new ResourceNotFoundException("Scategorie not found for this id :: " + code));
		return ResponseEntity.ok().body(Scategorie);
	}

	
	@PostMapping("/scategories")
	public Scategorie createsCategorie( @RequestBody Scategorie Scategorie) {
		return repository.save(Scategorie);
	}
	

	@DeleteMapping("/scategories/{id}")
	public Map<String, Boolean> deleteScategorie(@PathVariable(value = "id") Long ScategorieId)
			throws ResourceNotFoundException {
		Scategorie Scategorie = repository.findById(ScategorieId)
				.orElseThrow(() -> new ResourceNotFoundException("Categorie not found  id :: " + ScategorieId));

		repository.delete(Scategorie);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	 
	  @DeleteMapping("/scategories/delete")
	  public ResponseEntity<String> deleteAllScategories() {
	    System.out.println("Delete All Scategories...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All Scategories have been deleted!", HttpStatus.OK);
	  }
	 
	

	  @PutMapping("/scategories/{id}")
	  public ResponseEntity<Scategorie> updateScategorie(@PathVariable("id") long id, @RequestBody Scategorie Scategorie) {
	    System.out.println("Update Scategorie with ID = " + id + "...");
	 
	    Optional<Scategorie> ScategorieInfo = repository.findById(id);
	 
	    if (ScategorieInfo.isPresent()) {
	    	Scategorie scategorie = ScategorieInfo.get();
	           scategorie.setLibelle(Scategorie.getLibelle());
	       	      return new ResponseEntity<>(repository.save(Scategorie), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	  
	  @PatchMapping("/scategories/5/{id}")
	  public ResponseEntity<Scategorie> updateScategorie(@PathVariable("id") String id) {
	    System.out.println("Update SCategorie with Code  = " + id + "...");
	 
	    Optional<Scategorie> ScategorieInfo = repository.findByCode(id);
	 
	    if (ScategorieInfo.isPresent()) {
	    	Scategorie scategorie = ScategorieInfo.get();
	           scategorie.setRang(scategorie.getRang()+1);
	         
	      return new ResponseEntity<>(repository.save(scategorie), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

}
