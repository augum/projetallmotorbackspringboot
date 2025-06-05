package boutiqueProjet.org.Controller;

import boutiqueProjet.org.Entity.*;
import boutiqueProjet.org.Repository.ArticleRepository;
import boutiqueProjet.org.Repository.CommFRepository;
import boutiqueProjet.org.Repository.CompteurRepository;
import boutiqueProjet.org.Repository.LcommFRepository;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class CommFController {
    @Autowired
    CommFRepository repository;
    @Autowired
    LcommFRepository repo;
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    CompteurRepository comptrepo;
    @Autowired
    ServletContext context;

    @PostMapping("/commsf")
    public ResponseEntity<CommF> createComm(@RequestBody CommF CommF)
            throws JsonParseException, JsonMappingException, Exception{
        repository.save(CommF);
        List<LcommF> lcomms = CommF.getLcomms();

        for (LcommF lc : lcomms) {
            lc.setNumero(CommF.getNumero());
            Optional<Article> ArticleInfo = articleRepository.findByCode(lc.getCode_article());
            if (ArticleInfo.isPresent()) {
                Article Art = ArticleInfo.get();
                Art.setStock(Art.getStock() - lc.getQte());
                Art = articleRepository.save(Art);
            }
            repo.save(lc);
        }
        Optional<Compteur> CompteurInfo = comptrepo.findByAnnee(CommF.getAnnee());
        if (CompteurInfo.isPresent()) {
            Compteur compteur = CompteurInfo.get();
            //compteur.setNumav(compteur.getNumav()+1);
            //compteur =   comptrepo.save(compteur);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

