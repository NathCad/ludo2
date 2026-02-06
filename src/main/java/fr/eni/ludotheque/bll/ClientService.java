package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.DTO.ClientDTO;
import fr.eni.ludotheque.bo.Client;

import java.util.List;

public interface ClientService {

   public Client ajouterClient(ClientDTO clientDTO);

    public List<Client> trouverClientsParNom(String nom);
}
