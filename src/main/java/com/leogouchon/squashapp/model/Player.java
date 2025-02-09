package com.leogouchon.squashapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;
    private String lastname;

    public Player () {
    }

    public Player(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

}
