package ru.academy.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ru.academy.entity.Game;
import ru.academy.entity.GameEvent;
import ru.academy.entity.Player;
import ru.academy.entity.Team;

public class HibernateConfig {

    private static SessionFactory sessionFactory;

    public static SessionFactory createSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.addAnnotatedClass(Game.class);
            configuration.addAnnotatedClass(GameEvent.class);
            configuration.addAnnotatedClass(Player.class);
            configuration.addAnnotatedClass(Team.class);
            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            return sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }
}
