package com.emp.data;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/empdata")
public class EmpData extends HttpServlet {
	
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter pw = response.getWriter();
		pw.println("<br/><a href='empform.html'> CLICK ME TO GO c page  </a>");
		pw.println("<br/> <b><a href= 'index.html'>TO go back to main page click me</a></b>)");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			}
		catch(ClassNotFoundException e) {
			System.out.println("Error : "+e.getMessage());
		}
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dev","root","K3141Deva");
				Statement stmt = conn.createStatement();
				ResultSet rs= stmt.executeQuery("select*from emp_info");)
						{pw.println("<h1> Employee DETAILS </h1>");
						pw.println("<table border=1>");
						while(rs.next())
						{
							int stcode = rs.getInt("idemp");
							String name = rs.getString("name");
							String dept = rs.getString("department");
							String Exp = rs.getString("exp");
							String phonenumber = rs.getString("contact");
							String shortcut = rs.getString("shortcut");
							pw.println("<tr>");
							pw.println("<td>"+stcode+"</td>");
							pw.println("<td>"+name+"</td>");
							pw.println("<td>"+dept+"</td>");
							pw.println("<td>"+Exp+"</td>");
							pw.println("<td>"+phonenumber+"</td>");
							pw.println("<td>"+shortcut+"</td>");
							pw.println("</tr>");
									
							
						}
						pw.println("</table>");
					
						} catch (SQLException e) {
							// TODO Auto-generated catch block
						pw.println("e.printStackTrace()");
						}
		
	}

}
