package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Adresse;
import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.dal.ClientRepository;
import fr.eni.ludotheque.exceptions.ClientNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

   private final ClientRepository clientRepository;

   public ClientServiceImpl(ClientRepository clientRepository) {
      this.clientRepository = clientRepository;
   }

   @Override
   public Client clientUpdate(Integer id, Client client) {
      return null;
   }

   @Override
   public Client enregistrerNouveauClient(Client client) {
      return clientRepository.save(client);
   }

   @Override
   public List<Client> getNouveauClient() {
      return List.of();
   }

   @Override
   public Set<Client> trouverClientsDontLeNomCommencePar(String search) {
      return clientRepository.findByNomStartingWith(search);
   }

   @Override
   public Client mettreAJourClient(Client clientMiseAJour) {
      return clientRepository.save(clientMiseAJour);
   }

   @Override
   public Client mettreAJourAdresse(Integer idClient, Adresse adresseMiseAJour) {
      Client client =
              clientRepository
                      .findById(idClient)
                      .orElseThrow(() -> new RuntimeException("Le client n'existe pas"));

      Adresse adresseExistante = client.getAdresse();
      adresseExistante.setRue(adresseMiseAJour.getRue());
      adresseExistante.setCodePostal(adresseMiseAJour.getCodePostal());
      adresseExistante.setVille(adresseMiseAJour.getVille());
      return client;
   }

   @Override
   public void deleteClient(Integer id) {

      if(!clientRepository.existsById(id)){
         throw new ClientNotFoundException(id);
      }
      clientRepository.deleteById(id);
   }

   @Override
   public List getAllClients() {
      return List.of();
   }

   @Override
   public Client updateClient(Integer id, Client client) {
      return clientRepository.findById(id)
              .map(existingClient -> {
                 existingClient.setNom(client.getNom());
                 existingClient.setPrenom(client.getPrenom());
                 existingClient.setEmail(client.getEmail());
                 existingClient.setAdresse(client.getAdresse());
                 return clientRepository.save(existingClient);
              })
              .orElseThrow(() -> new ClientNotFoundException(id));
   }
}