package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.bo.Genre;
import fr.eni.ludotheque.bo.Jeu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
public class JeuRepositoryTest {

	@Autowired private JeuxRepository jeuxRepository;
	@Autowired private GenreRepository genreRepository;

	@Test
	@DisplayName("Test jeux")
	void jeuxRepositoryTest() {
		Genre genre = new Genre("Aventure");
		Genre genre2 = new Genre("Action");
		genreRepository.save(genre);
		genreRepository.save(genre2);

		Integer genreId = genre.getId();
		assertNotNull(genreId);

		Jeu jeu = new Jeu("Mario", "REF1", 12, 48, 2F, Set.of(genre, genre2));
		jeuxRepository.save(jeu);

		// Assert
		Integer id = jeu.getId();
		assertNotNull(id);
	}
}