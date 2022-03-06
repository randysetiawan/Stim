import java.sql.*;

public class Connect {
	private final String USERNAME = "root";
	private final String PASSWORD = "root";
	private final String DATABASE = "stim";
	private final String HOST = "localhost:3306";
	private final String CONNECTION = String.format("jdbc:mysql://%s/%s", HOST, DATABASE);
	private Connection connnection;
	private Statement statement;
	private ResultSet resultSet;
	private PreparedStatement preparedStatement;
	private static Connect instance = null;
	
	public static Connect getInstance() {
		if(instance == null) instance = new Connect();
		return instance;
	}
	
	private Connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connnection = DriverManager.getConnection(CONNECTION, USERNAME, PASSWORD);
			statement = connnection.createStatement();
		} catch (Exception e) {
			System.out.println("Failed Connect to DB");
			System.exit(0);
		}
		
	}
	
	//SELECT
	public ResultSet executeQuery(String query) {
		resultSet = null;
		try {
			resultSet = statement.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}
	
	//INSERT, UPDATE, DELETE
	public void executeUpdate(String query) {
		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//prepared statement method
	public PreparedStatement preparedStatement(String query) {
		preparedStatement = null;
		try {
			statement = connnection.prepareStatement(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return preparedStatement;
	}
	

}