package dao;

import java.util.List;

import entities.Advisor;
import entities.Location;
import entities.Position;
import transferobjects.AdvisorTransferObject;

public interface AdvisorDBDAO {
	// login specific
	public boolean login(String name, String password);
	
	// advisor specific
	public Advisor getAdvisor(int id);
	public Advisor getAdvisor(String name);
	public List<Advisor> getAllAdvisors();
	public List<Object[]> getAdvisorFundSales(int id);
	public List<Object[]> getAdvisorYearSales(int id);
	public List<Object[]> getAdvisorTrendSales(int id);
	public void addAdvisor(AdvisorTransferObject ato);
	public Advisor updateAdvisor(AdvisorTransferObject ato);
	public void deleteAdvisor(int id);
	
	// position specific
	public List<Position> getAllPositions();
	
	// location specific
	public List<Location> getAllLocations();
}