package fr.eni.ludotheque.bll;


import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.dal.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService{

   private final ClientRepository clientRepository;


   public ClientServiceImpl(ClientRepository clientRepository){

       this.clientRepository = clientRepository;
   }

   @Override

    public void ajouterClient(Client client){
       clientRepository.save(client);

//       if (client.getNom() == null || client.getNom().isBlank()){
//           throw new IllegalAccessException("Nom obligatoire");
//       }
//
//       if (client.getAdresse()== null){
//           throw new IllegalAccessException("Adresse obligatoire");
//       }
//
//       return clientRepository.save(client);
   }


}
