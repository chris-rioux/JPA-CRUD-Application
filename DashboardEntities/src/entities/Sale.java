package entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="SALE")
public class Sale {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int price;
	private int shares;
	@Column(name="sale_date")
	private Date saleDate;
	
	
	@ManyToOne
	@JoinColumn(name="fund_id")
	private Fund fund;
	
	@ManyToOne
	@JoinColumn(name="advisor_id")
	private Advisor advisor;


	public Sale() {
		super();
	}


	public int getId() {
		return id;
	}
	public int getPrice() {
		return price;
	}
	public int getShares() {
		return shares;
	}
	public Date getSaleDate() {
		return saleDate;
	}
	public Fund getFund() {
		return fund;
	}
	public Advisor getAdvisor() {
		return advisor;
	}


	public void setPrice(int price) {
		this.price = price;
	}
	public void setShares(int shares) {
		this.shares = shares;
	}
	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}
	public void setFund(Fund fund) {
		this.fund = fund;
	}
	public void setAdvisor(Advisor advisor) {
		this.advisor = advisor;
	}
	

}