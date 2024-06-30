package pl.take.football_league;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactoryCreator {
	private static EntityManagerFactory factory;

    static {
        try {
           factory = Persistence.createEntityManagerFactory("komis");
        } catch(ExceptionInInitializerError e) {
            throw e;
        }
    }

    public static EntityManagerFactory getFactory() {
        return factory;
    }

}
