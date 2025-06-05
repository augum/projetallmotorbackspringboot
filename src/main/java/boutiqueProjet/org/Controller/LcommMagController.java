package boutiqueProjet.org.Controller;

import boutiqueProjet.org.Entity.LcommMag;
import boutiqueProjet.org.Exception.ResourceNotFoundException;
import boutiqueProjet.org.Repository.lcommMagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class LcommMagController {
    @Autowired
    lcommMagRepository repository;
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/lcommsMag")
    public List<LcommMag> getAllLcomms() {
        System.out.println("Get all Lcomms...");

        List<LcommMag> LcommMag = new ArrayList<>();
        repository.findAll().forEach(LcommMag::add);

        return LcommMag;
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/lcommMag/{numero}")
    public List<LcommMag> getLlivrBynumero(@PathVariable(value = "numero") String numero)
    {
        List<LcommMag> LcommMag = new ArrayList<>();
        return repository.findAllBynumero(numero);
    }
    /*@GetMapping("/lcommMag/{numero}")
    public List<LcommMag> getAllByNumero(@PathVariable(value = "numero") int numero) {
        System.out.println("Get all Lcomms...");

        List<LcommMag> LcommMag = new ArrayList<>();
        repository.findAllByNumero(numero).forEach(LcommMag::add);

        return LcommMag;
    }*/
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/lcommsMag/{id}")
    public ResponseEntity<LcommMag> getLcommById(@PathVariable(value = "id") Long LcommId)
            throws ResourceNotFoundException {
        LcommMag LcommMag = repository.findById(LcommId)
                .orElseThrow(() -> new ResourceNotFoundException("Lcomm not found for this id :: " + LcommId));
        return ResponseEntity.ok().body(LcommMag);
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/lcommsMag")
    public  LcommMag createLcomm( @RequestBody LcommMag Lcomm) {

        return repository.save(Lcomm);


    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/lcommsMag/{id}")
    public Map<String, Boolean> deleteLcomm(@PathVariable(value = "id") Long LcommId)
            throws ResourceNotFoundException {
        LcommMag Lcomm = repository.findById(LcommId)
                .orElseThrow(() -> new ResourceNotFoundException("Lcomm not found  id :: " + LcommId));

        repository.delete(Lcomm);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/lcommsMag/{id}")
    public ResponseEntity<LcommMag> updateLcomm(@PathVariable("id") long id, @RequestBody LcommMag Lcomm) {
        System.out.println("Update Lcomm with ID = " + id + "...");

        Optional<LcommMag> LcommInfo = repository.findById(id);

        if (LcommInfo.isPresent()) {
            LcommMag lcomm = LcommInfo.get();
            lcomm.setLibart(Lcomm.getLibart());

            return new ResponseEntity<>(repository.save(Lcomm), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
