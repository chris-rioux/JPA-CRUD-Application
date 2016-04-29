package test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entities.Advisor;

public class DataTester {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("DashboardEntities");
		EntityManager em = emf.createEntityManager();
		
		List<Advisor> advisors = em.createQuery("SELECT a FROM Advisor a").getResultList();
		for (Advisor a : advisors) {
			System.out.println(a.getName());
		}

	}

}