package PAK;

/**
 * @author Chad Marshall
 *
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * The Class UpdateDao.
 */

public class UpdateDao {

	/** The dburl. */
	private String dburl = "jdbc:mysql://localhost:3306/linedb";

	/** The dbuname. */
	private String dbuname = "root";

	/** The dbpassword. */
	private String dbpassword = "mysql";

	/** The dbdriver. */
	private String dbdriver = "com.mysql.jdbc.Driver";

	/** Description and url pairs stored as a set of lines. */
	public static ArrayList<DLine> lines = new ArrayList<DLine>();

	/**
	 * OVERVIEW: Load driver - gets the Class instance of the db
	 *
	 * @param dbDriver
	 */
	public void loadDriver(String dbDriver) {
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * OVERVIEW: Establishes a connection to MySQL database with username and
	 * password.
	 *
	 * @return the connection
	 */
	public Connection getConnection() {

		Connection con = null;
		try {
			con = DriverManager.getConnection(dburl, dbuname, dbpassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;

	}

	/**
	 * OVERVIEW: Inserts the description and url pair into the db
	 *
	 * @param line
	 * @return the result
	 */
	public String insert(DLine line) {
		// Establishes connection to database
		loadDriver(dbdriver);
		Connection con = getConnection();

		String result = "Data entered successfully";
		// SQL statement to be executed as part of POST
		String sql = "insert into linedb.line values(?,?)";
		try {
			// Primes the description and url pair for insertion and executes SQL
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, line.getDesc());
			ps.setString(2, line.getUrl());
			// Updates db
			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = "Data not entered successfully";
		}
		return result;

	}

	/**
	 * OVERVIEW: Fetches all descriptions and url pairs from db
	 */
	public void get() {

		// Establishes connection to db
		loadDriver(dbdriver);
		Connection con = getConnection();

		// SQL statement to be executed as part of GET.
		String sql = "SELECT * FROM line";

		try {
			// Executes SQL
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			// Iterates through the pairs and stores them in a set of lines
			while (rs.next()) {
				String desc = rs.getString("desc");
				String url = rs.getString("url");

				DLine line = new DLine(desc, url);
				if (!lines.contains(line))
					lines.add(line);
			}
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
