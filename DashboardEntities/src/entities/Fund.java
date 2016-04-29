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
@Table(name="FUND")
public class Fund {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="fund_name")
	private String fundName;
	private String broker;
	
	
	@OneToMany(mappedBy="fund")
	private List<Advisor> advisors;
	
	@OneToMany(mappedBy="fund")
	private List<Sale> sales;
	

	public Fund() {
		super();
	}


	public int getId() {
		return id;
	}
	public String getFundName() {
		return fundName;
	}
	public String getBroker() {
		return broker;
	}
	public List<Advisor> getAdvisors() {
		return advisors;
	}
	public List<Sale> getSales() {
		return sales;
	}
	
	
	
	public void setFundName(String fundName) {
		this.fundName = fundName;
	}
	public void setBroker(String broker) {
		this.broker = broker;
	}
	public void setAdvisors(List<Advisor> advisors) {
		this.advisors = advisors;
	}
	public void setSales(List<Sale> sales) {
		this.sales = sales;
	}

	
}