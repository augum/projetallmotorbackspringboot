package boutiqueProjet.org.Controller;

import boutiqueProjet.org.Entity.Cheque;
import boutiqueProjet.org.Exception.ResourceNotFoundException;
import boutiqueProjet.org.Repository.ChequeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class ChequeController {
	@Autowired
	ChequeRepository repository;
	
	 @GetMapping("/Cheques")
	  public List<Cheque> getAllCheques() {
	    System.out.println("Get all Cheques...");
	    List<Cheque> Cheques = new ArrayList<>();
	    repository.findAll().forEach(Cheques::add);
	    return Cheques;
	  }
	
	@GetMapping("/Cheques/{id}")
	public ResponseEntity<Cheque> getChequeById(@PathVariable(value = "id") Long ChequeId)
			throws ResourceNotFoundException {
		Cheque Cheque = repository.findById(ChequeId)
				.orElseThrow(() -> new ResourceNotFoundException("Cheque not found for this id :: " + ChequeId));
		return ResponseEntity.ok().body(Cheque);
	}

	@PostMapping("/Cheques")
	public  Cheque createCheque( @RequestBody Cheque Cheque) {
	
		return repository.save(Cheque);
		 
	}

	@DeleteMapping("/Cheques/{id}")
	public Map<String, Boolean> deleteCheque(@PathVariable(value = "id") Long ChequeId)
			throws ResourceNotFoundException {
		Cheque Cheque = repository.findById(ChequeId)
				.orElseThrow(() -> new ResourceNotFoundException("Cheque not found  id :: " + ChequeId));
		repository.delete(Cheque);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	 
	  @DeleteMapping("/Cheques/delete")
	  public ResponseEntity<String> deleteAllCheques() {
	    System.out.println("Delete All Cheques...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All Cheques have been deleted!", HttpStatus.OK);
	  }
	 
	  @PutMapping("/Cheques/{id}")
	  public ResponseEntity<Cheque> updateCheque(@PathVariable("id") long id, @RequestBody Cheque Cheque) {
	    System.out.println("Update Cheque with ID = " + id + "...");
	    Optional<Cheque> ChequeInfo = repository.findById(id);
	    if (ChequeInfo.isPresent()) {
	    	Cheque cheque = ChequeInfo.get();
	    
	      return new ResponseEntity<>(repository.save(Cheque), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
}
