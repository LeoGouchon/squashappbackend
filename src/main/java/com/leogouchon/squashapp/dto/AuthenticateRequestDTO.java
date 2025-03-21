package com.leogouchon.squashapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticateRequestDTO {
    private String username;
    private String password;
}
