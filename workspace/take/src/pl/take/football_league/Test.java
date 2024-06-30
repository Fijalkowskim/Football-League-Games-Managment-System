package pl.take.football_league;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Test {
public static void main(String[] args) {
	EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("komis");
	EntityManager manager = managerFactory.createEntityManager(); 
	manager.getTransaction().begin();
	
	//Car car = new Car();
	//car.setMake("Fiat");
	//manager.persist(car);
	
	
	
	manager.getTransaction().commit();
	manager.close();
	managerFactory.close();
	
}
}
