package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Adresse;
import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.dal.ClientRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ClientServiceTest {

    private record AdresseEtClient(Adresse adresse, Client client) {}

    // faire en sorte que spring créé une instance du service et du repository
    @Autowired ClientService clientService;
    @Autowired ClientRepository clientRepository;

    private static AdresseEtClient getAdresseEtClient() {
        Adresse adresse = new Adresse("66 avenue des martyre", "66666", "DevilLand");
        Client client = new Client("Belzebut", "Lucifer", "666@enfer.com", "0666666666", adresse);
        return new AdresseEtClient(adresse, client);
    }

    @Test
    @DisplayName("ajouter un client et son adresse")
    // permet de ne pas sauvegarder les changements faits dans le test au niveau de la base
    @Transactional
    void ajoutClientEtSonAdresse() {
        // Arrange
        AdresseEtClient adresseEtClient = getAdresseEtClient();

        // Act
        Client clientEnregistre = clientService.enregistrerNouveauClient(adresseEtClient.client());

        // Assert
        Client clientLu = clientRepository.getReferenceById(clientEnregistre.getId());
        assertNotNull(clientLu);
    }

    @Test
    @DisplayName("trouver des client dont le nom commence par")
    @Transactional
    void trouverClientDontLeNomCommencePar() {
        // Arrange
        AdresseEtClient adresseEtClient = getAdresseEtClient();
        clientRepository.save(adresseEtClient.client());

        // Act
        Set<Client> clientsTrouves = clientService.trouverClientsDontLeNomCommencePar("Bel");

        // Assertion
        assertEquals(1, clientsTrouves.size());
    }

    @Test
    @DisplayName("modifier un client")
    @Transactional
    void modifierUnClient() {
        // Arrange
        // sauvegarder un client en base
        AdresseEtClient adresseEtClient = getAdresseEtClient();
        clientRepository.save(adresseEtClient.client());
        // recuperer les ids suite à la sauvegarde
        Integer clientId = adresseEtClient.client().getId();
        Integer noAdresse = adresseEtClient.adresse().getNoAdresse();

        // Act
        // creer une nouvelle instance de client avec le même id que celui créé, mais des infos
        // différentes
        Adresse adresseMiseAJour = new Adresse(noAdresse, "77 rue du père", "77777", "Eden");
        Client clientMiseAJour =
                new Client(clientId, "Christ", "Demarde", "777@cieux.com", "0777777777", adresseMiseAJour);

        // utiliser le service pour mettre à jour le client
        clientService.mettreAJourClient(clientMiseAJour);

        // Assertion
        // recuperer le cliend evec le repository et l'id
        Client clientLu = clientRepository.getReferenceById(clientId);
        // verifier que le iinfos du client ont bien été mises à jour
        assertEquals("Christ", clientLu.getNom());
        assertEquals(noAdresse, clientLu.getAdresse().getNoAdresse());
        assertEquals("77 rue du père", clientLu.getAdresse().getRue());
    }

    @Test
    @DisplayName("mise à jour client non trouvé")
    @Transactional
    void miseAJourClientNonTrouve() {

        // Arrange
        AdresseEtClient adresseEtClient = getAdresseEtClient();
        adresseEtClient.client().setId(1);
        adresseEtClient.adresse().setNoAdresse(5);

        // Act && assert
        assertThrows(
                RuntimeException.class, () -> clientService.mettreAJourClient(adresseEtClient.client()));
    }

    @Test
    @DisplayName("modifier uniquement l'adresse d'un client")
    @Transactional
    void modifierAdresse() {

        // Arrange
        AdresseEtClient adresseEtClient = getAdresseEtClient();
        clientRepository.save(adresseEtClient.client());
        Integer noAdresse = adresseEtClient.client().getAdresse().getNoAdresse();
        Integer idClient = adresseEtClient.client.getId();
        // Act
        Adresse adresseMiseAJour = new Adresse(noAdresse, "rue du nouveau", "79000", "Niort");
        Client clientMisAJour = clientService.mettreAJourAdresse(idClient, adresseMiseAJour);
        // Assertion
        Client clientLu = clientRepository.getReferenceById(idClient);
        assertEquals("rue du nouveau", clientLu.getAdresse().getRue());
    }
}