package com.leogouchon.hubscore.authenticate_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvitationResponseDTO {
    String token;

    public InvitationResponseDTO(String token) {
        this.token = token;
    }
}
