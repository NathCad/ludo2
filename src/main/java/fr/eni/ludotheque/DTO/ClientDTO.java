package fr.eni.ludotheque.DTO;

import lombok.NonNull;

public class ClientDTO {

    Integer id;
     @NonNull String nom;
     @NonNull String prenom;
    String email;
    String numeroTelephone;
}