package com.leogouchon.squashapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    private String token;
    @JsonIgnore
    private Boolean isAdmin = false;
    @OneToOne
    @JoinColumn(name = "players_id", unique = true)
    private Player player;
}
