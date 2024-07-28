package tv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import tv.models.ContactMessage;
import tv.utils.DatabaseUtil;

public class ContactDAO {
	private static String INSERT_CONTACT = "INSERT INTO contacts(email, message) VALUES(?, ?);";
	
	public static int addContact(ContactMessage contact) {
		try (Connection connection = DatabaseUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement(INSERT_CONTACT)) {
			statement.setString(1, contact.getEmail());
			statement.setString(2, contact.getMessage());
			return statement.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return 0;
	}
}
