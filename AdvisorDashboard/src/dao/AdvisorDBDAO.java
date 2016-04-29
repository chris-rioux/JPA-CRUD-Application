package dao;

import java.util.List;

import entities.Advisor;

public interface AdvisorDBDAO {
	public boolean login(String name, String password);
	public Advisor getAdvisor(int id);
	public List<Advisor> getAllAdvisors();
	public void updateAdvisor(int id, Advisor a);

}
