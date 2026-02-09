package fr.eni.ludotheque.bo;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Genre {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "no_genre")
	Integer id;

	@Column(nullable = false, length = 100)
	@NonNull
	String libelle;

	@ManyToMany(mappedBy = "genres")
	Set<Jeu> jeux;
}