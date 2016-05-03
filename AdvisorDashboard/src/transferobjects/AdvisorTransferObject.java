package transferobjects;

public class AdvisorTransferObject {
	private Integer id;
	private String name;
	private Integer salary;
	private String password;
	private Integer position;
	private Integer location;
	
	
	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Integer getSalary() {
		return salary;
	}
	public String getPassword() {
		return password;
	}
	public Integer getPosition() {
		return position;
	}
	public Integer getLocation() {
		return location;
	}
	
	
	public void setId(Integer id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSalary(Integer salary) {
		this.salary = salary;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}
	public void setLocation(Integer location) {
		this.location = location;
	}
	
	
}