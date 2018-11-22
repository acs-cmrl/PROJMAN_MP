package model;

public class Employee {
	public static final String TABLE = "employee";
	public static final String COL_ID = "employee_id";
	public static final String COL_FIRSTNAME = "firstname";
	public static final String COL_LASTNAME = "lastname";
	public static final String COL_BOARDMEMBER = "boardmember";
	public static final String COL_PHOTO = "photo";
	
	private Integer employee_id;
	private String firstname;
	private String lastname;
	private Boolean boardmember;
	private String photo;
	
	public Employee(
			Integer employee_id,
			String firstname,
			String lastname,
			Boolean boardmember,
			String photo) {
		setEmployee_id(employee_id);
		setFirstname(firstname);
		setLastname(lastname);
		setBoardmember(boardmember);
		setPhoto(photo);
	}
	
	public Integer getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(Integer employee_id) {
		this.employee_id = employee_id;
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
	public Boolean isBoardmember() {
		return boardmember;
	}
	public void setBoardmember(Boolean boardmember) {
		this.boardmember = boardmember;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
}
