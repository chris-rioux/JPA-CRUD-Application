package dao;

import java.util.List;

import entities.Advisor;
import entities.Location;
import entities.Position;

public interface AdvisorDBDAO {
	// login specific
	public boolean login(String name, String password);
	
	// advisor specific
	public Advisor getAdvisor(int id);
	public Advisor getAdvisor(String name);
//	public HashMap<String, Integer> getAdvisorSales(int id);
	public List<Advisor> getAllAdvisors();
	public void addAdvisor(String name, Integer salary, String password, int position, int location);
	public void updateAdvisor(Integer id, Advisor a);
	public void deleteAdvisor(int id);
	
	// position specific
	public List<Position> getAllPositions();
	
	// location specific
	public List<Location> getAllLocations();
}
