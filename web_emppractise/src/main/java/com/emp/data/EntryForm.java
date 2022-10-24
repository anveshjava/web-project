package com.emp.data;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/entryservlet")
public class EntryForm extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter pw = response.getWriter();
		String date = request.getParameter("date");
		String number = request.getParameter("id_value");
		String fullname = request.getParameter("full_name");
		String dept = request.getParameter("dept_text");
		String exp = request.getParameter("exp_value");
		String phonenumber = request.getParameter("phone");
		String shortcut = request.getParameter("short_value");
		pw.println("<b> Date  : </b>" + date + "<br/>");
		pw.println("<b> Employee ID is :" + number + "<br/>");
		pw.println("Employee full name :-" + fullname + "<br/>");
		pw.println("Employee department :-" + dept + "<br/>");
		pw.println("Employee has an Experience:- "+exp+ "<br/>");
		pw.println("Employee phone number is  :-" + phonenumber + "<br/>");
		pw.println("Employee short cut number :-" + shortcut+ "<br/>");
		int id = Integer.parseInt(number);
try {
			Class.forName("com.mysql.cj.jdbc.Driver");

		} catch (ClassNotFoundException e) {
			pw.println("Error from the class loading " + e);

		}
		try( Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dev","root","K3141Deva");
				PreparedStatement pst = conn.prepareStatement("insert into emp_info values (?,?,?,?,?,?)");){
			
			pst.setInt(1, id);
			pst.setString(2, fullname);
			pst.setString(3,dept);
			pst.setString(4,exp );
			pst.setString(5,phonenumber );
			pst.setString(6, shortcut);
			pst.addBatch();
			pst.executeBatch();
			
			pw.println("Data updated to database");
		} 
		
		catch (SQLException e) {
			
			pw.println("Error from the connection "+e);
		}
		
	}
}
