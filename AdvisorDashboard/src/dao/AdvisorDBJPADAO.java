package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import entities.Advisor;

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
	
	@Override 
	public List<Advisor> getAllAdvisors() {
		List<Advisor> advisors = em.createQuery("SELECT a FROM Advisor a").getResultList();
		for (Advisor advisor : advisors) {
			System.out.println(advisor.getId());
		}
		return advisors;
	}

	@Override
	public void updateAdvisor(int id, Advisor a) {
		Advisor managedAdv = em.find(Advisor.class, id);
		managedAdv.setName(a.getName());

	}

}
