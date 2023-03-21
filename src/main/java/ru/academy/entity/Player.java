package ru.academy.entity;


import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"team", "gameEvent"})
public class Player {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer number;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToMany(mappedBy = "player")
    private List<GameEvent> gameEvent;

    public void addEvent(GameEvent event) {
        if (gameEvent == null) {
            gameEvent = new ArrayList<>();
            gameEvent.add(event);
        } else {
            gameEvent.add(event);
        }
    }
}
