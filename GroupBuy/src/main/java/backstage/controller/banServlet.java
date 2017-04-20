package backstage.controller;

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

import insertAnnounce.model.AnnouncementBean;
import insertAnnounce.model.AnnouncementService;

@WebServlet("/Backstage/banServlet")
public class banServlet extends HttpServlet {
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
		String[] checkboxs = request.getParameterValues("checkbox");
		
		Map<String, String> errorMsg = new HashMap<String, String>();
		if(checkboxs==null){
			errorMsg.put("banGroup", "請選擇封鎖會員");
		}
		if(!errorMsg.isEmpty()){
			String contextPath = getServletContext().getContextPath();
			response.sendRedirect(response.encodeRedirectURL(contextPath+"/Backstage/newbackstage2.jsp"));
			return;
		}
		for(String checkbox : checkboxs){
			int result = announcementService.modifyMemStatus(Integer.parseInt(checkbox));
			System.out.println("成功 : " + result);
		}
		HttpSession session = request.getSession();
		List<Map<String, String>> Partmembers = announcementService.selectPartMem();
		session.setAttribute("Partmembers", Partmembers);
		String contextPath = getServletContext().getContextPath();
		response.sendRedirect(response.encodeRedirectURL(contextPath+"/Backstage/newbackstage2.jsp"));
		return;
	}

}
