package boutiqueProjet.org.Controller;

import boutiqueProjet.org.Entity.Client;
import boutiqueProjet.org.Exception.ResourceNotFoundException;
import boutiqueProjet.org.Repository.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.*;


@RestController
@RequestMapping("/api")
public class ClientController {
	@Autowired
	ClientRepository repository;
	@CrossOrigin("*")
	 @GetMapping("/clients")
	  public List<Client> getAllClients() {
	    System.out.println("Get all Clients...");
	 
	    List<Client> Clients = new ArrayList<>();
	    repository.findAll().forEach(Clients::add);
	 
	    return Clients;
	  }

	@GetMapping("/clients/{id}")
	public ResponseEntity<Client> getClientById(@PathVariable(value = "id") Long ClientId)
			throws ResourceNotFoundException {
		Client Client = repository.findById(ClientId)
				.orElseThrow(() -> new ResourceNotFoundException("Client not found for this id :: " + ClientId));
		return ResponseEntity.ok().body(Client);
	}

	@PostMapping("/clients")
	public Client createClient( @RequestBody Client Client) {
		return repository.save(Client);
	}


	@DeleteMapping("/clients/{id}")
	public Map<String, Boolean> deleteClient(@PathVariable(value = "id") Long ClientId)
			throws ResourceNotFoundException {
		Client Client = repository.findById(ClientId)
				.orElseThrow(() -> new ResourceNotFoundException("Client not found  id :: " + ClientId));

		repository.delete(Client);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}


	  @DeleteMapping("/clients/delete")
	  public ResponseEntity<String> deleteAllClients() {
	    System.out.println("Delete All Clients...");
	 
	    repository.deleteAll();
	 
	    return new ResponseEntity<>("All Clients have been deleted!", HttpStatus.OK);
	  }


	  @PutMapping("/clients/{id}")
	  public ResponseEntity<Client> updateClient(@PathVariable("id") long id, @RequestBody Client Client) {
	    System.out.println("Update Client with ID = " + id + "...");
	 
	    Optional<Client> ClientInfo = repository.findById(id);
	 
	    if (ClientInfo.isPresent()) {
	    	Client client = ClientInfo.get();
	          
	           client.setLibelle(Client.getLibelle());
	           client.setAdresse(Client.getAdresse());
	         
	           client.setEmail(Client.getEmail());
	           client.setLogin(Client.getLogin());
	          
	           client.setPwd(Client.getPwd());
	           
	           client.setTel(Client.getTel());
	          
	          
	      return new ResponseEntity<>(repository.save(Client), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

}
