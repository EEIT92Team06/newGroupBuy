package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

@WebServlet("/wish/wishsearch.controller")
public class WishSearchServlet extends HttpServlet {

	private WishPoolService wishPoolService;
	@Override
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);
		wishPoolService = (WishPoolService)context.getBean("wishPoolService");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		 request.setCharacterEncoding("UTF-8");
		 response.setContentType("text/html;charset=UTF-8");
		 Map<String,String>errorMsg = new HashMap<String,String>();
		 request.setAttribute("errorMsg",errorMsg);
		 //接收資料
		 String productType = request.getParameter("productType");
		 System.out.println("===============================");
		 System.out.println(productType);
		 System.out.println("===============================");
		 //轉換資料
		 int pType=0;
		 try {
			pType = Integer.parseInt(productType);
		} catch (NumberFormatException e) {
			errorMsg.put("pType", "請選擇搜尋類別!");
		}
		 if(errorMsg!=null && !errorMsg.isEmpty()){
			 request.getRequestDispatcher("/wish/wishpool.jsp").forward(request, response);
			 return;
		 }
		// 呼叫model, 根據Model執行結果呼叫View
			List<WishPoolBean> search = wishPoolService.search(pType);
			if (search != null) {
				request.setAttribute("wishCollection", search);
				request.getRequestDispatcher("/wish/wishpool.jsp").forward(request, response);
				return;
			}
		

	}
}
