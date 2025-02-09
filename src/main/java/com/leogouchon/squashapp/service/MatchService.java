package com.leogouchon.squashapp.service;

import com.leogouchon.squashapp.model.Match;
import com.leogouchon.squashapp.model.Player;
import com.leogouchon.squashapp.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatchService {

    private final MatchRepository matchRepository;
    private final PlayerService playerService;

    @Autowired
    public MatchService(MatchRepository matchRepository, PlayerService playerService) {
        this.matchRepository = matchRepository;
        this.playerService = playerService;
    }

    public Match createMatch(Long player1Id, Long player2Id) {
        Optional<Player> playerA = playerService.getPlayer(player1Id);
        Optional<Player> playerB = playerService.getPlayer(player2Id);
        if (playerA.isEmpty() || playerB.isEmpty()) {
            throw new RuntimeException("Player not found");
        } else {
            Match match = new Match(playerA.get(), playerB.get());
            System.out.println(match);
            return matchRepository.save(match);
        }
    }

    public String addPoint(Match match, Player player, String serviceSide) {
        if (match == null) {
            throw new RuntimeException("Match not found");
        }
        if (match.isFinished()) {
            throw new RuntimeException("Match is already finished");
        }
        match.addService(player, serviceSide);
        matchRepository.save(match);
        return match.getPointsHistory();
    }

    public Boolean isFinished(Match match) {
        return match.isFinished();
    }

    public void deleteMatch(Long id) {
        if (matchRepository.existsById(id)) {
            matchRepository.deleteById(id);
        } else {
            throw new RuntimeException("Player not found with id: " + id);
        }
    }

    public List<Match> getMatches() {
        return matchRepository.findAll();
    }

    public Optional<Match> getMatch(Long id) {
        return matchRepository.findById(id);
    }
}
