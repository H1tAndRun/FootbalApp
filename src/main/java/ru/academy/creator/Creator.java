package ru.academy.creator;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.academy.entity.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Creator {

    public void createTeams(Session session) {
        Team barcelona = new Team("Barcelona");
        Team realMadrid = new Team("Real Madrid");
        session.save(barcelona);
        session.save(realMadrid);
    }

    public Team createBarcelonaPlayers(Session session) {
        List<Player> barselonaPlayers = new ArrayList<>();
        barselonaPlayers.add(createPlayer("Valdes", 1));
        barselonaPlayers.add(createPlayer("Alves", 2));
        barselonaPlayers.add(createPlayer("Pique", 3));
        barselonaPlayers.add(createPlayer("Fabregas", 4));
        barselonaPlayers.add(createPlayer("Puyol", 5));
        barselonaPlayers.add(createPlayer("Xavi", 6));
        barselonaPlayers.add(createPlayer("Villa", 7));
        barselonaPlayers.add(createPlayer("Iniesta", 8));
        barselonaPlayers.add(createPlayer("Sanchez", 9));
        barselonaPlayers.add(createPlayer("Messi", 10));
        barselonaPlayers.add(createPlayer("Aclantara", 11));
        Query query = session.createQuery("FROM Team WHERE name = :name");
        query.setParameter("name", "Barcelona");
        Team team = (Team) query.uniqueResult();
        barselonaPlayers.stream()
                .peek(e -> e.setTeam(team))
                .forEach(session::save);
        team.setPlayers(barselonaPlayers);
        return team;
    }

    public Team createRealMadridPlayers(Session session) {
        List<Player> realMadridPlayers = new ArrayList<>();
        realMadridPlayers.add(createPlayer("Casilias", 1));
        realMadridPlayers.add(createPlayer("Varan", 2));
        realMadridPlayers.add(createPlayer("Pepe", 3));
        realMadridPlayers.add(createPlayer("Ramos", 4));
        realMadridPlayers.add(createPlayer("Coentrao", 5));
        realMadridPlayers.add(createPlayer("Xedira", 6));
        realMadridPlayers.add(createPlayer("Ronaldo", 7));
        realMadridPlayers.add(createPlayer("Kaka", 8));
        realMadridPlayers.add(createPlayer("Benzema", 9));
        realMadridPlayers.add(createPlayer("Ozil", 10));
        realMadridPlayers.add(createPlayer("Carvalho", 11));
        Query query = session.createQuery("FROM Team WHERE name = :name");
        query.setParameter("name", "Real Madrid");
        Team team = (Team) query.uniqueResult();
        realMadridPlayers.stream()
                .peek(e -> e.setTeam(team))
                .forEach(session::save);
        team.setPlayers(realMadridPlayers);
        return team;
    }

    public void createGame(Session session,
                           Team team1,
                           Team team2,
                           Integer goalTeam1,
                           Integer goalTeam2,
                           LocalDate date) {
        Game game = Game.builder()
                .team1(team1)
                .team2(team2)
                .goalsTeam1(goalTeam1)
                .goalsTeam2(goalTeam2)
                .matchDate(date)
                .build();
        session.save(game);
    }

    public void CreateGameEvent(Session session, String playerName, LocalDate matchDate, Action action) {
        Query queryPlayer = session.createQuery("from Player where name = :playerName");
        Player player = (Player) queryPlayer.setParameter("playerName", playerName).uniqueResult();
        Query queryGame = session.createQuery("from Game where matchDate=:matchDate");
        Game game = (Game) queryGame.setParameter("matchDate", matchDate).uniqueResult();
        GameEvent gameEvent = GameEvent
                .builder()
                .game(game)
                .player(player)
                .action(action)
                .build();
        player.addEvent(gameEvent);
        game.addEvent(gameEvent);
        session.save(gameEvent);
    }

    private Player createPlayer(String name, Integer number) {
        return Player
                .builder()
                .name(name)
                .number(number)
                .build();
    }
}
