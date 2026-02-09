package fr.eni.ludotheque.rest;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse <t>{
    private boolean success;
    private String message;

    private t date;
}
