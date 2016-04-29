package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	@JoinColumn(name="fund_id")
	private Fund fund;
	
	@ManyToOne
	@JoinColumn(name="position_id")
	private Position position;
	
	@ManyToOne
	@JoinColumn(name="location_id")
	private Location location;

	
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
	public Fund getFund() {
		return fund;
	}
	public Position getPosition() {
		return position;
	}
	public Location getLocation() {
		return location;
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
	public void setFund(Fund fund) {
		this.fund = fund;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	
	
}