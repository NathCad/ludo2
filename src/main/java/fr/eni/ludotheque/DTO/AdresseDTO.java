package fr.eni.ludotheque.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class AdresseDTO {

    AdresseDto adresse = new AdressetDTO(
            "rue1",
            "79001",
            "Niort1"
    );

    ClientDto client = new ClientDTO(
            "rue2",
            "79002",
            "Niort2"

    );

}
