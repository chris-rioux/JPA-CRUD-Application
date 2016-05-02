package dao;

import java.util.List;

import entities.Advisor;
import entities.Location;
import entities.Position;

public interface AdvisorDBDAO {
	public boolean login(String name, String password);
	public Advisor getAdvisor(int id);
//	public HashMap<String, Integer> getAdvisorSales(int id);
	public List<Advisor> getAllAdvisors();
	public void addAdvisor(String name, int salary, String password, Position position, Location location);
	public void updateAdvisor(int id, Advisor a);
	public void deleteAdvisor(int id);
}
