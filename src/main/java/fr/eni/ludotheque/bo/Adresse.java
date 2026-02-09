package fr.eni.ludotheque.bo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "Adresses")
public class Adresse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer noAdresse;

	@Column(nullable = false)
	@NonNull
	private String rue;

	@Column(nullable = false, length = 5, unique = true)
	@NonNull
	private String codePostal;

	@Column(nullable = false)
	@NonNull
	private String ville;
}