package boutiqueProjet.org.Controller;


import boutiqueProjet.org.Entity.Lcomm;
import boutiqueProjet.org.Exception.ResourceNotFoundException;
import boutiqueProjet.org.Repository.LcommRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;



@RestController
@RequestMapping("/api")
public class LcommController {
	@Autowired
	LcommRepository repository;

	 @GetMapping("/lcomms")
	  public List<Lcomm> getAllLcomms() {
	    System.out.println("Get all Lcomms...");
	 
	    List<Lcomm> Lcomms = new ArrayList<>();
	    repository.findAll().forEach(Lcomms::add);

	    return Lcomms;
	  }

	@GetMapping("/lcom/{numero}")
	public List<Lcomm> getLlivrBynumero(@PathVariable(value = "numero") String numero)
	{
		List<Lcomm> Lcomm = new ArrayList<>();
		return repository.findAllBynumero(numero);
	}
	@GetMapping("/lcomc/{client}")
	public List<Lcomm> getLlivrByclient(@PathVariable(value = "client") String client)
	{
		List<Lcomm> Lcomm = new ArrayList<>();
		return repository.findAllByclient(client);
	}

	 @GetMapping("/lcomms/{id}")
	  public List<Lcomm> getAllByNumero(@PathVariable(value = "id") String numero) {
	    System.out.println("Get all Lcomms...");
	 
	    List<Lcomm> Lcomms = new ArrayList<>();
	    repository.findAllByNumero(numero).forEach(Lcomms::add);
	 
	    return Lcomms;
	  }

	@GetMapping("/lcommss/{id}")
	public ResponseEntity<Lcomm> getLcommById(@PathVariable(value = "id") Long LcommId)
			throws ResourceNotFoundException {
		Lcomm Lcomm = repository.findById(LcommId)
				.orElseThrow(() -> new ResourceNotFoundException("Lcomm not found for this id :: " + LcommId));
		return ResponseEntity.ok().body(Lcomm);
	}
	@GetMapping("/lcommArt")
	public List<Lcomm> getAllCommsT() {
		System.out.println("Get all Comms...");
		List<Lcomm> Comms = new ArrayList<>();
		/*LocalDate dateo = LocalDate.parse(dateoString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate dateo2 = LocalDate.parse(dateoString2, DateTimeFormatter.ofPattern("yyyy-MM-dd"));*/
		repository.listeArt();
		return Comms;
	}
	@GetMapping("/lcommDate")
	public List<Map<String, Object>> getAllCommsT(@RequestParam(name = "date1") String dateoString1,
												  @RequestParam(name = "date2") String dateoString2) {
		LocalDate dateo = LocalDate.parse(dateoString1, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate dateo2 = LocalDate.parse(dateoString2, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		List<Object[]> results = repository.chercherCommD(dateo, dateo2);
		List<Map<String, Object>> response = new ArrayList<>();

		for (Object[] row : results) {
			Map<String, Object> map = new HashMap<>();
			map.put("libart", row[0]);
			map.put("total", row[1]);
			response.add(map);
		}

		return response;
	}
	
	@GetMapping("/detailvente")
	public List<Lcomm> getAllCommdetail(@RequestParam(name = "date1") String dateoString1,@RequestParam(name = "date2") String dateoString2 ) {
		System.out.println("Get all Comms...");
		List<Lcomm> Comms = new ArrayList<>();
		LocalDate dateo = LocalDate.parse(dateoString1, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate dateo2 = LocalDate.parse(dateoString2, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		repository.chercherCommDetail(dateo,dateo2).forEach(Comms::add);
		return Comms;
	}
	@PostMapping("/lcomms")
	public  Lcomm createLcomm( @RequestBody Lcomm Lcomm) {
		
		return repository.save(Lcomm);
		
		 
	}


	@DeleteMapping("/lcomms/{id}")
	public Map<String, Boolean> deleteLcomm(@PathVariable(value = "id") Long LcommId)
			throws ResourceNotFoundException {
		Lcomm Lcomm = repository.findById(LcommId)
				.orElseThrow(() -> new ResourceNotFoundException("Lcomm not found  id :: " + LcommId));

		repository.delete(Lcomm);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}


	  @PutMapping("/lcomms/{id}")
	  public ResponseEntity<Lcomm> updateLcomm(@PathVariable("id") long id, @RequestBody Lcomm Lcomm) {
	    System.out.println("Update Lcomm with ID = " + id + "...");
	 
	    Optional<Lcomm> LcommInfo = repository.findById(id);
	 
	    if (LcommInfo.isPresent()) {
	    	Lcomm lcomm = LcommInfo.get();
	    	lcomm.setLibart(Lcomm.getLibart());
	      return new ResponseEntity<>(repository.save(Lcomm), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

	@GetMapping("/lcommdetail")
	public List<Lcomm> getAllCommsTI(@RequestParam(name = "mag") String mag, @RequestParam(name = "date1") String date1oString,@RequestParam(name = "date2") String date2oString) {
		System.out.println("Get all Comms by interval date...");
		List<Lcomm> Comms = new ArrayList<>();
		LocalDate dateo1 = LocalDate.parse(date1oString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate dateo2 = LocalDate.parse(date2oString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		repository.chercherLCommandparMagasinetDateIn(mag,dateo1,dateo2).forEach(Comms::add);
		return Comms;
	}

}
