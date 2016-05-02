package entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ADVISOR")
public class Advisor {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private int salary;
	private String password;

	
	@ManyToOne
	@JoinColumn(name="position_id")
	private Position position;
	
	@ManyToOne
	@JoinColumn(name="location_id")
	private Location location;
	
	@OneToMany(mappedBy="advisor")
	private List<Sale> sales;


	public Advisor() {
		super();
	}


	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public int getSalary() {
		return salary;
	}
	public String getPassword() {
		return password;
	}
	public Position getPosition() {
		return position;
	}
	public Location getLocation() {
		return location;
	}
	public List<Sale> getSales() {
		return sales;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public void setSales(List<Sale> sales) {
		this.sales = sales;
	}
	
	
}