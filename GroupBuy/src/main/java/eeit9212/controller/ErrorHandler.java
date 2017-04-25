package eeit9212.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/errorhandler")
public class ErrorHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer statusCode=(Integer)request.getAttribute("javax.servlet.error.status_code");
		String requestUri=(String)request.getAttribute("javax.servlet.error.request_uri");	
		
		request.setAttribute("requestUri", requestUri);
		request.setAttribute("statusCode", statusCode);

		System.out.println("status_code="+statusCode);
		System.out.println("message="+request.getAttribute("javax.servlet.error.message"));
		System.out.println("exception_type="+request.getAttribute("javax.servlet.error.exception_type"));
		System.out.println("exception="+request.getAttribute("javax.servlet.error.exception"));
		System.out.println("request_uri="+requestUri);
		System.out.println("servlet_name="+request.getAttribute("javax.servlet.error.servlet_name"));
			
		RequestDispatcher rd = request.getRequestDispatcher("/errorpage.jsp");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
