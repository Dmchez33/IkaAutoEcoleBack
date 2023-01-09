package com.ikaautoecole.spring.projet.DTO.request;

import lombok.Data;

import java.util.Set;

import javax.validation.constraints.*;

@Data
public class SuperAdminRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

}
