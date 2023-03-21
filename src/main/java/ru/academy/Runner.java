package ru.academy;

import org.hibernate.Session;
import ru.academy.config.HibernateConfig;
import ru.academy.creator.Creator;
import ru.academy.entity.Action;
import ru.academy.entity.Team;
import java.time.LocalDate;

public class Runner {

    public static void main(String[] args) {
        try (Session session = HibernateConfig.createSessionFactory().openSession()) {
            init(session);
            FootballInfo footballInfo = new FootballInfo();
            System.out.println(footballInfo.getStatisticsByPlayerName(session, "Messi"));
            System.out.println(footballInfo.getStatisticsByTeamName(session,"Barcelona"));
            System.out.println(footballInfo.getCountGame(session));
            System.out.println(footballInfo.getStatisticsYellowCard(session));

        }
    }

    private static void init(Session session) {
        Creator creator = new Creator();
        creator.createTeams(session);
        Team barcelonaPlayers = creator.createBarcelonaPlayers(session);
        Team realMadridPlayers = creator.createRealMadridPlayers(session);
        creator.createGame(session, barcelonaPlayers, realMadridPlayers, 2, 1,
                LocalDate.of(2022, 01, 13));
        creator.CreateGameEvent(session, "Messi",
                LocalDate.of(2022, 01, 13), Action.GOAL);
        creator.CreateGameEvent(session, "Messi",
                LocalDate.of(2022, 01, 13), Action.GOAL);
        creator.CreateGameEvent(session, "Ronaldo",
                LocalDate.of(2022, 01, 13), Action.GOAL);

        creator.createGame(session, barcelonaPlayers, realMadridPlayers, 0, 0,
                LocalDate.of(2022, 03, 15));
        creator.CreateGameEvent(session, "Pique",
                LocalDate.of(2022, 03, 15), Action.YELLOW_CARD);
        creator.CreateGameEvent(session, "Puyol",
                LocalDate.of(2022, 03, 15), Action.YELLOW_CARD);
        creator.CreateGameEvent(session, "Ramos",
                LocalDate.of(2022, 03, 15), Action.YELLOW_CARD);
        creator.CreateGameEvent(session, "Pepe",
                LocalDate.of(2022, 03, 15), Action.YELLOW_CARD);

        creator.createGame(session, realMadridPlayers, barcelonaPlayers, 1, 0,
                LocalDate.of(2022, 04, 17));
        creator.CreateGameEvent(session, "Benzema",
                LocalDate.of(2022, 04, 17), Action.GOAL);
        creator.CreateGameEvent(session, "Alves",
                LocalDate.of(2022, 04, 17), Action.YELLOW_CARD);
    }
}
