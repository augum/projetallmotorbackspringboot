package boutiqueProjet.org.Controller;

import boutiqueProjet.org.Entity.*;
import boutiqueProjet.org.Exception.ResourceNotFoundException;
import boutiqueProjet.org.Repository.*;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class CommMagController {
    @Autowired
    CommMagRepository repository;
    @Autowired
    lcommMagRepository repo;
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    CompteurRepository comptrepo;
    @Autowired
    ServletContext context;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/commMags")
    public List<CommMag> getAllComms() {
        System.out.println("Get all Comms mag...");
        List<CommMag> Comms = new ArrayList<>();
        repository.findAll().forEach(Comms::add);
        return Comms;
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/commagsd")
    public List<CommMag> getAllCommsT(@RequestParam(name = "mag") String mag, @RequestParam(name = "date") String dateoString ) {
        System.out.println("Get all Comms...");
        List<CommMag> Comms = new ArrayList<>();
        LocalDate dateo = LocalDate.parse(dateoString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        repository.chercherCommandparMagasinetDate(mag,dateo).forEach(Comms::add);
        return Comms;
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/commMags/{id}")
    public ResponseEntity<CommMag> getCommById(@PathVariable(value = "id") Long CommId)
            throws ResourceNotFoundException {
        CommMag Comm = repository.findById(CommId)

                .orElseThrow(() -> new ResourceNotFoundException("Comm not found for this id :: " + CommId));
        return ResponseEntity.ok().body(Comm);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/commMags")
    public ResponseEntity<CommMag> createComm( @RequestBody CommMag CommMag)
            throws JsonParseException, JsonMappingException, Exception{

        repository.save(CommMag);
        List<LcommMag> lcomms = CommMag.getLcomms();

        for (LcommMag lc : lcomms) {
            lc.setNumero(CommMag.getNumero());
            Optional<Article> ArticleInfo = articleRepository.findByCode(lc.getCode_article());
            if (ArticleInfo.isPresent()) {
                Article Art = ArticleInfo.get();
                Art.setStock(Art.getStock() - lc.getQte());
                Art = articleRepository.save(Art);
            }
            repo.save(lc);
        }
        Optional<Compteur> CompteurInfo = comptrepo.findByAnnee(CommMag.getAnnee());
        if (CompteurInfo.isPresent()) {
            Compteur compteur = CompteurInfo.get();
            compteur.setNumav(compteur.getNumav()+1);
            compteur =   comptrepo.save(compteur);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/commMags/{id}")
    public ResponseEntity<Comm> deleteComm(@PathVariable(value = "id") Long CommId)
    {
        Optional<CommMag> CommInfo = repository.findById(CommId);
        if (CommInfo.isPresent()) {
            System.out.println("Commande 11");
            CommMag Comm = CommInfo.get();
            repo.deleteByNumero(Comm.getNumero());
            repository.delete(Comm);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/commMags/delete")
    public ResponseEntity<String> deleteAllComms() {
        System.out.println("Delete All Comms...");
        repository.deleteAll();
        return new ResponseEntity<>("All Comms have been deleted!", HttpStatus.OK);
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/commMags/{id}")
    public ResponseEntity<CommMag> updateComm(@PathVariable("id") long id, @RequestBody CommMag Comm) {
        System.out.println("Update Comm with ID = " + id + "...");
        Optional<CommMag> CommInfo = repository.findById(id);
        if (CommInfo.isPresent()) {
            CommMag comm = CommInfo.get();
            comm.setLibelle(Comm.getLibelle());
            return new ResponseEntity<>(repository.save(comm), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
