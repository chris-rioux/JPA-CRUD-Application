package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import entities.Advisor;
import entities.Location;
import entities.Position;

@Transactional
public class AdvisorDBJPADAO implements AdvisorDBDAO {
	@PersistenceContext
	private EntityManager em;

	@Override
	public boolean login(String name, String password) {
		return (em.createQuery("SELECT a.password FROM Advisor a WHERE a.name = '" + name + "'").getSingleResult().equals(password)); 
	}

	@Override
	public Advisor getAdvisor(int id) {
		Advisor a = em.find(Advisor.class, id);
		em.detach(a);
		return a;
	}
	
	
	
//	@Override 
//	public HashMap<String, Integer> getAdvisorSales(int id) {
//		HashMap<String, Integer> fundSales = (HashMap<String, Integer>) em.createQuery("SELECT f.fundName, SUM(s.price * s.shares) FROM Sale s LEFT JOIN Fund f ON s.fund_id = f.id LEFT JOIN Advisor a ON s.advisor_id = a.id WHERE a.id = " + id + " GROUP BY f.fundName").getResultList();
//		return fundSales;
//	}
	
	
	
	@Override 
	public List<Advisor> getAllAdvisors() {
		List<Advisor> advisors = em.createQuery("SELECT a FROM Advisor a").getResultList();
		return advisors;
	}
	
	@Override
	public void addAdvisor(String name, int salary, String password, Position position, Location location) {
		Advisor a = new Advisor();
		a.setName(name);
		a.setSalary(salary);
		a.setPassword(password);
		a.setPosition(position);
		a.setLocation(location);
	}

	@Override
	public void updateAdvisor(int id, Advisor a) {
		Advisor managedAdv = em.find(Advisor.class, id);
		managedAdv.setName(a.getName());

	}
	
	@Override
	public void deleteAdvisor(int id) {
		Advisor delAdv = em.find(Advisor.class, id);
		em.remove(delAdv);
	}

}