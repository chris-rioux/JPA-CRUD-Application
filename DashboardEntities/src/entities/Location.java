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
@Table(name="LOCATION")
public class Location {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String address;
	private String city;
	private String country;
	@Column(name="lat_long")
	private String latLong;
	
	
	@OneToMany(mappedBy="location")
	private List<Advisor> advisors;

	
	public Location() {
		super();
	}

	
	public int getId() {
		return id;
	}
	public String getAddress() {
		return address;
	}
	public String getCity() {
		return city;
	}
	public String getCountry() {
		return country;
	}
	public String getLatLong() {
		return latLong;
	}
	public List<Advisor> getAdvisors() {
		return advisors;
	}

	
	public void setAddress(String address) {
		this.address = address;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public void setLatLong(String latLong) {
		this.latLong = latLong;
	}
	public void setAdvisors(List<Advisor> advisors) {
		this.advisors = advisors;
	}
	
	
	public void addAdvisor(Advisor advisor) {
        if (!advisors.contains(advisor)) {
            // add advisor to this location's list
            advisors.add(advisor);
            // remove advisor from their old location
            if (advisor.getLocation() != null) {
                advisor.getLocation().getAdvisors().remove(advisor);
            }
            // Bidirectional, so add location to the advisor
            advisor.setLocation(this);
        }
    }
    
    public void removeAdvisor(Advisor advisor) {
        if (advisors.contains(advisor))  {
            // remove advisor from this location's list
            advisors.remove(advisor);
            // Bidirectional, so null out the location in the advisor
            advisor.setLocation(null);
        }
    }

	
}