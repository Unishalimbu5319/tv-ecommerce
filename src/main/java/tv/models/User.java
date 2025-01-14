package tv.models;

public class User {
	private int id;
	private String firstName;
	private String lastName;
	private String username;
	private String email;
    private String password;
    private String role;

    public User(String firstName, String lastName, String email, String username, String password, String role) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    	this.username = username;
        this.password = password;
        this.role = role;
    }

    
    public User(int id, String firstName, String lastName, String email, String username, String password, String role) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public String getUsername() {
        return username;
    }

    public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getPassword() {
        return password;
    }

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
