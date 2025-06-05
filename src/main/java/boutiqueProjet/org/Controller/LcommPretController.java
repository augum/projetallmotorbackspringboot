package boutiqueProjet.org.Controller;

import boutiqueProjet.org.Entity.LcommPret;
import boutiqueProjet.org.Exception.ResourceNotFoundException;
import boutiqueProjet.org.Repository.LcommPretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class LcommPretController {
    @Autowired
    LcommPretRepository repository;
    @GetMapping("/lcommsPret")
    public List<LcommPret> getAllLcommsp() {
        System.out.println("Get all Lcomms...");

        List<LcommPret> LcommPret = new ArrayList<>();
        repository.findAll().forEach(LcommPret::add);
        return LcommPret;
    }
    @GetMapping("/lcommPret/{numero}")
    public List<LcommPret> getLlivrBynumerop(@PathVariable(value = "numero") int numero)
    {
        List<LcommPret> LcommPret = new ArrayList<>();
        return repository.findAllBynumero(numero);
    }
    /*@GetMapping("/lcommMag/{numero}")
    public List<LcommMag> getAllByNumero(@PathVariable(value = "numero") int numero) {
        System.out.println("Get all Lcomms...");

        List<LcommMag> LcommMag = new ArrayList<>();
        repository.findAllByNumero(numero).forEach(LcommMag::add);

        return LcommMag;
    }*/

    @GetMapping("/lcommsPret/{id}")
    public ResponseEntity<LcommPret> getLcommByIdp(@PathVariable(value = "id") Long LcommId)
            throws ResourceNotFoundException {
        LcommPret LcommPret = repository.findById(LcommId)
                .orElseThrow(() -> new ResourceNotFoundException("Lcomm not found for this id :: " + LcommId));
        return ResponseEntity.ok().body(LcommPret);
    }

    @PostMapping("/lcommsPret")
    public  LcommPret createLcommp( @RequestBody LcommPret Lcomm) {

        return repository.save(Lcomm);


    }

    @DeleteMapping("/lcommsPret/{id}")
    public Map<String, Boolean> deleteLcommp(@PathVariable(value = "id") Long LcommId)
            throws ResourceNotFoundException {
        LcommPret Lcomm = repository.findById(LcommId)
                .orElseThrow(() -> new ResourceNotFoundException("Lcomm not found  id :: " + LcommId));

        repository.delete(Lcomm);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PutMapping("/lcommsPret/{id}")
    public ResponseEntity<LcommPret> updateLcommp(@PathVariable("id") long id, @RequestBody LcommPret Lcomm) {
        System.out.println("Update Lcomm with ID = " + id + "...");

        Optional<LcommPret> LcommInfo = repository.findById(id);

        if (LcommInfo.isPresent()) {
            LcommPret lcomm = LcommInfo.get();
            lcomm.setLibart(Lcomm.getLibart());

            return new ResponseEntity<>(repository.save(Lcomm), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
