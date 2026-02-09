package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Adresse;
import fr.eni.ludotheque.bo.Client;
import java.util.Set;

public interface ClientService {
    Client enregistrerNouveauClient(Client client);

    Set<Client> trouverClientsDontLeNomCommencePar(String bel);

    Client mettreAJourClient(Client clientMiseAJour);

    Client mettreAJourAdresse(Integer idClient, Adresse adresseMiseAJour);
}
