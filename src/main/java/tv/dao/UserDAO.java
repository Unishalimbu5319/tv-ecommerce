package tv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tv.models.User;
import tv.utils.DatabaseUtil;

public class UserDAO {
	private static final String SELECT_ALL_USERS = "SELECT * FROM users";
	private static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id=?";
	private static final String SELECT_USER_BY_EMAIl = "SELECT * FROM users WHERE email=?";
	private static final String INSERT_USER = "INSERT INTO users (firstName, lastName, email, username, password, role) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_USER = "UPDATE users SET firstName=?, lastName=?, email=?, username=? WHERE email=?";
	private static final String DELETE_USER = "DELETE FROM users WHERE id=?";

	public static List<User> getAllusers() {
		List<User> users = new ArrayList<>();
		try (Connection connection = DatabaseUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USERS);
			ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String firstName = resultSet.getString("firstName");
				String lastName = resultSet.getString("lastName");
				String email = resultSet.getString("email");
				String username = resultSet.getString("username");
				String password = resultSet.getString("password");
				String role = resultSet.getString("role");
				User user = new User(id, firstName, lastName, email, username, password, role);
				users.add(user);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return users;
	}
	
	public static User getUserById(int id) {
		User user = null;
		try (Connection connection = DatabaseUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_ID)) {
			statement.setInt(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					String firstName = resultSet.getString("firstName");
					String lastName = resultSet.getString("lastName");
					String email = resultSet.getString("email");
					String username = resultSet.getString("username");
					String password = resultSet.getString("password");
					String role = resultSet.getString("role");
					user = new User(id, firstName, lastName, email, username, password, role);
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return user;
	}
	
	public static User getUserByEmail(String email) {
		User user = null;
		try (Connection connection = DatabaseUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_EMAIl)) {
			statement.setString(1, email);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					String firstName = resultSet.getString("firstName");
					String lastName = resultSet.getString("lastName");
					int id = resultSet.getInt("id");
					String username = resultSet.getString("username");
					String password = resultSet.getString("password");
					String role = resultSet.getString("role");
					user = new User(id, firstName, lastName, email, username, password, role);
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return user;
	}
	
	public static int addUser(User user) {
		try (Connection connection = DatabaseUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement(INSERT_USER)) {
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getUsername());
			statement.setString(5, user.getPassword());
			statement.setString(6, user.getRole());
			return statement.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return 0;
	}

	public static void updateUser(User user) {
		try (Connection connection = DatabaseUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement(UPDATE_USER)) {
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getUsername());
			statement.setString(5, user.getEmail());
			statement.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public static void deleteUser(int id) {
		try (Connection connection = DatabaseUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement(DELETE_USER)) {
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}
