package com.leogouchon.hubscore.squash_match_service.dto;

import com.leogouchon.hubscore.squash_match_service.entity.SquashMatches;
import com.leogouchon.hubscore.player_service.entity.Players;
import com.leogouchon.hubscore.common.type.MatchPoint;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class SquashMatchResponseDTO {
    private UUID id;
    private Players playerA;
    private Players playerB;
    private List<MatchPoint> pointsHistory;
    private int finalScoreA;
    private int finalScoreB;
    private boolean isFinished;

    public SquashMatchResponseDTO(SquashMatches match) {
        this.id = match.getId();
        this.playerA = match.getPlayerA();
        this.playerB = match.getPlayerB();
        this.pointsHistory = match.getPointsHistory();
        this.finalScoreA = match.getFinalScoreA();
        this.finalScoreB = match.getFinalScoreB();
    }
}
