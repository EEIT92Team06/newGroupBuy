package controller;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.google.gson.Gson;

import login.model.MemberBean;
import sitemail.model.AnnouncementBean;
import sitemail.model.MailBean;
import sitemail.model.SiteMailBean;
import sitemail.model.SiteMailService;


@WebServlet("/mail/specificMailAjaxServlet.do")
public class SpecificMailAjaxServlet extends HttpServlet {
	private SiteMailService siteMailService;
	@Override
	public void init() throws ServletException {
      ServletContext application=this.getServletContext();
      ApplicationContext context=WebApplicationContextUtils.getWebApplicationContext(application);
      siteMailService=(SiteMailService)context.getBean("siteMailService");
	}

	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		Gson gson=new Gson();
        Map<String,MailBean>map=new HashMap<String,MailBean>();
        Map<String,AnnouncementBean>map1=new HashMap<String,AnnouncementBean>();
        Map<String,Integer>map2=new HashMap<String,Integer>();

        HttpSession session=request.getSession();
        MemberBean memberBean=(MemberBean)session.getAttribute("loginToken");
        String temp=request.getParameter("siteMailNo");
        String temp1=request.getParameter("announceSiteMailNo");
        String temp2=request.getParameter("unReadSiteMailNo");
        String temp3=request.getParameter("unReadannounceNo");
        System.out.println("temp3="+temp3);
        Integer siteMailNo=null;
        Integer announceSiteMailNo=null;
        Integer unReadSiteMailNo=null;
        Integer unReadannounceNo=null;
        if(temp!=null){
         siteMailNo=Integer.parseInt(temp);
        }
        if(temp1!=null){
         announceSiteMailNo=Integer.parseInt(temp1);
        }
        if(temp2!=null){
        	unReadSiteMailNo=Integer.parseInt(temp2);
        }
        if(temp3!=null){
        	unReadannounceNo=Integer.parseInt(temp3);
        }
        //查詢狀態信
        if(siteMailNo!=null){
        	MailBean mailBean=siteMailService.selectSpecificMail(siteMailNo);
        	map.put("mailBean", mailBean);
        	SiteMailBean siteMailBean=new SiteMailBean();
			siteMailBean.setSiteMailNo(mailBean.getSiteMailNo());
			siteMailBean.setMemberNo(mailBean.getMemberNo());
			siteMailBean.setSiteMailCanNo(mailBean.getSiteMailCanNo());
			siteMailBean.setSiteMailTime(mailBean.getSiteMailTime());
			int updateNum=siteMailService.updateMailStatus(siteMailBean);
        	out.println(gson.toJson(map));
        	out.close();
        }
        //查詢公告信
        if(announceSiteMailNo!=null){
        	AnnouncementBean announcementBean=siteMailService.selectAnnounceDetail(announceSiteMailNo);
        	map1.put("announcementBean", announcementBean);
			int updateNum=siteMailService.updateAnnounceMailStatus(announcementBean);
        	out.println(gson.toJson(map1));
        	out.close();
        }
        //查詢未讀狀態信
        if(unReadSiteMailNo!=null){
        	MailBean unReadAllMail=siteMailService.selectSpecificMail(unReadSiteMailNo);
        	map.put("unReadAllMail", unReadAllMail);
        	int unReadNum=siteMailService.getUnReadNum(memberBean);
        	map2.put("unReadNum", unReadNum);
        	SiteMailBean siteMailBean=new SiteMailBean();
			siteMailBean.setSiteMailNo(unReadAllMail.getSiteMailNo());
			siteMailBean.setMemberNo(unReadAllMail.getMemberNo());
			siteMailBean.setSiteMailCanNo(unReadAllMail.getSiteMailCanNo());
			siteMailBean.setSiteMailTime(unReadAllMail.getSiteMailTime());
			int updateNum=siteMailService.updateMailStatus(siteMailBean);
        	out.println(gson.toJson(map));
        	out.close();
        }
        //查詢未讀公告信
        if(unReadannounceNo!=null){
        	AnnouncementBean unReadAnnouncementBean=siteMailService.selectAnnounceDetail(unReadannounceNo);
        	int updateNum=siteMailService.updateAnnounceMailStatus(unReadAnnouncementBean);
        	map1.put("unReadAnnouncementBean", unReadAnnouncementBean);
        	System.out.println("unReadAnnouncementBean="+unReadAnnouncementBean);
        	out.println(gson.toJson(map1));
        	out.close();
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
