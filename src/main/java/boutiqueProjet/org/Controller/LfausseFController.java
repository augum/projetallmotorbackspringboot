package boutiqueProjet.org.Controller;

import boutiqueProjet.org.Entity.LfausseF;
import boutiqueProjet.org.Exception.ResourceNotFoundException;
import boutiqueProjet.org.Repository.LfaussefRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class LfausseFController {
    @Autowired
    LfaussefRepository repository;
    @GetMapping("/lcommfd")
    public List<LfausseF> getAllLcommsfd() {
        System.out.println("Get all Lcomms...");

        List<LfausseF> LfausseF = new ArrayList<>();
        repository.findAll().forEach(LfausseF::add);

        return LfausseF;
    }
    @GetMapping("/lcommfd/{numero}")
    public List<LfausseF> getLlivrBynumerofd(@PathVariable(value = "numero") int numero)
    {
        List<LfausseF> LfausseF = new ArrayList<>();
        return repository.findAllBynumero(numero);
    }
    /*@GetMapping("/lcommMag/{numero}")
    public List<LcommMag> getAllByNumero(@PathVariable(value = "numero") int numero) {
        System.out.println("Get all Lcomms...");

        List<LcommMag> LcommMag = new ArrayList<>();
        repository.findAllByNumero(numero).forEach(LcommMag::add);

        return LcommMag;
    }*/

    @GetMapping("/lcommsfd/{id}")
    public ResponseEntity<LfausseF> getLcommByIdfd(@PathVariable(value = "id") Long LcommId)
            throws ResourceNotFoundException {
        LfausseF LfausseF = repository.findById(LcommId)
                .orElseThrow(() -> new ResourceNotFoundException("Lcomm not found for this id :: " + LcommId));
        return ResponseEntity.ok().body(LfausseF);
    }

    @PostMapping("/lcommsfd")
    public  LfausseF createLcommfd( @RequestBody LfausseF LfausseF) {

        return repository.save(LfausseF);
    }

    @DeleteMapping("/lcommsfd/{id}")
    public Map<String, Boolean> deleteLcommfd(@PathVariable(value = "id") Long LcommId)
            throws ResourceNotFoundException {
        LfausseF Lcomm = repository.findById(LcommId)
                .orElseThrow(() -> new ResourceNotFoundException("Lcomm not found  id :: " + LcommId));

        repository.delete(Lcomm);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PutMapping("/lcommsfd/{id}")
    public ResponseEntity<LfausseF> updateLcommfd(@PathVariable("id") long id, @RequestBody LfausseF Lcomm) {
        System.out.println("Update Lcomm with ID = " + id + "...");

        Optional<LfausseF> LcommInfo = repository.findById(id);

        if (LcommInfo.isPresent()) {
            LfausseF lcomm = LcommInfo.get();
            lcomm.setLibart(Lcomm.getLibart());

            return new ResponseEntity<>(repository.save(Lcomm), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
