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
		List<Advisor> aList = getAllAdvisors();
		for (Advisor advisor : aList) {
			if (advisor.getName().equals(name) && advisor.getPassword().equals(password)) {
				return true;
			}
			else{
				continue;
			}
		}
		return false;
	}

	@Override
	public Advisor getAdvisor(int id) {
		Advisor a = em.find(Advisor.class, id);
		em.detach(a);
		return a;
	}
	
	@Override
	public Advisor getAdvisor(String name) {
		Advisor a = (Advisor) em.createQuery("SELECT a FROM Advisor a WHERE a.name = '" + name + "'").getSingleResult();
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
	public void addAdvisor(String name, Integer salary, String password, int position, int location) {
		Advisor a = new Advisor();
		a.setName(name);
		a.setSalary(salary);
		a.setPassword(password);
		
		Position managedPos = em.find(Position.class, position);
		a.setPosition(managedPos);
		
		Location managedLoc = em.find(Location.class, location);
		a.setLocation(managedLoc);
		
		em.persist(a);
		
		managedPos.addAdvisor(a);
		managedLoc.addAdvisor(a);
	}

	@Override
	public void updateAdvisor(Integer id, Advisor a) {
		Advisor managedAdv = em.find(Advisor.class, id);
		managedAdv.setName(a.getName());
		managedAdv.setSalary(a.getSalary());
		managedAdv.setPassword(a.getPassword());
		managedAdv.setPosition(a.getPosition());
		managedAdv.setLocation(a.getLocation());
		System.out.println("JPADAO: " + managedAdv.getId() + " " + managedAdv.getName() + " " + managedAdv.getSalary() + " " + managedAdv.getPassword());
	}
	
	@Override
	public void deleteAdvisor(int id) {
		Advisor delAdv = em.find(Advisor.class, id);
		em.remove(delAdv);
	}
	
	@Override
	public List<Position> getAllPositions() {
		List<Position> positions = em.createQuery("SELECT p FROM Position p").getResultList();
		return positions;
	}
	
	@Override
	public List<Location> getAllLocations() {
		List<Location> locations = em.createQuery("SELECT l FROM Location l").getResultList();
		return locations;
	}

}