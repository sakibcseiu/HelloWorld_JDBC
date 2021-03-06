package com.sakib.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Delete
 */
@WebServlet("/Delete")
public class Delete extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {

			
			PrintWriter out = resp.getWriter();
			req.getRequestDispatcher("index.html").include(req, resp);
			out.print("<br>");
			String roll = req.getParameter("roll");

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/exam", "root", "");

			PreparedStatement state=con.prepareStatement("delete from student where Roll=?");
			state.setString(1, roll);
			
			state.executeUpdate();			
				ResultSet rr = state.executeQuery("select *from student");

				out.print("<html>");
				while (rr.next()) {
					out.print(" " + rr.getString(1) + " " + rr.getString(2)
							+ " " + rr.getString(3) + " " + rr.getString(4)
							+ " " + rr.getString(5));
					out.print("<br>");
				}
				out.print("</html>");
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
