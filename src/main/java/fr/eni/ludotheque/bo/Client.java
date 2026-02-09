package fr.eni.ludotheque.bo;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Clients")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;

	@Column(nullable = false, length = 100)
	@NonNull
	String nom;

	@Column(nullable = false, length = 100)
	@NonNull
	String prenom;

	@Column(unique = true)
	@NonNull
	String email;

	@Column(name = "numero_telephone", length = 20)
	@NonNull
	String numeroTelephone;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "adresse", nullable = false)
	@NonNull
	Adresse adresse;
}