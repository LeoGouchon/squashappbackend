package com.leogouchon.squashapp.service;

import com.leogouchon.squashapp.model.Players;
import com.leogouchon.squashapp.repository.PlayerRepository;
import com.leogouchon.squashapp.service.interfaces.IPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService implements IPlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(
            PlayerRepository playerRepository
    ) {
        this.playerRepository = playerRepository;
    }

    public Page<Players> getPlayers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return playerRepository.findAll(pageable);
    }

    public Optional<Players> getPlayer(Long id) {
        return playerRepository.findById(id);
    }

    public Players createPlayer(Players player) throws RuntimeException {
        if (player.getFirstname() == null || player.getLastname() == null) {
            throw new RuntimeException("Firstname and lastname must not be null");
        }
        return playerRepository.save(player);
    }

    public void deletePlayer(Long id) throws RuntimeException {
        if (playerRepository.existsById(id)) {
            playerRepository.deleteById(id);
        } else {
            throw new RuntimeException("Player not found with id: " + id);
        }
    }

    public List<Players> getUnassociatedPlayers() {
        return playerRepository.findPlayersWithoutUser();
    }
}
