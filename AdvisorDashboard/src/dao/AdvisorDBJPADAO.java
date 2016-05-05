package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import entities.Advisor;
import entities.Location;
import entities.Position;
import entities.Sale;
import transferobjects.AdvisorTransferObject;

@Transactional
public class AdvisorDBJPADAO implements AdvisorDBDAO {
	@PersistenceContext
	private EntityManager em;

	// login specific methods
	@Override
	public boolean login(String name, String password) {
		List<Advisor> aList = getAllAdvisors();
		for (Advisor advisor : aList) {
			if (advisor.getName().equals(name) && advisor.getPassword().equals(password)) {
				return true;
			}
			else {
				continue;
			}
		}
		return false;
	}

	// get advisor methods
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
	
	@Override 
	public List<Advisor> getAllAdvisors() {
		List<Advisor> advisors = em.createQuery("SELECT a FROM Advisor a").getResultList();
		return advisors;
	}
	
	// get advisor chart data methods
	@Override
	public List<Object[]> getAdvisorFundSales(int id) {
		List<Object[]> advisorFundSales = em.createNativeQuery("SELECT f.fund_name, SUM(s.price*s.shares) FROM sale s LEFT JOIN fund f ON s.fund_id = f.id LEFT JOIN advisor a ON s.advisor_id = a.id WHERE a.id = " + id + " GROUP BY f.fund_name").getResultList();
		return advisorFundSales;
	}
	
	@Override
	public List<Object[]> getAdvisorYearSales(int id) {
		List<Object[]> advisorYearSales = em.createNativeQuery("SELECT YEAR(s.sale_date), SUM(s.price*s.shares) FROM sale s LEFT JOIN advisor a ON s.advisor_id = a.id WHERE a.id = " + id + " GROUP BY YEAR(s.sale_date)").getResultList();
		return advisorYearSales;
	}
	
	@Override
	public List<Object[]> getAdvisorTrendSales(int id) {
		List<Object[]> advisorTrendSales = em.createNativeQuery("SELECT DATE(s.sale_date), SUM(s.price*s.shares) FROM sale s LEFT JOIN advisor a ON s.advisor_id = a.id WHERE a.id = " + id + " GROUP BY s.sale_date").getResultList();
		return advisorTrendSales;
	}
	
	// advisor manipulation methods
	@Override
	public void addAdvisor(AdvisorTransferObject ato) {
		Advisor a = new Advisor();
		a.setName(ato.getName());
		a.setSalary(ato.getSalary());
		a.setPassword(ato.getPassword());
		
		Position managedPos = em.find(Position.class, ato.getPosition());
		managedPos.addAdvisor(a);
		
		Location managedLoc = em.find(Location.class, ato.getLocation());
		managedLoc.addAdvisor(a);
		
		em.persist(a);
	}

	@Override
	public Advisor updateAdvisor(AdvisorTransferObject ato) {
		
		Advisor managedAdv = em.find(Advisor.class, ato.getId());
		managedAdv.setName(ato.getName());
		managedAdv.setSalary(ato.getSalary());
		managedAdv.setPassword(ato.getPassword());
		
		Position managedPos = em.find(Position.class, ato.getPosition());
		managedPos.addAdvisor(managedAdv);
		
		Location managedLoc = em.find(Location.class, ato.getLocation());
		managedLoc.addAdvisor(managedAdv);
		
		return managedAdv;				
	}
	
	@Override
	public void deleteAdvisor(int id) {
		Advisor delAdv = em.find(Advisor.class, id);
		
		if (id == 1) {
		}
		else {
			// delete sales of advisor to allow deletion of advisor
			List<Sale> sales = delAdv.getSales();
			for (Sale s : sales) {
				em.remove(s);
			}
			
			// remove advisor from database
			em.remove(delAdv);			
		}
	}
	
	// ensuring cascading db relationships between advisors and the positions and locations
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