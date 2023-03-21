package ru.academy;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.academy.entity.Action;
import ru.academy.entity.GameEvent;
import ru.academy.entity.Player;
import java.util.List;

public class FootballInfo {

    public long getStatisticsByPlayerName(Session session, String playerName) {
        Query query = session.createQuery("from  Player where name =: name");
        Player player = (Player) query.setParameter("name", playerName).uniqueResult();
        return player.getGameEvent().stream().filter(e -> e.getAction().equals(Action.GOAL)).count();
    }

    public long getStatisticsByTeamName(Session session, String teamName) {
        Query query = session.createQuery("SELECT p FROM Player p JOIN p.team t WHERE t.name = :teamName");
        List<Player> players = query.setParameter("teamName", teamName).getResultList();
        return players.stream().filter(player -> player.getGameEvent() != null)
                .flatMap(player -> player.getGameEvent().stream())
                .filter(event -> event.getAction().equals(Action.GOAL))
                .count();
    }

    public long getCountGame(Session session) {
        Query query = session.createQuery("SELECT COUNT(*) FROM Game");
        return (long) query.uniqueResult();
    }

    public long getStatisticsYellowCard(Session session){
        List<GameEvent> gameEvent = session.createQuery("from GameEvent").getResultList();
        return gameEvent.stream().filter(e->e.getAction().equals(Action.YELLOW_CARD)).count();
    }
}
