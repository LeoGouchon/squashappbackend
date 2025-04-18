package com.leogouchon.squashapp.dto;

import com.leogouchon.squashapp.model.Players;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO {
    private Long id;
    private String email;
    private Players player;

    public UserResponseDTO(Long id, String email, Players player) {
        this.id = id;
        this.email = email;
        this.player = player;
    }
}
