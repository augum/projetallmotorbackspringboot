package boutiqueProjet.org.Controller;

import boutiqueProjet.org.Entity.Compteur;
import boutiqueProjet.org.Entity.Devis;
import boutiqueProjet.org.Entity.Ldevis;
import boutiqueProjet.org.Exception.ResourceNotFoundException;
import boutiqueProjet.org.Repository.CompteurRepository;
import boutiqueProjet.org.Repository.DevisRepository;
import boutiqueProjet.org.Repository.LdevisRepository;
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
public class DevisController {
	@Autowired
	DevisRepository repository;
	@Autowired
	LdevisRepository repo;
	@Autowired
	CompteurRepository comptrepo;
	@Autowired
	ServletContext context;
	@GetMapping("/devis")
	public List<Devis> getAllDeviss() {
		System.out.println("Get all Deviss...");
		List<Devis> Deviss = new ArrayList<>();
		repository.findAll().forEach(Deviss::add);
		return Deviss;
	}

	@GetMapping("/devis/{id}")
	public ResponseEntity<Devis> getDevisById(@PathVariable(value = "id") Long DevisId)
			throws ResourceNotFoundException {
		Devis Devis = repository.findById(DevisId)
				.orElseThrow(() -> new ResourceNotFoundException("Devis not found for this id :: " + DevisId));
		return ResponseEntity.ok().body(Devis);
	}

	@PostMapping("/devis")
	public ResponseEntity<Devis> createComm( @RequestBody Devis Devis)  throws JsonParseException, JsonMappingException , Exception{
		repository.save(Devis);
		List<Ldevis> ldevis = Devis.getLdeviss();
		for (Ldevis lc : ldevis) {
			lc.setNumero(Devis.getNumero());
			repo.save(lc);
		}

		Optional<Compteur> CompteurInfo = comptrepo.findByAnnee(Devis.getAnnee());
		if (CompteurInfo.isPresent()) {
			Compteur compteur = CompteurInfo.get();
			compteur.setNumdev(compteur.getNumdev()+1);
			compteur =   comptrepo.save(compteur);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/devis/{id}")
	public Map<String, Boolean> deleteDevis(@PathVariable(value = "id") Long DevisId)
			throws ResourceNotFoundException {
		Devis Devis = repository.findById(DevisId)
				.orElseThrow(() -> new ResourceNotFoundException("Devis not found  id :: " + DevisId));
		repository.delete(Devis);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	@DeleteMapping("/devis/delete")
	public ResponseEntity<String> deleteAllDeviss() {
		System.out.println("Delete All Deviss...");
		repository.deleteAll();
		return new ResponseEntity<>("All Deviss have been deleted!", HttpStatus.OK);
	}

	@PutMapping("/devis/{id}")
	public ResponseEntity<Devis> updateDevis(@PathVariable("id") long id, @RequestBody Devis Devis) {
		System.out.println("Update Devis with ID = " + id + "...");
		Optional<Devis> DevisInfo = repository.findById(id);
		if (DevisInfo.isPresent()) {
			Devis devis = DevisInfo.get();
			devis.setLibelle(Devis.getLibelle());
			return new ResponseEntity<>(repository.save(Devis), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
