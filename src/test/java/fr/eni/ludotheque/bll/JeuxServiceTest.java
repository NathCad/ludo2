package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Genre;
import fr.eni.ludotheque.bo.Jeu;
import fr.eni.ludotheque.dal.GenreRepository;
import fr.eni.ludotheque.dal.JeuxRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
public class JeuxServiceTest {

    @Autowired JeuService jeuService;
    @Autowired
    JeuxRepository jeuxRepository;
    @Autowired GenreRepository genreRepository;

    @Test
    @DisplayName("ajout d'un jeu et des liens vers les genres")
    @Transactional
    void ajoutJeuEtGenre() {
        // Arrange
        Genre aventure = new Genre("Aventure");
        genreRepository.save(aventure);

        // Act
        Set<Genre> genresPresent = Set.of(aventure);
        Jeu jeu = new Jeu("Mario", "1234", 10, 48, 2f, genresPresent);
        Jeu jeuAjouter = jeuService.ajouterJeu(jeu);

        // Assertion
        Jeu jeuAjoute = jeuxRepository.getReferenceById(jeu.getId());
        assertNotNull(jeuAjoute);
    }
}