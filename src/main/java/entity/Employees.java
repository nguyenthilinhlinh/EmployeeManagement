package entity;

public class Employees {
	private int employeeID; 
	private String employeeName; 
	private String email;
	private String image;
	private String password;
	private String role;
	private boolean status;
	
	public Employees() {}

	public Employees(int employeeID, String employeeName, String email,String image, String password, String role, boolean status) {
		this.employeeID = employeeID;
		this.employeeName = employeeName;
		this.email = email;
		this.image = image;
		this.password = password;
		this.role = role;
		this.status = status;
		
	}
	
	

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Employees [employeeID=" + employeeID + ", employeeName=" + employeeName + ", email=" + email
				+ ", image=" + image + ", password=" + password + ", role=" + role + ", status=" + status + "]";
	}
	

	
	
	


	
}
