package PAK;

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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String adminText = request.getParameter("admin");
		String[] entireLine = adminText.split("\n");

		String result = "";
		for (int i = 0; i < entireLine.length; i++) {
			int pos = entireLine[i].indexOf("http");

			String first = entireLine[i].substring(0, pos);
			first = first.replaceAll("\\s+$", "").replaceAll("\n", "").replaceAll("\r", "");

			String second = entireLine[i].substring(pos);
			second = second.replaceAll("\n", "").replaceAll("\r", "");
			UpdateDao uDao = new UpdateDao();
			// System.out.println("|"+first+"|");
			// System.out.println("|"+second+"|");
			DLine line = new DLine(first, second);
			result += uDao.insert(line) + "\n";

		}

		response.getWriter().print(result);
	}

}
