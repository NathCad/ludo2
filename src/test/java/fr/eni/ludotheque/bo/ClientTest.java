package fr.eni.ludotheque.bo;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClientTest {

    @BeforeAll
    public static void initBeforeAll() {
        System.out.println("Dans initBeforeAll");
    }

    @BeforeEach
    public void initBeforeEach() {
        System.out.println("Dans initBeforeEach");
    }

    @AfterEach
    public void methodeAfterEach() {
        System.out.println("Dans methodeAfterEach");
    }

    @AfterAll
    public static void methodeAfterAll() {
        System.out.println("Dans methodeAfterAll");
    }

    @Test
    @DisplayName("Objet client")
    public void testCreationFicheClient() {

        // Préparation test
        Client client =
                new Client(
                        "TOTO", "Titi", "lilili@jiji.fr", "0633333333", new Adresse("test", "test", "test"));

        assertEquals("TOTO", client.getNom());
        assertEquals("Titi", client.getPrenom());
        assertEquals("lilili@jiji.fr", client.getEmail());
        assertEquals("0633333333", client.getNumeroTelephone());

        // Teste le changement de nom
        client.setNom("TITI");
        assertEquals("TITI", client.getNom());
    }
}