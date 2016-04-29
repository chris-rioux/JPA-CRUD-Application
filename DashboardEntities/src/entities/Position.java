package entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="POSITION")
public class Position {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="position_name")
	private String positionName;
	
	
	@OneToMany(mappedBy="position")
	private List<Advisor> advisors;

	
	public Position() {
		super();
	}

	
	public int getId() {
		return id;
	}
	public String getPositionName() {
		return positionName;
	}
	public List<Advisor> getAdvisors() {
		return advisors;
	}

	
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public void setAdvisors(List<Advisor> advisors) {
		this.advisors = advisors;
	}


}