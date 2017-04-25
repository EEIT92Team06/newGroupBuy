package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import login.model.MemberBean;
import sitemail.model.AnnouncementBean;
import sitemail.model.MailBean;
import sitemail.model.SiteMailBean;
import sitemail.model.SiteMailCanBean;
import sitemail.model.SiteMailService;

@WebServlet("/overViewMailServlet.do")
public class OverViewMailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SiteMailService siteMailService;

	@Override
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);
		siteMailService = (SiteMailService) context.getBean("siteMailService");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("text/html;UTF-8");
		HttpSession session = request.getSession(false);
		if (session == null) {
			String path = request.getContextPath();
			response.sendRedirect(path + "/secure/login.jsp");
			return;
		}

		MemberBean memberBean = (MemberBean) session.getAttribute("loginToken");
		// 查詢全部信件
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		List<MailBean> allMail = siteMailService.selectMailByMemberNo(memberBean);
		List<String> allMailTime = new ArrayList<String>();
		for(int i=0;i<allMail.size();i++){
			String temp = format.format(allMail.get(i).getSiteMailTime());
			allMailTime.add(temp);
		}
		session.setAttribute("allMailTime", allMailTime);
		session.setAttribute("allMail", allMail);
		List<AnnouncementBean> announceMail = siteMailService.selectAnnounceMail(memberBean.getMemberNo());
		List<String> announceMailTime = new ArrayList<String>();
		for(int i=0;i<announceMail.size();i++){
			String temp1 = format.format(announceMail.get(i).getSiteMailTime());
			announceMailTime.add(temp1);
		}
		session.setAttribute("announceMailTime", announceMailTime);
		session.setAttribute("announceMail", announceMail);
		String path = request.getContextPath();

		// 查詢未讀信件
		List<MailBean> unReadMail = siteMailService.selectUnReadMailByMemberNo(memberBean);
		List<String> unReadMailTime = new ArrayList<String>();
		for(int i=0;i<unReadMail.size();i++){
			String temp1 = format.format(unReadMail.get(i).getSiteMailTime());
			unReadMailTime.add(temp1);
		}
		session.setAttribute("unReadMailTime", unReadMailTime);
		session.setAttribute("unReadMail", unReadMail);
		
		List<AnnouncementBean> unReadannounceMail = siteMailService.selectUnReadAnnounceMail(memberBean.getMemberNo());
		List<String> unReadannounceMailTime = new ArrayList<String>();
		for(int i=0;i<unReadannounceMail.size();i++){
			String temp1 = format.format(unReadannounceMail.get(i).getSiteMailTime());
			unReadannounceMailTime.add(temp1);
		}
		session.setAttribute("unReadannounceMailTime", unReadannounceMailTime);
		session.setAttribute("unReadannounceMail", unReadannounceMail);
		response.sendRedirect(path + "/mail/sitemail.jsp");
		return;

	}

}
