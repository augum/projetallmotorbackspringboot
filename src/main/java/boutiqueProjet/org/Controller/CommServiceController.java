package boutiqueProjet.org.Controller;

import boutiqueProjet.org.Entity.CommService;
import boutiqueProjet.org.Entity.Compteur;
import boutiqueProjet.org.Entity.LcommService;
import boutiqueProjet.org.Exception.ResourceNotFoundException;
import boutiqueProjet.org.Repository.CommServiceRepository;
import boutiqueProjet.org.Repository.CompteurRepository;
import boutiqueProjet.org.Repository.LcommServiceRepository;
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
public class CommServiceController {
	@Autowired
	CommServiceRepository repository;
	@Autowired
	LcommServiceRepository repo;
	@Autowired
	CompteurRepository comptrepo;
	@Autowired
    ServletContext context;
	 @GetMapping("/commServices")
	  public List<CommService> getAllCommServices() {
	    System.out.println("Get all CommServices...");
	    List<CommService> CommServices = new ArrayList<>();
	    repository.findAll().forEach(CommServices::add);
	    return CommServices;
	  }
	
	@GetMapping("/commServices/{id}")
	public ResponseEntity<CommService> getCommServiceById(@PathVariable(value = "id") Long CommServiceId)
			throws ResourceNotFoundException {
		CommService CommService = repository.findById(CommServiceId)
				.orElseThrow(() -> new ResourceNotFoundException("CommService not found for this id :: " + CommServiceId));
		return ResponseEntity.ok().body(CommService);
	}

	
	
	@PostMapping("/commServices")
	public ResponseEntity<CommService> createComms( @RequestBody CommService CommService)  throws JsonParseException, JsonMappingException , Exception{
		repository.save(CommService);
		List<LcommService> lcomms = CommService.getLcomms();
	    for (LcommService lc : lcomms) {
	        lc.setNumero(CommService.getNumero());
       		repo.save(lc);
	       }	 
	
     Optional<Compteur> CompteurInfo = comptrepo.findByAnnee(CommService.getAnnee());
     	if (CompteurInfo.isPresent()) {
	    	Compteur compteur = CompteurInfo.get();
	           compteur.setNumcomm(compteur.getNumcomm()+1);
	         compteur =   comptrepo.save(compteur);
     	}
		 return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/commServices/{id}")
	public Map<String, Boolean> deleteCommService(@PathVariable(value = "id") Long CommServiceId)
			throws ResourceNotFoundException {
		CommService CommService = repository.findById(CommServiceId)
				.orElseThrow(() -> new ResourceNotFoundException("CommService not found  id :: " + CommServiceId));
		repository.delete(CommService);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	 
	  @DeleteMapping("/CommServices/delete")
	  public ResponseEntity<String> deleteAllCommServices() {
	    System.out.println("Delete All CommServices...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All CommServices have been deleted!", HttpStatus.OK);
	  }
	 
	  @PutMapping("/commServices/{id}")
	  public ResponseEntity<CommService> updateCommService(@PathVariable("id") long id, @RequestBody CommService CommService) {
	    System.out.println("Update CommService with ID = " + id + "...");
	    Optional<CommService> CommServiceInfo = repository.findById(id);
	    if (CommServiceInfo.isPresent()) {
	    	CommService commService = CommServiceInfo.get();
	    	commService.setLibelle(CommService.getLibelle());
	      return new ResponseEntity<>(repository.save(CommService), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
}
