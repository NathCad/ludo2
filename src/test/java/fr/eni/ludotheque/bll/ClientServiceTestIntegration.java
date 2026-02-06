package fr.eni.ludotheque.bll;


import fr.eni.ludotheque.DTO.ClientDTO;
import fr.eni.ludotheque.bo.Client;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ClientServiceTestIntegration {

    @Autowired
    private ClientService clientService;

    @Test
    @Transactional
    public void testTrouverClientsDontLeNomCommencePar() {
        //Arrange
        String nom = "Sti";
        ClientDTO client= new ClientDTO("Stiller", "Ben", "e1",  "tel1","e1",  "79001", "Niort1");
        ClientDTO client2 = new ClientDTO("Stillo", "Ben1", "e2", "tel2", "rue 2", "79002", "Niort2");
        clientService.ajouterClient(client);
        clientService.ajouterClient(client2);

        //Act
        List<Client> clients = clientService.trouverClientsParNom(nom);

        //Assert
        assertThat(clients).hasSize(2);
    }
}
