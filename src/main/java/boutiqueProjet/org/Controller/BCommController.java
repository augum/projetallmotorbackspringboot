package boutiqueProjet.org.Controller;

import boutiqueProjet.org.Entity.*;
import boutiqueProjet.org.Exception.ResourceNotFoundException;
import boutiqueProjet.org.Repository.ArticleRepository;
import boutiqueProjet.org.Repository.BcommRepository;
import boutiqueProjet.org.Repository.BlcommRepository;
import boutiqueProjet.org.Repository.CompteurRepository;
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
public class BCommController {
    @Autowired
    BcommRepository repository;
    @Autowired
    BlcommRepository repo;
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    CompteurRepository comptrepo;
    @Autowired
    ServletContext context;
    //methode pour recuperer la liste des toutes les commandes

    @GetMapping("/bcomms")
    public List<Bcomm> getAllComms1() {
        System.out.println("Get all Comms...");
        List<Bcomm> Comms = new ArrayList<>();
        repository.findAll().forEach(Comms::add);
        return Comms;
    }
    //retourne la somme des commandes d'une boutique pour un interval de temps

    @GetMapping("/bsommetout")
    public double getForecastTotals1(@RequestParam(name = "mag") String mag, @RequestParam(name = "date1") String date1oString,@RequestParam(name = "date2") String date2oString,@RequestParam(name = "modepayement") String modepayement ) {
        LocalDate dateo1 = LocalDate.parse(date1oString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate dateo2 = LocalDate.parse(date2oString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return repository.somme(mag,dateo1,dateo2,modepayement);
    }
    //calcule la somme des commandes pour une boutique a la date du jour selon le mode de paiement

    @GetMapping("/bsommeun")
    public double getSomme1(@RequestParam(name = "mag") String mag, @RequestParam(name = "date") String dateoString,@RequestParam(name = "modepayement") String modepayement ) {
        System.out.println("Get all somme un...");
        List<Bcomm> Comms = new ArrayList<>();
        LocalDate dateo = LocalDate.parse(dateoString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return repository.sommeS(mag,dateo,modepayement);
    }
    //liste des commandes pour une boutique a la date du jour

    @GetMapping("/bcommsd")
    public List<Bcomm> getAllCommsT1(@RequestParam(name = "mag") String mag,@RequestParam(name = "date") String dateoString) {
        System.out.println("Get all Comms...");
        List<Bcomm> Comms = new ArrayList<>();
        LocalDate dateo = LocalDate.parse(dateoString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        repository.chercherCommandparMagasinetDate(mag,dateo).forEach(Comms::add);
        return Comms;
    }
    @GetMapping("/bcommsd2")
    public List<Bcomm> getAllCommsT1L(@RequestParam(name = "libelle") String libelle,@RequestParam(name = "date") String dateoString) {
        System.out.println("Get all Comms...");
        List<Bcomm> Comms = new ArrayList<>();
        LocalDate dateo = LocalDate.parse(dateoString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        repository.chercherCommandparMagasinetDateL(libelle,dateo).forEach(Comms::add);
        return Comms;
    }
    //liste des commandes selon les boutiques pour une periode

    @GetMapping("/bcommsdI")
    public List<Bcomm> getAllCommsTI1(@RequestParam(name = "mag") String mag, @RequestParam(name = "date1") String date1oString,@RequestParam(name = "date2") String date2oString,@RequestParam(name = "modepayement") String modepayement ) {
        System.out.println("Get all Comms by interval date...");
        List<Bcomm> Comms = new ArrayList<>();
        LocalDate dateo1 = LocalDate.parse(date1oString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate dateo2 = LocalDate.parse(date2oString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        repository.chercherCommandparMagasinetDateIn(mag,dateo1,dateo2,modepayement).forEach(Comms::add);
        return Comms;
    }

    @GetMapping("/bcomms/{id}")
    public ResponseEntity<Bcomm> getCommById1(@PathVariable(value = "id") Long CommId)
            throws ResourceNotFoundException {
        Bcomm Comm = repository.findById(CommId)

                .orElseThrow(() -> new ResourceNotFoundException("Comm not found for this id :: " + CommId));
        return ResponseEntity.ok().body(Comm);
    }

    @PostMapping("/bcomms")
    public ResponseEntity<Bcomm> createComm1( @RequestBody Bcomm Comm)
            throws JsonParseException, JsonMappingException , Exception{
        repository.save(Comm);
        List<BLcomm> lcomms = Comm.getLcomms();
        for (BLcomm lc : lcomms) {
            lc.setNumero(Comm.getNumero());
            Optional<Article> ArticleInfo = articleRepository.findByCode(lc.getCode_article());
            if (ArticleInfo.isPresent()) {
                Article Art = ArticleInfo.get();
                //Art.setStock(Art.getStock() - lc.getQte());
                Art = articleRepository.save(Art);
            }
            repo.save(lc);
        }
        Optional<Compteur> CompteurInfo = comptrepo.findByAnnee(Comm.getAnnee());
        if (CompteurInfo.isPresent()) {
            Compteur compteur = CompteurInfo.get();
            compteur.setNumcommb(compteur.getNumcommb()+1);
            compteur =   comptrepo.save(compteur);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/bcomms/{id}")
    public ResponseEntity<Comm> deleteComm1(@PathVariable(value = "id") Long CommId)
    {
        Optional<Bcomm> CommInfo = repository.findById(CommId);
        if (CommInfo.isPresent()) {
            System.out.println("Commande 11");
            Bcomm Comm = CommInfo.get();
            repo.deleteByNumero(Comm.getNumero());
            repository.delete(Comm);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/bcomms/delete")
    public ResponseEntity<String> deleteAllComms1() {
        System.out.println("Delete All Comms...");
        repository.deleteAll();
        return new ResponseEntity<>("All Comms have been deleted!", HttpStatus.OK);
    }
    @PutMapping("/bcomms/{id}")
    public ResponseEntity<Bcomm> updateComm1(@PathVariable("id") long id, @RequestBody Bcomm Comm) {
        System.out.println("Update Comm with ID = " + id + "...");
        Optional<Bcomm> CommInfo = repository.findById(id);
        if (CommInfo.isPresent()) {
            Bcomm comm = CommInfo.get();
            comm.setLibelle(Comm.getLibelle());
            return new ResponseEntity<>(repository.save(comm), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
