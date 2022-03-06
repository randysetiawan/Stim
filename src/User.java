
public class User {
	String username, password, gender, country, role;
	int userId;
	static User user;
	
	public static User logInUser(int userId, String username, String password, String gender, String country, String role) {
		if(user == null) {
			user = new User(userId, username, password, gender, country, role);
		}
		return user;
	}
	
	public static User getUser() {
		return user;
		
	}

	private User(int userId, String username, String password, String gender, String country, String role) {
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.gender = gender;
		this.country = country;
		this.role = role;
	}

}
