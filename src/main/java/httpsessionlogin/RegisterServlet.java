package httpsessionlogin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("link.html").include(request, response);

		String name = request.getParameter("userName");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		Context ctx = null;

		try {
			ctx = new InitialContext();
			DataSource myds = (DataSource) ctx.lookup("java:jboss/mysqlDS");
			/*
			 * Class.forName("com.mysql.jdbc.Driver"); Connection con =
			 * DriverManager.getConnection("jdbc:mysql://192.168.1.245:3306/test", "root",
			 * "");
			 */
			Connection con = myds.getConnection();
			 Statement st = con.createStatement();
			    int i = st.executeUpdate("insert into EMP(username, password) values ('"+name + "','" + password + "'"+")");
			    if (i > 0) {
			        response.sendRedirect("welcome.jsp");
			       
			    } else {
			        response.sendRedirect("login.html");
			    }
		} catch (Exception e) {

		}
		out.close();

	}

}
