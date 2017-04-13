package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import wish.model.WishPoolBean;
import wish.model.WishPoolService;

@WebServlet("/wish/wishpool.controller")
public class WishPoolServlet extends HttpServlet {
     private WishPoolService wishPoolService;
	@Override
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		ApplicationContext context = 
				WebApplicationContextUtils.getWebApplicationContext(application);
		wishPoolService = (WishPoolService)context.getBean("wishPoolService");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		//取得資料
		WishPoolBean bean = null;
		List<WishPoolBean> select =  wishPoolService.select(bean);
		if(select!=null){
			HttpSession session = request.getSession();
			session.setAttribute("wishCollection", select);
			response.sendRedirect(request.getContextPath()+"/wish/wishpool2.jsp");
			return;
		}
	}
	
	
     
     
}
