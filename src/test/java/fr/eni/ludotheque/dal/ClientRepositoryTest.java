package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.bo.Adresse;
import fr.eni.ludotheque.bo.Client;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class ClientRepositoryTest {

    @Autowired private ClientRepository clientRepo;

    @Test
    @DisplayName("Trouver id")
    public void testFindIdClient() {
        // Arrange
        String nom = "TOTO";
        String sciecq = "SCIECQ";
        Adresse adresse = new Adresse("6 impasse des grands bois", "79000", sciecq);
        Client client1 = new Client(nom, "Titi", "lilili@jiji.fr", "0633333333", adresse);

        // Test sauvegarde
        // Act
        clientRepo.save(client1);
        // Assert
        Integer idClient = client1.getId();
        assertNotNull(idClient);

        // Test récupération
        // Act
        Optional<Client> clientOpt = clientRepo.findById(idClient);
        // Assert
        assertTrue(clientOpt.isPresent());
        Client clientLu = clientOpt.get();
        assertEquals(nom, clientLu.getNom());
        Adresse adresseLue = clientLu.getAdresse();
        assertNotNull(adresseLue);
        assertEquals(sciecq, adresseLue.getVille());

        // Test maj
        // Arrange
        String nana = "Nana";
        clientLu.setNom(nana);
        String paris = "Paris";
        adresseLue.setVille(paris);
        // Act
        clientRepo.save(clientLu);
        // assertion
        Optional<Client> clientMajLuOpt = clientRepo.findById(idClient);

        assertTrue(clientMajLuOpt.isPresent());
        Client clientMajLu = clientMajLuOpt.get();
        assertEquals(nana, clientMajLu.getNom());
        Adresse adresseMajLue = clientMajLu.getAdresse();
        assertNotNull(adresseMajLue);
        assertEquals(paris, adresseMajLue.getVille());

        // Test delete
        // Act
        clientRepo.delete(client1);
        clientOpt = clientRepo.findById(idClient);
        // Assert
        assertTrue(clientOpt.isEmpty());
    }
}