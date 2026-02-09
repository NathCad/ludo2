package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Adresse;
import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.dal.ClientRepository;
import jakarta.transaction.Transactional;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

   private final ClientRepository clientRepository;

   public ClientServiceImpl(ClientRepository clientRepository) {
      this.clientRepository = clientRepository;
   }

   @Override
   public Client enregistrerNouveauClient(Client client) {
      return clientRepository.save(client);
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
}