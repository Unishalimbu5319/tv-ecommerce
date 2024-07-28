package tv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tv.models.Product;
import tv.utils.DatabaseUtil;

public class ProductDAO {
	private static final String SELECT_ALL_PRODUCTS = "SELECT * FROM products";
	private static final String SELECT_PRODUCT_BY_ID = "SELECT * FROM products WHERE id=?";
	private static final String INSERT_PRODUCT = "INSERT INTO products (name, image, price, description) VALUES (?, ?, ?, ?)";
	private static final String UPDATE_PRODUCT = "UPDATE products SET name=?, image=?, price=?, description=? WHERE id=?";
	private static final String DELETE_PRODUCT = "DELETE FROM products WHERE id=?";

	// Retrieve all products from the database
	public static List<Product> getAllProducts() {
		List<Product> products = new ArrayList<>();
		try (Connection connection = DatabaseUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_ALL_PRODUCTS);
				ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String image = resultSet.getString("image");
				float price = resultSet.getFloat("price");
				String description = resultSet.getString("description");
				Product product = new Product(id, name, image, price, description);
				products.add(product);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return products;
	}

	// Retrieve a product by ID from the database
	public static Product getProductById(int id) {
		Product product = null;
		try (Connection connection = DatabaseUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_PRODUCT_BY_ID)) {
			statement.setInt(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					String name = resultSet.getString("name");
					String image = resultSet.getString("image");
					float price = resultSet.getFloat("price");
					String description = resultSet.getString("description");
					product = new Product(id, name, image, price, description);
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return product;
	}

	// Add a product to the database
	public static void addProduct(Product product) {
		try (Connection connection = DatabaseUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_PRODUCT)) {
			statement.setString(1, product.getName());
			statement.setString(2, product.getImage());
			statement.setDouble(3, product.getPrice());
			statement.setString(4, product.getDescription());
			statement.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	// Update a product in the database
	public static void updateProduct(Product product) {
		try (Connection connection = DatabaseUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT)) {
			statement.setString(1, product.getName());
			statement.setString(2, product.getImage());
			statement.setDouble(3, product.getPrice());
			statement.setString(4, product.getDescription());
			statement.setInt(5, product.getId());
			statement.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	// Delete a product from the database
	public static void deleteProduct(int id) {
		try (Connection connection = DatabaseUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT)) {
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}

