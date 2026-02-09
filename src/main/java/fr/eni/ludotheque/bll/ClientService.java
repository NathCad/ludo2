package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Adresse;
import fr.eni.ludotheque.bo.Client;

import java.util.List;
import java.util.Set;

public interface ClientService {
   Static Client clientUpdate(Integer id, Client client);

    Client enregistrerNouveauClient(Client client);

    List<Client> getNouveauClient();

   Set<Client> trouverClientsDontLeNomCommencePar(String bel);

    Client mettreAJourClient(Client clientMiseAJour);

    Client mettreAJourAdresse(Integer idClient, Adresse adresseMiseAJour);

    void deleteClient(Integer id);

    List getAllClients();

    Client updateClient(Integer id, Client client);
}
