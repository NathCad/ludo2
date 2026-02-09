package fr.eni.ludotheque.exceptions;


import lombok.Data;
import lombok.NonNull;

@Data
public class ClientNotFoundException extends RuntimeException {

    @NonNull
    private Integer idClient;

    public ClientNotFoundException(Integer idClient) {
        super("Le client " + idClient + " n'a pas été trouvé");
    }
}
