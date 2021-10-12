package com.grofandris.api.Bank.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final UUID uuid = UUID.randomUUID();

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @NotBlank(message = "Account name is mandatory")
    private String accountName;

    @NotNull
    private double balance;

}
