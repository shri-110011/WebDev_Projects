package com.shri.ecommercebackend.testredis;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import redis.clients.jedis.Jedis;

@WebServlet("/TestRedisServlet")
public class TestRedisConnection extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String redisHost = "localhost"; // replace with the WSL2 IP address
        int redisPort = 6379;
        
		try {
			PrintWriter out = response.getWriter();
			
			Jedis jedis = new Jedis(redisHost, redisPort);
			
			String key = "availableQuantity", value = "1234";

	        jedis.set(key, value);
	        out.println("For key: " + key + " got value: " + jedis.get("availableQuantity"));

	        jedis.close();
	        
			out.println("Redis Server Connection Successful!");
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServletException();
		}
		
		
	}

}
