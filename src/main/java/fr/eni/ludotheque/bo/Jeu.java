package fr.eni.ludotheque.bo;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Jeu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "no_jeu")
	Integer id;

	@Column(unique = true, nullable = false, length = 100)
	@NonNull
	String titre;

	@Column(unique = true, nullable = false, length = 100)
	@NonNull
	String reference;

	@Column(nullable = false)
	@NonNull
	Integer age_min;

	@Column(length = 100)
	String description;

	@Column(nullable = false)
	@NonNull
	Integer duree;

	@Column(nullable = false)
	@NonNull
	Float tarif_jour;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "jeux_genres",
			joinColumns = @JoinColumn(name = "no_jeu"),
			inverseJoinColumns = @JoinColumn(name = "no_genre"))
	@NonNull
	Set<Genre> genres;
}