package tv.models;

public class ContactMessage {
	private String email;
	private String message;
	private int id;
	
	public ContactMessage(String email, String message) {
		super();
		this.email = email;
		this.message = message;
	}
		
	public ContactMessage(String email, String message, int id) {
		super();
		this.email = email;
		this.message = message;
		this.id = id;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
