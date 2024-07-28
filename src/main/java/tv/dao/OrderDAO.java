package tv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import tv.models.Order;
import tv.utils.DatabaseUtil;

public class OrderDAO {
	private static final String SELECT_ALL_ORDERS = "SELECT * FROM orders";
	private static final String INSERT_ORDER = "INSERT INTO orders(email, order_date, shipping_information, order_status, payment_method, cc_name, cc_number, cc_expiry, cc_cvv, total) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	public static void CreateOrder(Order order) {
		try (Connection connection = DatabaseUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_ORDER)) {
			statement.setString(1, order.getEmail());
			statement.setDate(2, order.getOrderDate());
			statement.setString(3, order.getShippingInformation());
			statement.setString(4, order.getOrderStatus());
			statement.setString(5, order.getPaymentMethod());
			statement.setString(6, order.getCcName());
			statement.setString(7, order.getCcNumber());
			statement.setString(8, order.getCcExpiration());
			statement.setString(9, order.getCcCVV());
			statement.setDouble(10, order.getAmount());
			
			statement.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

    public static List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ORDERS);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String email = resultSet.getString("email");
                Date orderDate = resultSet.getDate("order_date");
                String shippingInformation = resultSet.getString("shipping_information");
                String orderStatus = resultSet.getString("order_status");
                String paymentMethod = resultSet.getString("order_status");
                String ccName = resultSet.getString("cc_name");
                String ccExpiry = resultSet.getString("cc_expiry");
                String ccNumber = resultSet.getString("cc_number");
                String ccCVV = resultSet.getString("cc_cvv");
                double amount = resultSet.getDouble("total");
                
                
                Order order = new Order(id, email, orderDate, shippingInformation, orderStatus, paymentMethod, ccName, ccExpiry, ccNumber, ccCVV, amount);
                orders.add(order);
            }
            
            DatabaseUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

}
