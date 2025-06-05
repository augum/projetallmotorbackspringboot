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
public class CommPretController {
    @Autowired
    CommPretRepository repository;
    @Autowired
    LcommPretRepository repo;
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    CompteurRepository comptrepo;
    @Autowired
    ServletContext context;

    @GetMapping("/commPrets")
    public List<CommPret> getAllCommsp() {
        System.out.println("Get all Comms mag...");
        List<CommPret> Comms = new ArrayList<>();
        repository.findAll().forEach(Comms::add);
        return Comms;
    }
    @GetMapping("/compretsd")
    public List<CommPret> getAllCommsTp(@RequestParam(name = "mag") String mag, @RequestParam(name = "date") String dateoString ) {
        System.out.println("Get all Comms...");
        List<CommPret> Comms = new ArrayList<>();
        LocalDate dateo = LocalDate.parse(dateoString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        repository.chercherCommandparMagasinetDateP(mag,dateo).forEach(Comms::add);
        return Comms;
    }
    @GetMapping("/commPrets/{id}")
    public ResponseEntity<CommPret> getCommByIdp(@PathVariable(value = "id") Long CommId)
            throws ResourceNotFoundException {
        CommPret Comm = repository.findById(CommId)

                .orElseThrow(() -> new ResourceNotFoundException("Comm not found for this id :: " + CommId));
        return ResponseEntity.ok().body(Comm);
    }

    @PostMapping("/commPrets")
    public ResponseEntity<CommPret> createCommp( @RequestBody CommPret CommPret)
            throws JsonParseException, JsonMappingException, Exception{

        repository.save(CommPret);
        List<LcommPret> lcomms = CommPret.getLcomms();

        for (LcommPret lc : lcomms) {
            lc.setNumero(CommPret.getNumero());
            Optional<Article> ArticleInfo = articleRepository.findByCode(lc.getCode_article());
            if (ArticleInfo.isPresent()) {
                Article Art = ArticleInfo.get();
                //Art.setStock(Art.getStock() - lc.getQte());
                Art = articleRepository.save(Art);
            }
            repo.save(lc);
        }
        Optional<Compteur> CompteurInfo = comptrepo.findByAnnee(CommPret.getAnnee());
        if (CompteurInfo.isPresent()) {
            Compteur compteur = CompteurInfo.get();
            compteur.setNumav(compteur.getNumav()+1);
            compteur =   comptrepo.save(compteur);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/commPrets/{id}")
    public ResponseEntity<CommPret> deleteCommp(@PathVariable(value = "id") Long CommId)
    {
        Optional<CommPret> CommInfo = repository.findById(CommId);
        if (CommInfo.isPresent()) {
            System.out.println("Commande 11");
            CommPret Comm = CommInfo.get();
            repo.deleteByNumero(Comm.getNumero());
            repository.delete(Comm);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/commPrets/delete")
    public ResponseEntity<String> deleteAllCommsp() {
        System.out.println("Delete All Comms...");
        repository.deleteAll();
        return new ResponseEntity<>("All Comms have been deleted!", HttpStatus.OK);
    }

    @PutMapping("/commPrets/{id}")
    public ResponseEntity<CommPret> updateCommp(@PathVariable("id") long id, @RequestBody CommPret Comm) {
        System.out.println("Update Comm with ID = " + id + "...");
        Optional<CommPret> CommInfo = repository.findById(id);
        if (CommInfo.isPresent()) {
            CommPret comm = CommInfo.get();
            comm.setLibelle(Comm.getLibelle());
            return new ResponseEntity<>(repository.save(comm), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
