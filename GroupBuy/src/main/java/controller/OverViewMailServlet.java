package controller;

import java.io.IOException;
import java.util.ArrayList;
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
      ServletContext application=this.getServletContext();
      ApplicationContext context=WebApplicationContextUtils.getWebApplicationContext(application);
      siteMailService=(SiteMailService)context.getBean("siteMailService");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("text/html;UTF-8");
        HttpSession session=request.getSession(false);
        if(session==null){
        	String path=request.getContextPath();
        	response.sendRedirect(path+"/secure/login.jsp");
        	return;
        }
        
        MemberBean memberBean=(MemberBean)session.getAttribute("loginToken");
        List<MailBean> allMail=siteMailService.selectMailByMemberNo(memberBean);
        request.setAttribute("allMail", allMail);
        List<AnnouncementBean> announceMail = siteMailService.selectAnnounceMail(memberBean.getMemberNo());
        request.setAttribute("announceMail", announceMail);
        request.getRequestDispatcher("/mail/mail.jsp").forward(request, response);
        return;
	}

}
