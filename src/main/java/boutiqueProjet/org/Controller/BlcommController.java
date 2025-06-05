package boutiqueProjet.org.Controller;

import boutiqueProjet.org.Entity.BLcomm;
import boutiqueProjet.org.Exception.ResourceNotFoundException;
import boutiqueProjet.org.Repository.BlcommRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/api")
public class BlcommController {
    @Autowired
    BlcommRepository repository;

    @GetMapping("/blcomms")
    public List<BLcomm> getAllLcomms1() {
        System.out.println("Get all Lcomms...");

        List<BLcomm> Lcomms = new ArrayList<>();
        repository.findAll().forEach(Lcomms::add);

        return Lcomms;
    }

    @GetMapping("/blcom/{numero}")
    public List<BLcomm> getLlivrBynumero1(@PathVariable(value = "numero") String numero)
    {
        List<BLcomm> Lcomm = new ArrayList<>();
        return repository.findAllBynumero(numero);
    }

    @GetMapping("/blcomms/{id}")
    public List<BLcomm> getAllByNumero1(@PathVariable(value = "id") String numero) {
        System.out.println("Get all Lcomms...");
        List<BLcomm> Lcomms = new ArrayList<>();
        repository.findAllBynumero(numero);

        return Lcomms;
    }

    @GetMapping("/blcommss/{id}")
    public ResponseEntity<BLcomm> getLcommById1(@PathVariable(value = "id") Long LcommId)
            throws ResourceNotFoundException {
        BLcomm Lcomm = repository.findById(LcommId)
                .orElseThrow(() -> new ResourceNotFoundException("Lcomm not found for this id :: " + LcommId));
        return ResponseEntity.ok().body(Lcomm);
    }
    @PostMapping("/blcomms")
    public  BLcomm createLcomm1( @RequestBody BLcomm Lcomm) {

        return repository.save(Lcomm);


    }

    @DeleteMapping("/blcomms/{id}")
    public Map<String, Boolean> deleteLcomm1(@PathVariable(value = "id") Long LcommId)
            throws ResourceNotFoundException {
        BLcomm Lcomm = repository.findById(LcommId)
                .orElseThrow(() -> new ResourceNotFoundException("Lcomm not found  id :: " + LcommId));

        repository.delete(Lcomm);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


    @PutMapping("/blcomms/{id}")
    public ResponseEntity<BLcomm> updateLcomm1(@PathVariable("id") long id, @RequestBody BLcomm Lcomm) {
        System.out.println("Update Lcomm with ID = " + id + "...");

        Optional<BLcomm> LcommInfo = repository.findById(id);

        if (LcommInfo.isPresent()) {
            BLcomm lcomm = LcommInfo.get();
            lcomm.setLibart(Lcomm.getLibart());
            return new ResponseEntity<>(repository.save(Lcomm), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
