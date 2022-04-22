package PAK;

/**
 * @author Chad Marshall
 *
 */
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Update
 */

@WebServlet("/Update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Update() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * OVERVIEW: Takes admin form input as string and parses it into description and
	 * url pairs. Then POSTs it to the MySQL database.
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Takes input from description/url box on admin page
		String adminText = request.getParameter("admin");
		String[] entireLine = adminText.split("\n");

		String result = "";
		// Iterates through entire input and POSTs ever line.
		for (int i = 0; i < entireLine.length; i++) {
			// Finds the point of separation between description and url
			int pos = entireLine[i].indexOf("http");
			// removes newlines from entire description and whitespace from end of
			// decription.
			String first = entireLine[i].substring(0, pos);
			first = first.replaceAll("\\s+$", "").replaceAll("\n", "").replaceAll("\r", "");
			// removes newlines from url
			String second = entireLine[i].substring(pos);
			second = second.replaceAll("\n", "").replaceAll("\r", "");
			// Establishes connection to MySQL database
			UpdateDao uDao = new UpdateDao();
			// POSTs single line to MySQL database
			DLine line = new DLine(first, second);
			// Builds result statement for admin user to see
			result += uDao.insert(line) + "\n";

		}

		response.getWriter().print(result);
	}

}
