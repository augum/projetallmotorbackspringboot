package boutiqueProjet.org.Controller;

import boutiqueProjet.org.Entity.Compteur;
import boutiqueProjet.org.Entity.Lrecouf;
import boutiqueProjet.org.Entity.Recouf;
import boutiqueProjet.org.Exception.ResourceNotFoundException;
import boutiqueProjet.org.Repository.CompteurRepository;
import boutiqueProjet.org.Repository.LrecoufRepository;
import boutiqueProjet.org.Repository.RecoufRepository;
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
public class RecoufController {
	@Autowired
	RecoufRepository repository;
	LrecoufRepository repo;
	@Autowired
	CompteurRepository comptrepo;
	@Autowired
    ServletContext context;
	 @GetMapping("/Recoufs")
	  public List<Recouf> getAllRecoufs() {
	    System.out.println("Get all Recoufs...");
	    List<Recouf> Recoufs = new ArrayList<>();
	    repository.findAll().forEach(Recoufs::add);
	    return Recoufs;
	  }
	
	@GetMapping("/Recoufs/{id}")
	public ResponseEntity<Recouf> getRecoufById(@PathVariable(value = "id") Long RecoufId)
			throws ResourceNotFoundException {
		Recouf Recouf = repository.findById(RecoufId)
				.orElseThrow(() -> new ResourceNotFoundException("Recouf not found for this id :: " + RecoufId));
		return ResponseEntity.ok().body(Recouf);
	}

	@PostMapping("/Recoufs")
	public ResponseEntity<Recouf> createRecouf( @RequestBody Recouf Recouf)  throws JsonParseException, JsonMappingException , Exception{
		  repository.save(Recouf);
		  List<Lrecouf> lrecoufs = Recouf.getLrecoufs();
		    for (Lrecouf lc : lrecoufs) {
	          	lc.setNumero(Recouf.getNumero());
	          	repo.save(lc);
		       }	 
		    Optional<Compteur> CompteurInfo = comptrepo.findByAnnee(Recouf.getAnnee());
	     	if (CompteurInfo.isPresent()) {
		    	Compteur compteur = CompteurInfo.get();
		           compteur.setNumregf(compteur.getNumregf()+1);
		         compteur =   comptrepo.save(compteur);
	     	}
			 return new ResponseEntity<>(HttpStatus.OK);
		} 

	@DeleteMapping("/Recoufs/{id}")
	public Map<String, Boolean> deleteRecouf(@PathVariable(value = "id") Long RecoufId)
			throws ResourceNotFoundException {
		Recouf Recouf = repository.findById(RecoufId)
				.orElseThrow(() -> new ResourceNotFoundException("Recouf not found  id :: " + RecoufId));
		repository.delete(Recouf);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	 
	  @DeleteMapping("/Recoufs/delete")
	  public ResponseEntity<String> deleteAllRecoufs() {
	    System.out.println("Delete All Recoufs...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All Recoufs have been deleted!", HttpStatus.OK);
	  }
	 
	  @PutMapping("/Recoufs/{id}")
	  public ResponseEntity<Recouf> updateRecouf(@PathVariable("id") long id, @RequestBody Recouf Recouf) {
	    System.out.println("Update Recouf with ID = " + id + "...");
	    Optional<Recouf> RecoufInfo = repository.findById(id);
	    if (RecoufInfo.isPresent()) {
	    	Recouf recouf = RecoufInfo.get();
	    	recouf.setLibelle(Recouf.getLibelle());
	      return new ResponseEntity<>(repository.save(Recouf), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }		
}
