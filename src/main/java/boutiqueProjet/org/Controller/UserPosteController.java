package boutiqueProjet.org.Controller;


import boutiqueProjet.org.Entity.UserPoste;
import boutiqueProjet.org.Exception.ResourceNotFoundException;
import boutiqueProjet.org.Repository.UserPosteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")

public class UserPosteController {
	@Autowired
	UserPosteRepository repository;
	
	 @GetMapping("/usersp")
	  public List<UserPoste> getAllUserPoste() {
	    System.out.println("Get all UserPoste...");
	 
	    List<UserPoste> UserPoste = new ArrayList<>();
	    repository.findAll().forEach(UserPoste::add);
	 
	    return UserPoste;
	  }
	
	@GetMapping("/usersp/{id}")
	public ResponseEntity<UserPoste> getUserPosteById(@PathVariable(value = "id") Long UserPosteId)
			throws ResourceNotFoundException {
		UserPoste UserPoste = repository.findById(UserPosteId)
				.orElseThrow(() -> new ResourceNotFoundException("UserPoste not found for this id :: " + UserPosteId));
		return ResponseEntity.ok().body(UserPoste);
	}

	 
	 
	
	@PostMapping("/usersp")
	public UserPoste createUserPoste( @RequestBody UserPoste UserPoste) {
		return repository.save(UserPoste);
	}
	

	@DeleteMapping("/usersp/{id}")
	public Map<String, Boolean> deleteUserPoste(@PathVariable(value = "id") Long UserPosteId)
			throws ResourceNotFoundException {
		UserPoste UserPoste = repository.findById(UserPosteId)
				.orElseThrow(() -> new ResourceNotFoundException("UserPoste not found  id :: " + UserPosteId));

		repository.delete(UserPoste);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	 
	  @DeleteMapping("/usersp/delete")
	  public ResponseEntity<String> deleteAllUserPoste() {
	    System.out.println("Delete All UserPoste...");
	 
	    repository.deleteAll();
	 
	    return new ResponseEntity<>("All UserPostes have been deleted!", HttpStatus.OK);
	  }
	 
	

	  @PutMapping("/usersp/{id}")
	  public ResponseEntity<UserPoste> updateUserPoste(@PathVariable("id") long id, @RequestBody UserPoste UserPoste) {
	    System.out.println("Update UserPoste with ID = " + id + "...");
	 
	    Optional<UserPoste> UserPosteInfo = repository.findById(id);
	 
	    if (UserPosteInfo.isPresent()) {
	    	UserPoste userPoste = UserPosteInfo.get();
	    	userPoste.setRole(UserPoste.getRole());
	    	userPoste.setNom(UserPoste.getNom());
	    	userPoste.setMat(UserPoste.getMat()); 
	    	userPoste.setPwd(UserPoste.getPwd());
	      return new ResponseEntity<>(repository.save(UserPoste), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }	

}
