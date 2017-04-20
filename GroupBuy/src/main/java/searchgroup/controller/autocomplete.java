package searchgroup.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import searchgroup.model.dao.autocompleteDAO;


@WebServlet("/searchgroup/auto")
public class autocomplete extends HttpServlet{
	private static autocompleteDAO autocompleteDAO;
	private static String [] x=null;
	 @Override
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);
		autocompleteDAO = (autocompleteDAO)context.getBean("autocompleteDAO");
		
	}
	
	    private static final long serialVersionUID = 1L;	
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        response.setContentType("text/html");
	        response.setHeader("Cache-control", "no-cache, no-store");
	        response.setHeader("Pragma", "no-cache");
	        response.setHeader("Expires", "-1");
	        
	        JSONArray arrayObj=new JSONArray();
	        
	        String query = request.getParameter("term");
	        System.out.println(query);
	        query = query.toLowerCase();
	        x=autocompleteDAO.take(query);
	        
	        for(int i=0; i<x.length; i++) {
//	            String country = x[i].toLowerCase();
//	            if(country.startsWith(query)) {
	                arrayObj.add(x[i]);
//	            }
	        }
	        
	        out.println(arrayObj.toString());
	        out.close();    
	    }
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        doGet(request, response);      
	    }
	
	
	
}
