package com.leogouchon.hubscore.squash_match_service.repository;

import com.leogouchon.hubscore.squash_match_service.entity.SquashMatches;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SquashMatchRepository extends JpaRepository<SquashMatches, UUID>, JpaSpecificationExecutor<SquashMatches> {

    @Query(
            value = """
                 SELECT 
                    CAST(EXTRACT(EPOCH FROM DATE_TRUNC('day', m.end_time)) AS BIGINT) AS day_unix,
                    COUNT(*) AS total_matches,
                    p.id AS player_id,
                    p.firstname AS player_name,
                    
                    SUM(CASE 
                        WHEN (m.player_a_id = p.id AND m.final_score_a > m.final_score_b) OR 
                             (m.player_b_id = p.id AND m.final_score_b > m.final_score_a) 
                        THEN 1 ELSE 0 END) AS wins,
                     
                    SUM(CASE
                        WHEN (m.player_a_id = p.id AND m.final_score_a < m.final_score_b) OR
                             (m.player_b_id = p.id AND m.final_score_b < m.final_score_a)
                        THEN 1 ELSE 0 END) AS losses,
                
                    SUM(CASE
                        WHEN m.player_b_id = p.id THEN m.final_score_b
                        WHEN m.player_a_id = p.id THEN m.final_score_a
                        ELSE 0 END) AS points_scored,
                     
                    SUM(CASE
                        WHEN m.player_a_id = p.id THEN m.final_score_b
                        WHEN m.player_b_id = p.id THEN m.final_score_a
                        ELSE 0 END) AS points_conceded
                    
                 FROM squash_matches AS m
                 JOIN players p ON p.id = m.player_a_id OR p.id = m.player_b_id
                 GROUP BY day_unix, p.id, p.firstname
                 ORDER BY day_unix desc, p.id
        """, nativeQuery = true
    )
    List<Object[]> getSessionsData(Pageable pageable);
}
