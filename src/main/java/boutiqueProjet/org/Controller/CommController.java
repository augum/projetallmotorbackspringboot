package boutiqueProjet.org.Controller;

import boutiqueProjet.org.Entity.Article;
import boutiqueProjet.org.Entity.Comm;
import boutiqueProjet.org.Entity.Lcomm;
import boutiqueProjet.org.Exception.ResourceNotFoundException;
import boutiqueProjet.org.Repository.ArticleRepository;
import boutiqueProjet.org.Repository.CommRepository;
import boutiqueProjet.org.Repository.CompteurRepository;
import boutiqueProjet.org.Repository.LcommRepository;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")

public class CommController {
	@Autowired
	CommRepository repository;
	@Autowired
	LcommRepository repo;
	@Autowired
	ArticleRepository articleRepository;
	@Autowired
	CompteurRepository comptrepo;
	@Autowired
    ServletContext context;
	//methode pour recuperer la liste des toutes les commandes

	 @GetMapping("/comms")
	  public List<Comm> getAllComms() {
	    System.out.println("Get all Comms...");
	    List<Comm> Comms = new ArrayList<>();
	    repository.findAll().forEach(Comms::add);
	    return Comms;
	  }
	  //credit

	  @GetMapping("/commsc")
	  public List<Comm> getAllCommsCredit(@RequestParam(name = "mag") String mag) {
		  System.out.println("Get all Comms...");
		  List<Comm> Comms = new ArrayList<>();
		  repository.listedette(mag).forEach(Comms::add);
		  return Comms;
	  }
	@GetMapping("/commnum")
	public List<Comm> getAllCommsNum(@RequestParam(name = "numero") String numero) {
		System.out.println("Get all Comms...");
		List<Comm> Comms = new ArrayList<>();
		repository.listeNumero(numero).forEach(Comms::add);
		return Comms;
	}
	@GetMapping("/commdate")
	public List<Comm> getAllCommsDate(@RequestParam(name = "date") String date) {
		System.out.println("Get all Comms...");
		LocalDate dateo = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		List<Comm> Comms = new ArrayList<>();
		repository.listeDate(dateo).forEach(Comms::add);
		return Comms;
	}
	@GetMapping("/commnom")
	public List<Comm> getAllCommsNom(@RequestParam(name = "nom") String nom) {
		System.out.println("Get all Comms...");
		List<Comm> Comms = new ArrayList<>();
		repository.listeNom(nom).forEach(Comms::add);
		return Comms;
	}
    @GetMapping("/commscn")
    public List<Comm> getAllCommsCreditN(@RequestParam(name = "mag") String mag,@RequestParam(name = "client") String client) {
        System.out.println("Get all Comms...");
        List<Comm> Comms = new ArrayList<>();
        repository.listedetteN(mag,client).forEach(Comms::add);
        return Comms;
    }

