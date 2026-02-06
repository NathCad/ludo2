package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Adresse;
import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.dal.ClientRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ClientServiceTest {

    @Autowired
    private ClientService clientService;

    @MockitoBean
    private ClientRepository clientRepository;

    @Test
    @DisplayName("trouver les clients dont le nom commence par")
    public void testTrouverClientsParNomQuiCommencePar(){
        //Arrange
String nom= "Sti";
        Adresse adresse= new Adresse(("rue des 1", 79001, "Niotr1"))
        Client clientATrouver = new Client("Stiller", "Ben", "e1", adresse);
        clientATrouver.setNoTelephone("tel1");
        Adresse adresse2 = new Adresse("rue2", "79002", "Niort2");
        Client clientATrouver2 = new Client("Stillo", "Ben1", "e2", adresse2);
        clientATrouver.setNoTelephone("tel2");

        List<Client> listeClients = new ArrayList<>();
        listeClients.add(clientATrouver);
        listeClients.add(clientATrouver2);
        when(clientRepository.findByNomStartsWith(nom)).thenReturn(listeClients);

        //Act

        List<Client> clients = clientService.trouverClientsParNom(nom);

        //Assert
        assertThat(clients).hasSize(2);

    }

}
