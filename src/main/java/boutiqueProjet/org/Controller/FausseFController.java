package boutiqueProjet.org.Controller;

import boutiqueProjet.org.Entity.*;
import boutiqueProjet.org.Repository.*;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FausseFController {
    @Autowired
    FausseFRepository repository;
    @Autowired
    LfaussefRepository repo;
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    CompteurRepository comptrepo;
    @Autowired
    ServletContext context;
    @GetMapping("/commFau")
    public List<FausseF> getAllComms() {
        System.out.println("Get all Comms mag...");
        List<FausseF> Comms = new ArrayList<>();
        repository.findAll().forEach(Comms::add);
        return Comms;
    }
    @PostMapping("/commF")
    public ResponseEntity<FausseF> createCommf(@RequestBody FausseF FausseF)
            throws JsonParseException, JsonMappingException, Exception{

        repository.save(FausseF);
        List<LfausseF> lcomms = FausseF.getLcomms();

        for (LfausseF lc : lcomms) {
            lc.setNumero(FausseF.getNumero());
            Optional<Article> ArticleInfo = articleRepository.findByCode(lc.getCode_article());
            if (ArticleInfo.isPresent()) {
                Article Art = ArticleInfo.get();
                //Art.setStock(Art.getStock() - lc.getQte());
                Art = articleRepository.save(Art);
            }
            repo.save(lc);
        }
        Optional<Compteur> CompteurInfo = comptrepo.findByAnnee(FausseF.getAnnee());
        if (CompteurInfo.isPresent()) {
            Compteur compteur = CompteurInfo.get();
            //compteur.setNumav(compteur.getNumav()+1);
            //compteur =   comptrepo.save(compteur);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
