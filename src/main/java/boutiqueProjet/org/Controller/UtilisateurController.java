package boutiqueProjet.org.Controller;

import boutiqueProjet.org.Entity.Utilisateur;
import boutiqueProjet.org.Exception.ResourceNotFoundException;
import boutiqueProjet.org.Repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.*;


@RestController
@RequestMapping("/api")
public class UtilisateurController {
	@Autowired
	UtilisateurRepository repository;

	 @GetMapping("/users")
	  public List<Utilisateur> getAllUtilisateur() {
	    System.out.println("Get all Utilisateur...");
	 
	    List<Utilisateur> Utilisateur = new ArrayList<>();
	    repository.findAll().forEach(Utilisateur::add);
	 
	    return Utilisateur;
	  }

	@GetMapping("/users/{id}")
	public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable(value = "id") Long UtilisateurId)
			throws ResourceNotFoundException {
		Utilisateur Utilisateur = repository.findById(UtilisateurId)
				.orElseThrow(() -> new ResourceNotFoundException("Utilisateur not found for this id :: " + UtilisateurId));
		return ResponseEntity.ok().body(Utilisateur);
	}

	 @GetMapping("/users/5/{login}")
	  public ResponseEntity<Utilisateur> getUtilisateurByLogin(@PathVariable(value = "login") String login)
		  throws ResourceNotFoundException {
		  Utilisateur Utilisateur = repository.findByLogin(login)
				  .orElseThrow(() -> new ResourceNotFoundException("Usernot found for this id : "));
		   return ResponseEntity.ok().body(Utilisateur);
	  }

	@PostMapping("/users")
	public Utilisateur createUtilisateur( @RequestBody Utilisateur Utilisateur) {

		return repository.save(Utilisateur);
	}


	@DeleteMapping("/users/{id}")
	public Map<String, Boolean> deleteUtilisateur(@PathVariable(value = "id") Long UtilisateurId)
			throws ResourceNotFoundException {
		Utilisateur Utilisateur = repository.findById(UtilisateurId)
				.orElseThrow(() -> new ResourceNotFoundException("Utilisateur not found  id :: " + UtilisateurId));

		repository.delete(Utilisateur);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}


	  @DeleteMapping("/users/delete")
	  public ResponseEntity<String> deleteAllUtilisateur() {
	    System.out.println("Delete All Utilisateur...");
	 
	    repository.deleteAll();
	 
	    return new ResponseEntity<>("All Utilisateurs have been deleted!", HttpStatus.OK);
	  }


	  @PutMapping("/users/{id}")
	  public ResponseEntity<Utilisateur> updateUtilisateur(@PathVariable("id") long id, @RequestBody Utilisateur Utilisateur) {
	    System.out.println("Update Utilisateur with ID = " + id + "...");
	 
	    Optional<Utilisateur> UtilisateurInfo = repository.findById(id);
	 
	    if (UtilisateurInfo.isPresent()) {
	    	Utilisateur utilisateur = UtilisateurInfo.get();
	    	utilisateur.setRole(Utilisateur.getRole());
	    	utilisateur.setNom(Utilisateur.getNom());
	    	utilisateur.setLogin(Utilisateur.getLogin()); 
	    	utilisateur.setPwd(Utilisateur.getPwd());
	      return new ResponseEntity<>(repository.save(Utilisateur), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
}
