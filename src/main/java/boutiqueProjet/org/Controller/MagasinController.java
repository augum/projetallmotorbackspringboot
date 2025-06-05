package boutiqueProjet.org.Controller;

import boutiqueProjet.org.Entity.Magasin;
import boutiqueProjet.org.Repository.MagasinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MagasinController {
    @Autowired
    MagasinRepository repository;

    @GetMapping("/magasins")
    public List<Magasin> getAllMagasins() {
        System.out.println("Get all Clients...");
        List<Magasin> Magasins = new ArrayList<>();
        repository.findAll().forEach(Magasins::add);
        return Magasins;
    }

    @GetMapping("/magasins/{id}")
    public ResponseEntity<Magasin> getmagById(@PathVariable(value = "id") Long Id)
            throws ResourceNotFoundException {
        Magasin mag = repository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Magasin not found for this id :: " + Id));
        return ResponseEntity.ok().body(mag);
    }
   
}
