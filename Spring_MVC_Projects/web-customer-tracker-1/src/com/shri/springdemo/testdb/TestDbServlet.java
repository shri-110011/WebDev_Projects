package com.shri.springdemo.testdb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestDbServlet
 */
@WebServlet("/TestDbServlet")
public class TestDbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// setup connection variables
		String user = "springstudent", password = "springstudent",
				jdbcURL = "jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false&serverTimezone=UTC",
				driver = "com.mysql.cj.jdbc.Driver";

		// get connection to the database
		try {
			PrintWriter out = response.getWriter();

			out.println("Connecting to the database: " + jdbcURL);

			/*
			 * Class.forName(full qualified name of class) is required to load the class
			 * dynamically;
			 */
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(jdbcURL, user, password);

			out.println("Connection Successful!");

		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException();
		}

	}

}
