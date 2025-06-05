package boutiqueProjet.org.Controller;


import boutiqueProjet.org.Entity.LcommF;
import boutiqueProjet.org.Exception.ResourceNotFoundException;
import boutiqueProjet.org.Repository.LcommFRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;



@RestController
@RequestMapping("/api")
public class LcommFController {
    @Autowired
    LcommFRepository repository;
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/lcommsf")
    public List<LcommF> getAllLcomms() {
        System.out.println("Get all Lcomms...");

        List<LcommF> Lcomms = new ArrayList<>();
        repository.findAll().forEach(Lcomms::add);

        return Lcomms;
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/lcomf/{numero}")
    public List<LcommF> getLlivrBynumero(@PathVariable(value = "numero") int numero)
    {
        List<LcommF> Lcomm = new ArrayList<>();
        return repository.findAllBynumero(numero);
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/lcommsf/{id}")
    public List<LcommF> getAllByNumero(@PathVariable(value = "id") int numero) {
        System.out.println("Get all Lcomms...");

        List<LcommF> Lcomms = new ArrayList<>();
        repository.findAllByNumero(numero).forEach(Lcomms::add);

        return Lcomms;
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/lcommssf/{id}")
    public ResponseEntity<LcommF> getLcommById(@PathVariable(value = "id") Long LcommId)
            throws ResourceNotFoundException {
        LcommF Lcomm = repository.findById(LcommId)
                .orElseThrow(() -> new ResourceNotFoundException("Lcomm not found for this id :: " + LcommId));
        return ResponseEntity.ok().body(Lcomm);
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/lcommsf")
    public  LcommF createLcomm( @RequestBody LcommF LcommF) {

        return repository.save(LcommF);


    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/lcommsf/{id}")
    public Map<String, Boolean> deleteLcomm(@PathVariable(value = "id") Long LcommId)
            throws ResourceNotFoundException {
        LcommF Lcomm = repository.findById(LcommId)
                .orElseThrow(() -> new ResourceNotFoundException("Lcomm not found  id :: " + LcommId));

        repository.delete(Lcomm);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/lcommsf/{id}")
    public ResponseEntity<LcommF> updateLcomm(@PathVariable("id") long id, @RequestBody LcommF Lcomm) {
        System.out.println("Update Lcomm with ID = " + id + "...");

        Optional<LcommF> LcommInfo = repository.findById(id);

        if (LcommInfo.isPresent()) {
            LcommF lcomm = LcommInfo.get();
            lcomm.setLibart(Lcomm.getLibart());

            return new ResponseEntity<>(repository.save(Lcomm), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
