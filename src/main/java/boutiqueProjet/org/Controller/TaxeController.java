package boutiqueProjet.org.Controller;

import boutiqueProjet.org.Entity.Taxe;
import boutiqueProjet.org.Repository.TaxeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class TaxeController {
    @Autowired
    TaxeRepository repository;

    @GetMapping("/taxes/{id}")
    public ResponseEntity<Taxe> getmagById(@PathVariable(value = "id") Long Id)
            throws ResourceNotFoundException {
        Taxe taxe = repository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Taxe not found for this id :: " + Id));
        return ResponseEntity.ok().body(taxe);
    }
}
