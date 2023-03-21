package ru.academy.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString(exclude = {"players"})
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    @NonNull
    private String name;

    @OneToMany(mappedBy = "team")
    private List<Player> players;
}
