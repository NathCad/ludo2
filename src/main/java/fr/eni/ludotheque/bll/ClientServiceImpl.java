package fr.eni.ludotheque.bll;


import fr.eni.ludotheque.DTO.ClientDTO;
import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.dal.ClientRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ClientServiceImpl implements ClientService{
    @NonNull
   private ClientRepository clientRepository;


   @Override
    public  Client ajouterClient(ClientDTO clientDTO) {
       Client client = new Client();
       BeanUtils.copyProperties(clientDTO, client);
       Client newClient = null;

//       try {
//           newClient = clientRepository.save(client);
//       }catch(DataIntegrityViolationException ex) {
//           throw new EmailClientAlreadyExistException();
//       }

       return clientRepository.save(client);
   }



   @Override
   public List<Client> trouverClientsParNom (String nom){

      return clientRepository.findByNomStartsWith(nom);
   }


}
