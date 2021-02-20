package customer.management.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import customer.management.system.cust;



public class CustDao{
	private String jdbcURL = "jdbc:mysql://localhost:3306/userdb?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "rootpasswordgiven ";

	private static final String INSERT_USERS_SQL = "INSERT INTO register" + "  (firstname,lastname,password,gender,email,phone,address,postal) VALUES "
			+ " (?, ?, ?);";

	private static final String SELECT_USER_BY_ID = "select id,firstname,lastname,password,gender,email,phone,address,postal from register where id =?";
	private static final String SELECT_ALL_USERS = "select * from register";
	private static final String DELETE_USERS_SQL = "delete from register where id = ?;";
	private static final String UPDATE_USERS_SQL = "update register set firstname = ?,lastname=?,password=?,gender=?,email= ?, phone =?,address=?postal=? where id = ?;";

	public CustDao() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public void insertUser(cust user) throws SQLException {
		System.out.println(INSERT_USERS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			preparedStatement.setString(1, user.getFirstname());
			preparedStatement.setString(2, user.getLastname());
			preparedStatement.setString(3, user.getPassword())
			preparedStatement.setString(4, user.getGender())
			preparedStatement.setString(5, user.getEmail());
			preparedStatement.setLong(6, user.getPhone());
			preparedStatement.setString(7, user.getAddress());
			preparedStatement.setInt(8, user.getPostal());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public static cust selectUser(int id) {
		cust user = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String firstname = rs.getString("firstname");
				String lastname = rs.getString("lastname");
				String password = rs.getString("password");
				String gender= rs.getString("gender");
				String email = rs.getString("email");
				int phone = rs.getInt("phone");
				String address = rs.getString("address");
				int postal = rs.getInt("postal");
				user = new cust(id,firstname,lastname,password,gender,email,phone,address,postal );
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return user;
	}

	public List<cust> selectAllUsers() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<cust> register = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String firstname = rs.getString("firstname");
				String lastname = rs.getString("lastname");
				String password = rs.getString("password");
				String gender= rs.getString("gender");
				String email = rs.getString("email");
				int phone = rs.getInt("phone");
				String address = rs.getString("address");
				int postal = rs.getInt("postal");
				register.add(new cust(id,firstname,lastname,password,gender,email,phone,address,postal ));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return register;
	}

	public boolean deleteUser(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateUser(cust user) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
			System.out.println("updated USer:"+statement);
			statement.setString(1, user.getFirstname());
			statement.setString(2, user.getLastname());
			statement.setString(3, user.getPassword());
			statement.setString(4, user.getGender());
			statement.setString(5, user.getEmail());
			statement.setInt(6, user.getPhone());
			statement.setString(7, user.getAddress());
			statement.setInt(8, user.getPostal());
			statement.setInt(9, user.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}