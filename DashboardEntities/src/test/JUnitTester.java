package test;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entities.Advisor;
import entities.Fund;
import entities.Location;
import entities.Position;
import entities.Sale;

public class JUnitTester {
	private EntityManagerFactory emf;
	private EntityManager em;
	
	@Before
	public void setUp() throws Exception {
		emf = Persistence.createEntityManagerFactory("DashboardEntities");
		em = emf.createEntityManager();
	}
	
	// testing advisor table
	@Test
	public void testAdvisor() {
		Advisor a = em.find(Advisor.class, 1);
		assertEquals("Velma Q. Melendez", a.getName());
		assertEquals(268606, a.getSalary());
		assertEquals("manager", a.getPosition().getPositionName());
		assertEquals(10, a.getFund().getId());
		assertEquals("New York City", a.getLocation().getCity());
		assertEquals(3, a.getFund().getSales().get(0).getId());
	}
	
	// testing fund table
	@Test
	public void testFund() {
		Fund f = em.find(Fund.class, 1);
		assertEquals("American Funds 2055 Trgt Date Retire A", f.getFundName());
	}
	
	// testing location table
	@Test
	public void testLocation() {
		Location l = em.find(Location.class, 2);
		assertEquals("Chicago", l.getCity());
	}
	
	// testing position table
	@Test
	public void testPosition() {
		Position p = em.find(Position.class, 1);
		assertEquals("manager", p.getPositionName());
		assertEquals("Velma Q. Melendez", p.getAdvisors().get(0).getName());
	}
	
	// testing sale table
	@Test
	public void testSale() {
		Sale s = em.find(Sale.class, 1);
		assertEquals(89, s.getPrice());
		assertEquals("2016-03-12 00:00:00.0", s.getSaleDate().toString());
	}
	
	@After
	public void tearDown() throws Exception {
		em.close();
		emf.close();
	}

}
