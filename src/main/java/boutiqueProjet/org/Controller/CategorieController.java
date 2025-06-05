package boutiqueProjet.org.Controller;

import boutiqueProjet.org.Entity.Categorie;
import boutiqueProjet.org.Exception.ResourceNotFoundException;
import boutiqueProjet.org.Repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.*;


@RestController
@RequestMapping("/api")
public class CategorieController {
	@Autowired
	CategorieRepository repository;

	 @GetMapping("/categories")
	  public List<Categorie> getAllCategories() {
	    System.out.println("Get all Categories...");
	 
	    List<Categorie> Categories = new ArrayList<>();
	    repository.findAll().forEach(Categories::add);
	 
	    return Categories;
	  }

	@GetMapping("/categories/{id}")
	public ResponseEntity<Categorie> getCategorieById(@PathVariable(value = "id") Long CategorieId)
			throws ResourceNotFoundException {
		Categorie Categorie = repository.findById(CategorieId)
				.orElseThrow(() -> new ResourceNotFoundException("Categorie not found for this id :: " + CategorieId));
		return ResponseEntity.ok().body(Categorie);
	}

	@PostMapping("/categories")
	public Categorie createCategorie(@RequestBody Categorie Categorie) {

	 	return repository.save(Categorie);
	}


	@DeleteMapping("/categories/{id}")
	public Map<String, Boolean> deleteCategorie(@PathVariable(value = "id") Long CategorieId)
			throws ResourceNotFoundException {
		Categorie Categorie = repository.findById(CategorieId)
				.orElseThrow(() -> new ResourceNotFoundException("Categorie not found  id :: " + CategorieId));

		repository.delete(Categorie);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}


	  @DeleteMapping("/categories/delete")
	  public ResponseEntity<String> deleteAllCategories() {
	    System.out.println("Delete All Categories...");
	 
	    repository.deleteAll();
	 
	    return new ResponseEntity<>("All Categories have been deleted!", HttpStatus.OK);
	  }


	  @PutMapping("/categories/{id}")
	  public ResponseEntity<Categorie> updateCategorie(@PathVariable("id") long id, @RequestBody Categorie Categorie) {
	    System.out.println("Update Categorie with ID = " + id + "...");
	 
	    Optional<Categorie> CategorieInfo = repository.findById(id);
	 
	    if (CategorieInfo.isPresent()) {
	    	Categorie categorie = CategorieInfo.get();
	          categorie.setLibelle(Categorie.getLibelle());
	           
	      return new ResponseEntity<>(repository.save(Categorie), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
}
