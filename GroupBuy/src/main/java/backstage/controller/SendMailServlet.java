package backstage.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import insertAnnounce.model.AnnouncementBean;
import insertAnnounce.model.AnnouncementService;

@WebServlet("/Backstage/SendMailServlet")
public class SendMailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AnnouncementService announcementService;
	@Override
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);
		announcementService = (AnnouncementService)context.getBean("announceService");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("UTF-8");
		Map<String, String> errorMsg = new HashMap<String, String>();
		Map<String, String> successMsg = new HashMap<String, String>();
		request.setAttribute("errorMsg", errorMsg);
		request.setAttribute("successMsg", successMsg);
		
		String[] checkboxs = request.getParameterValues("checkbox");
		String title = request.getParameter("title");
		String siteMail = request.getParameter("siteMail");
		
		if(checkboxs == null || (title == null || title.trim().length() ==0) 
				||(siteMail == null || siteMail.trim().length() == 0)){
			errorMsg.put("errorMsg", "欄位不可為空");
		}
		if(!errorMsg.isEmpty()){
			RequestDispatcher rd = request.getRequestDispatcher("/Backstage/newbackstage1.jsp");
			rd.forward(request, response);
			return;
		}
		Timestamp time = new Timestamp(new Date().getTime());
		for(String checkbox : checkboxs){   

			AnnouncementBean anncounce = new AnnouncementBean(Integer.parseInt(checkbox), 9301, title, time, siteMail,"系統公告");
			System.out.println("announce : " + anncounce);
			announcementService.insertMsg(anncounce);
			successMsg.put("successMsg", "發送成功");
		}
		RequestDispatcher rd = request.getRequestDispatcher("/Backstage/newbackstage1.jsp");
		rd.forward(request, response);
		return;
		
//		System.out.println("checkboxs : " + checkboxs);
//		for(String checkbox : checkboxs){
//			System.out.println("checkbox : " + checkbox);
//		}
//		System.out.println("title : " + title);
//		System.out.println("siteMail : " + siteMail);
	}

}
