package in.ac.adit.model;

public class UserContact {
	private String name;
	private String email;
	private String phoneno;
	private String comments;
	private Long id;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Long getId() {
		return id;
	}
	public void setId(long l) {
		this.id = (long) l;
	}
	
}
