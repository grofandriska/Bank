package com.grofandris.api.Bank.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final UUID uuid = UUID.randomUUID();

    @OneToMany(mappedBy = "user")
    private List<Account> userAccounts;

    @NotBlank
    private String userName;

    @Email
    private String email;

}
