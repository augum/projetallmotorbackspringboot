package boutiqueProjet.org.Controller;

import boutiqueProjet.org.Entity.Compteur;
import boutiqueProjet.org.Entity.Fcomm;
import boutiqueProjet.org.Entity.Lfcomm;
import boutiqueProjet.org.Exception.ResourceNotFoundException;
import boutiqueProjet.org.Repository.CompteurRepository;
import boutiqueProjet.org.Repository.FcommRepository;
import boutiqueProjet.org.Repository.LfcommRepository;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import java.util.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class FcommController {
    @Autowired
    FcommRepository repository;
    @Autowired
    LfcommRepository repo;
    @Autowired
    CompteurRepository comptrepo;
    @Autowired
    ServletContext context;
    @GetMapping("/fcomms")
    public List<Fcomm> getAllFcomms() {
        System.out.println("Get all Fcomms...");
        List<Fcomm> Fcomms = new ArrayList<>();
        repository.findAll().forEach(Fcomms::add);
        return Fcomms;
    }

    @GetMapping("/fcomms/{id}")
    public ResponseEntity<Fcomm> getFcommById(@PathVariable(value = "id") Long FcommId)
            throws ResourceNotFoundException {
        Fcomm Fcomm = repository.findById(FcommId)
                .orElseThrow(() -> new ResourceNotFoundException("Fcomm not found for this id :: " + FcommId));
        return ResponseEntity.ok().body(Fcomm);
    }

    @PostMapping("/fcomms")
    public ResponseEntity<Fcomm> createComm( @RequestBody Fcomm Fcomm)  throws JsonParseException, JsonMappingException, Exception{
        repository.save(Fcomm);
        List<Lfcomm> lfcomms = Fcomm.getLfcomms();
        for (Lfcomm lc : lfcomms) {
            lc.setNumero(Fcomm.getNumero());
            repo.save(lc);
        }

        Optional<Compteur> CompteurInfo = comptrepo.findByAnnee(Fcomm.getAnnee());
        if (CompteurInfo.isPresent()) {
            Compteur compteur = CompteurInfo.get();
            compteur.setNumcommf(compteur.getNumcommf()+1);
            compteur =   comptrepo.save(compteur);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/fcomms/{id}")
    public Map<String, Boolean> deleteFcomm(@PathVariable(value = "id") Long FcommId)
            throws ResourceNotFoundException {
        Fcomm Fcomm = repository.findById(FcommId)
                .orElseThrow(() -> new ResourceNotFoundException("Fcomm not found  id :: " + FcommId));
        repository.delete(Fcomm);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @DeleteMapping("/fcomms/delete")
    public ResponseEntity<String> deleteAllFcomms() {
        System.out.println("Delete All Fcomms...");
        repository.deleteAll();
        return new ResponseEntity<>("All Fcomms have been deleted!", HttpStatus.OK);
    }

    @PutMapping("/fcomms/{id}")
    public ResponseEntity<Fcomm> updateFcomm(@PathVariable("id") long id, @RequestBody Fcomm Fcomm) {
        System.out.println("Update Fcomm with ID = " + id + "...");
        Optional<Fcomm> FcommInfo = repository.findById(id);
        if (FcommInfo.isPresent()) {
            Fcomm fcomm = FcommInfo.get();
            fcomm.setLibelle(Fcomm.getLibelle());
            return new ResponseEntity<>(repository.save(Fcomm), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