	@GetMapping("/listeparclient")
	public List<Lcomm> listeclient(@RequestParam(name = "client") String client,@RequestParam(name = "date1") String date1oString,@RequestParam(name = "date2") String date2oString) {
		System.out.println("Get all Comms...");
		LocalDate dateo1 = LocalDate.parse(date1oString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate dateo2 = LocalDate.parse(date2oString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		List<Lcomm> Comms = new ArrayList<>();
		repository.listeparclient(client,dateo1,dateo2).forEach(Comms::add);
		return Comms;
	}

	  //retourne la somme des commandes d'une boutique pour un interval de temps

    @GetMapping("/sommetout")
    public double getForecastTotals(@RequestParam(name = "mag") String mag, @RequestParam(name = "date1") String date1oString,@RequestParam(name = "date2") String date2oString,@RequestParam(name = "modepayement") String modepayement ) {
        LocalDate dateo1 = LocalDate.parse(date1oString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate dateo2 = LocalDate.parse(date2oString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	     return repository.somme(mag,dateo1,dateo2,modepayement);
    }

    @GetMapping("/sommetoute")
    public double getForecastTotalse(@RequestParam(name = "local") String local, @RequestParam(name = "date1") String date1oString,@RequestParam(name = "date2") String date2oString,@RequestParam(name = "mag") String mag,@RequestParam(name = "mode") String mode ) {
        LocalDate dateo1 = LocalDate.parse(date1oString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate dateo2 = LocalDate.parse(date2oString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return repository.sommeSEd(local,dateo1,dateo2,mag,mode);
    }
    //calcule la somme des commandes pour une boutique a la date du jour selon le mode de paiement

    @GetMapping("/sommeun")
    public double getSomme(@RequestParam(name = "mag") String mag, @RequestParam(name = "date") String dateoString,@RequestParam(name = "modepayement") String modepayement ) {
        System.out.println("Get all somme un...");
        List<Comm> Comms = new ArrayList<>();
        LocalDate dateo = LocalDate.parse(dateoString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return repository.sommeS(mag,dateo,modepayement);
    }
    /********/
    @GetMapping("/sommeunU")
    public double getSomme(@RequestParam(name = "mag") String mag, @RequestParam(name = "date") String dateoString,@RequestParam(name = "modepayement") String modepayement,@RequestParam(name = "idutilisateur") String idutilisateur ) {
        System.out.println("Get all somme un...");
        List<Comm> Comms = new ArrayList<>();
        LocalDate dateo = LocalDate.parse(dateoString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return repository.sommeSC(mag,dateo,modepayement,idutilisateur);
    }

	@GetMapping("/sommeune")
	public double getSommeE(@RequestParam(name = "local") String local,@RequestParam(name = "date") String dateoString,@RequestParam(name = "mag") String mag,@RequestParam(name = "mode") String mode) {
		System.out.println("Get all somme un...");
		LocalDate dateo = LocalDate.parse(dateoString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		List<Lcomm> Comms = new ArrayList<>();
		return repository.sommeSE(local,dateo,mag,mode);
	}

    //liste des commandes pour une boutique a la date du jour

	@GetMapping("/commsd")
	public List<Comm> getAllCommsT(@RequestParam(name = "mag") String mag, @RequestParam(name = "date") String dateoString,@RequestParam(name = "modepayement") String modepayement ) {
		System.out.println("Get all Comms...");
		List<Comm> Comms = new ArrayList<>();
        LocalDate dateo = LocalDate.parse(dateoString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		repository.chercherCommandparMagasinetDate(mag,dateo,modepayement).forEach(Comms::add);
		return Comms;
	}
	@GetMapping("/commsdu")
	public List<Comm> getAllCommsTu(@RequestParam(name = "mag") String mag, @RequestParam(name = "date") String dateoString,@RequestParam(name = "modepayement") String modepayement,@RequestParam(name = "idutilisateur") String idutilisateur ) {
		System.out.println("Get all Comms...");
		List<Comm> Comms = new ArrayList<>();
		LocalDate dateo = LocalDate.parse(dateoString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		repository.chercherCommandparMagasinetDateU(mag,dateo,modepayement,idutilisateur).forEach(Comms::add);
		return Comms;
	}
	//liste des commandes selon les boutiques pour une periode

	@GetMapping("/commsdI")
	public List<Comm> getAllCommsTI(@RequestParam(name = "mag") String mag, @RequestParam(name = "date1") String date1oString,@RequestParam(name = "date2") String date2oString,@RequestParam(name = "modepayement") String modepayement ) {
		System.out.println("Get all Comms by interval date...");
		List<Comm> Comms = new ArrayList<>();
		LocalDate dateo1 = LocalDate.parse(date1oString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate dateo2 = LocalDate.parse(date2oString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		repository.chercherCommandparMagasinetDateIn(mag,dateo1,dateo2,modepayement).forEach(Comms::add);
		return Comms;
	}
	//pour la synchro
	@GetMapping("/commssynchro")
	public List<Comm> getAllCommsSynchro(@RequestParam(name = "mag") String mag, @RequestParam(name = "date1") String date1oString,@RequestParam(name = "date2") String date2oString ) {
		System.out.println("Get all Comms by interval date...");
		List<Comm> Comms = new ArrayList<>();
		LocalDate dateo1 = LocalDate.parse(date1oString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate dateo2 = LocalDate.parse(date2oString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		repository.chercherCommandparMagasinetDateInSycro(mag,dateo1,dateo2).forEach(Comms::add);
		return Comms;
	}
	@PostMapping("/commsynchro")
	public List<Comm> save(@RequestBody List<Comm> comms) throws JsonParseException, JsonMappingException , Exception
	{

	   for(Comm sychro: comms){
          Comm comm = new Comm();

		   comm.setAnnee(sychro.getAnnee());
		   comm.setNumero(sychro.getNumero());
		   comm.setDate(sychro.getDate());
		   comm.setCode_client(sychro.getCode_client());
		   comm.setLibelle(sychro.getLibelle());
		   comm.setLib_client(sychro.getLib_client());
		   comm.setAvance(sychro.getAvance());
		   comm.setTotht(sychro.getTotht());
		   comm.setTottva(sychro.getTottva());
		   comm.setTotttc(sychro.getTotttc());
		   comm.setMt(sychro.getMt());
		   comm.setReste(sychro.getReste());
		   comm.setModepayement(sychro.getModepayement());
		   comm.setMag(sychro.getMag());
		   comm.setIdUtilisateur(sychro.getIdUtilisateur());

		   Optional<Comm> oneComm = repository.findByNumero(sychro.getNumero());
		   if(oneComm.isPresent()){
			   System.out.println("la commande existe deja...");
		   }else{
			   repository.save(comm);
		   }


	   }


		return null;
	}
  // Fin sychro
	@GetMapping("/sommeuneI")
	public double getSommeEi(@RequestParam(name = "local") String local,@RequestParam(name = "date1") String date1oString,@RequestParam(name = "date2") String date2oString,@RequestParam(name = "mag") String mag,@RequestParam(name = "mode") String mode) {
		System.out.println("Get all somme un...");
		LocalDate dateo1 = LocalDate.parse(date1oString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate dateo2 = LocalDate.parse(date2oString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		List<Lcomm> Comms = new ArrayList<>();
		return repository.sommeSEd(local,dateo1,dateo2,mag,mode);
	}

	@GetMapping("/sommeparclient")
	public double getSommeEi1(@RequestParam(name = "client") String client,@RequestParam(name = "date1") String date1oString,@RequestParam(name = "date2") String date2oString) {
		System.out.println("Get all somme un...");
		LocalDate dateo1 = LocalDate.parse(date1oString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate dateo2 = LocalDate.parse(date2oString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		List<Lcomm> Comms = new ArrayList<>();
		return repository.sommeparclient(client,dateo1,dateo2);
	}

	@GetMapping("/comms/{id}")
	public ResponseEntity<Comm> getCommById(@PathVariable(value = "id") Long CommId)
			throws ResourceNotFoundException {
		Comm Comm = repository.findById(CommId)
		
				.orElseThrow(() -> new ResourceNotFoundException("Comm not found for this id :: " + CommId));
		return ResponseEntity.ok().body(Comm);
	}

	@PostMapping("/comms")
	public ResponseEntity<Comm> createComm( @RequestBody Comm Comm)
			throws JsonParseException, JsonMappingException , Exception{
		  Comm.setReste(Comm.getTotttc() - Comm.getMt()) ;
		repository.save(Comm);
		List<Lcomm> lcomms = Comm.getLcomms();
		for (Lcomm lc : lcomms) {
			lc.setNumero(Comm.getNumero());
			lc.setDate(LocalDate.now());
			lc.setModePayement(Comm.getModepayement());
			lc.setClient(Comm.getLib_client());
			Optional<Article> ArticleInfo = articleRepository.findByCode(lc.getCode_article());
			if (ArticleInfo.isPresent()) {

				Article Art = ArticleInfo.get();
				Art.setStock(Art.getStock() - lc.getQte());
                if(Art.getStock()<= Art.getFodec()){
                    Art.setDispo(false);
                }
				Art = articleRepository.save(Art);
                System.out.println(Art.isDispo());
			}
			repo.save(lc);
		}
     /*Optional<Compteur> CompteurInfo = comptrepo.findByAnnee(Comm.getAnnee());
     	if (CompteurInfo.isPresent()) {
	    	Compteur compteur = CompteurInfo.get();
	           compteur.setNumcomm(compteur.getNumcomm()+1);
	           compteur =   comptrepo.save(compteur);
     	}*/
		 return new ResponseEntity<>(HttpStatus.OK);
	}


	@DeleteMapping("/comms/{id}")
	public ResponseEntity<Comm> deleteComm(@PathVariable(value = "id") Long CommId)
	{
		Optional<Comm> CommInfo = repository.findById(CommId);
	  if (CommInfo.isPresent()) {
		  System.out.println("Commande 11");
		  Comm Comm = CommInfo.get();
		  repo.deleteByNumero(Comm.getNumero());
		  repository.delete(Comm);
		  return new ResponseEntity<>(HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

	  @DeleteMapping("/comms/delete")
	  public ResponseEntity<String> deleteAllComms() {
	    System.out.println("Delete All Comms...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All Comms have been deleted!", HttpStatus.OK);
	  }
	 
	 /* @PutMapping("/comms/{id}")
	  public ResponseEntity<Comm> updateComm(@PathVariable("id") long id, @RequestBody Comm Comm) {
	    System.out.println("Update Comm with ID = " + id + "...");
	    Optional<Comm> CommInfo = repository.findById(id);
	    if (CommInfo.isPresent()) {
	    	Comm comm = CommInfo.get();
	    	comm.setLibelle(Comm.getLibelle());
	      return new ResponseEntity<>(repository.save(comm), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }*/

	 @PutMapping("/comms/{id}")
	public ResponseEntity<Comm> updateArticle(@PathVariable("id") long id, @RequestBody Comm Comm) {
		System.out.println("Commande is update with ID = " + id + "...");
		Optional<Comm> CommInfo = repository.findById(id);
		if (CommInfo.isPresent()) {
			Comm comm = CommInfo.get();
			comm.setMt(Comm.getMt());
			comm.setReste(Comm.getReste()) ;
			return new ResponseEntity<>(repository.save(Comm), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	  
	
	 
}
