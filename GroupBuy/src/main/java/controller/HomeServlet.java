package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Home
 */
@WebServlet("/home.controller")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public HomeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		// homepage(login)
		String loginButton = request.getParameter("loginButton");
		String loginMemberNo1 = request.getParameter("loginMemberNo");
		
		int loginMemberNo = -1;
		if ("Login".equals(loginButton)) {
			if (loginMemberNo1 != null && loginMemberNo1.length() != 0) {
				try {
					loginMemberNo = Integer.parseInt(loginMemberNo1);
					session.setAttribute("myMemberNo", loginMemberNo); // session存login的memberNo
					session.setAttribute("myPic", "eeit9211@gmail.com.jpg");
				} catch (NumberFormatException e) {
					System.out.println("loginMemberNo error-----------");
				}
			}
			RequestDispatcher rd = request.getRequestDispatcher("/test.jsp");
			rd.forward(request, response);
			return;

		}
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doGet(request, response);
	}

}
