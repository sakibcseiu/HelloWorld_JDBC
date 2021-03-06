package com.sakib.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.jndi.url.corbaname.corbanameURLContextFactory;

/**
 * Servlet implementation class Insert
 */
@WebServlet("/Insert")
public class Insert extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {

			
			PrintWriter out = resp.getWriter();

			req.getRequestDispatcher("index.html").include(req, resp);
			out.print("<br>");
			String fisrtname = req.getParameter("firstname");
			String lastname = req.getParameter("lastname");
			String dept = req.getParameter("department");
			String age = req.getParameter("age");
			String roll = req.getParameter("roll");

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/exam", "root", "");

			PreparedStatement state = con
					.prepareStatement("insert into student values(?,?,?,?,?)");

			state.setString(1, fisrtname);
			state.setString(2, lastname);
			state.setString(3, dept);
			state.setString(4, age);
			state.setString(5, roll);

			int r = state.executeUpdate();
			if (r > 0) {
				ResultSet rr = state.executeQuery("select *from student");

				out.print("<html>");
				while (rr.next()) {
					out.print(" " + rr.getString(1) + " " + rr.getString(2)
							+ " " + rr.getString(3) + " " + rr.getString(4)
							+ " " + rr.getString(5));
					out.print("<br>");
				}
				out.print("</html>");
			}
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
