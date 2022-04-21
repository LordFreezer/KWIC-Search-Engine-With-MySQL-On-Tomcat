package PAK;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UpdateDao {
	private String dburl = "jdbc:mysql://localhost:3306/linedb";
	private String dbuname = "root";
	private String dbpassword = "mysql";
	private String dbdriver = "com.mysql.jdbc.Driver";
	public static ArrayList<DLine> lines = new ArrayList<DLine>();

	public void loadDriver(String dbDriver) {
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

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

	public String insert(DLine line) {
		loadDriver(dbdriver);
		Connection con = getConnection();
		String result = "Data entered successfully";
		String sql = "insert into linedb.line values(?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, line.getDesc());
			ps.setString(2, line.getUrl());

			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = "Data not entered successfully";
		}
		return result;

	}

	public void get() {

		loadDriver(dbdriver);
		Connection con = getConnection();
		// String result = "Data retrieved successfully";
		String sql = "SELECT * FROM line";

		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				String desc = rs.getString("desc");
				String url = rs.getString("url");

				DLine line = new DLine(desc, url);
				if (!lines.contains(line))
					lines.add(line);
				// System.out.println("FROM DATABASE" + desc + " " + url);
			}
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
