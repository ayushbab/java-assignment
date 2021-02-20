package customer.management.system;

import java.util.List;

public class cust{
	private int id;  
	protected String firstname,lastname,password,gender,email,address;
	protected int phone,postal;
	  
	public cust(int id2, String firstname2, String lastname2, String password2, String gender2, String email2,
			int phone2, String address2, int postal2) {
		// TODO Auto-generated constructor stub
	}
	public int getId() {  
	    return id;  

}
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public int getPostal() {
		return postal;
	}

	public void setPostal(int postal) {
		this.postal = postal;
	}
	public static List<cust> selectAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	

  
}  