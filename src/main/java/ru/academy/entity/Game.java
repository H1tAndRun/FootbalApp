package ru.academy.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"gameEvents"})
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne
    @JoinColumn(name = "team1_id")
    private Team team1;

    @ManyToOne
    @JoinColumn(name = "team2_id")
    private Team team2;

    @Column
    private Integer goalsTeam1;

    @Column
    private Integer goalsTeam2;

    @Column
    private LocalDate matchDate;

    @OneToMany(mappedBy = "game")
    private List<GameEvent> gameEvents;

    public void addEvent(GameEvent event) {
        if (gameEvents == null) {
            gameEvents = new ArrayList<>();
            gameEvents.add(event);
        } else {
            gameEvents.add(event);
        }
    }

}
