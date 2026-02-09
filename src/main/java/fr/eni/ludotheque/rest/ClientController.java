package fr.eni.ludotheque.rest;

import fr.eni.ludotheque.bll.ClientService;
import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.dal.ClientRepository;
import fr.eni.ludotheque.exceptions.ClientNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;




@RestController
@RequestMapping("/api/clients")
public class ClientController {
    private final ClientRepository clientRepository;
    private final ClientService clientService;

    public ClientController(ClientRepository clientRepository, ClientService clientService) {
        this.clientRepository = clientRepository;
        this.clientService = clientService;
    }

    @GetMapping("/api/clients")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Client> getAllClients(){
        List clients = clientService.getAllClients();
        return clients;
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Client> getClientById(@PathVariable Integer id){
//        return clientRepository.findById(id)
//        .map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }


    //Ajout client
    @PostMapping
    public Client ajouterClient(@RequestBody Client client) {
        return clientRepository.save(client);
    }

    //Delete client
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Integer id) {

        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();

    }

    //modifier complètement un client
    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Integer id, @Valid @RequestBody Client client){
      try{
      Client clientUpdate = ClientService.clientUpdate(id, client);
      return ResponseEntity.ok(clientUpdate);
      }catch (ClientNotFoundException clientNotFound){
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
      }

    }


}



