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

import sitemail.model.AnnouncementBean;
import sitemail.model.MailBean;
import sitemail.model.MemberBean;
import sitemail.model.SiteMailBean;
import sitemail.model.SiteMailService;

@WebServlet("/specificMailServlet.do")
public class SpecificMailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SiteMailService siteMailService;

	@Override
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);
		siteMailService = (SiteMailService) context.getBean("siteMailService");
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
		//以下兩行判斷是否有點選信件內容(因為jsp有傳值在網址列)
		String temp = request.getParameter("allMailsiteMailNo");
		String temp1 = request.getParameter("announceMailsiteMailNo");
		//判斷是否是按全部信件的垃圾桶
		String allMailBotton=request.getParameter("allMailBotton");
		//判斷是否是按未讀信件的垃圾桶
		String unReadMailBotton=request.getParameter("unReadMailBotton");
		
		String searchKeyWord = request.getParameter("searchKeyWord");
		String readStauts=request.getParameter("unRead");
		

		//查詢未讀信件的時候
		if(readStauts!=null){		
			login.model.MemberBean  bean=(login.model.MemberBean)session.getAttribute("loginToken");
			List<MailBean> allMail=siteMailService.selectUnReadMailByMemberNo(bean);
			request.setAttribute("allMail", allMail);
			List<AnnouncementBean> announceMail=siteMailService.selectUnReadAnnounceMail(bean.getMemberNo());
			request.setAttribute("announceMail", announceMail);
			request.getRequestDispatcher("/mail/mail.jsp").forward(request, response);
			return;
		}
		//這裡執行搜尋
		if (searchKeyWord != null) {
			login.model.MemberBean  bean=(login.model.MemberBean)session.getAttribute("loginToken");
			if(bean!=null){
				if(searchKeyWord.contains("系")||searchKeyWord.contains("統")||searchKeyWord.contains("公")||searchKeyWord.contains("告")){
					List<AnnouncementBean> announceMail=siteMailService.searchAnnounceMail(searchKeyWord, bean.getMemberNo());
					request.setAttribute("announceMail", announceMail);
				}
				List<MailBean> allMail = siteMailService.searchMail(searchKeyWord,bean.getMemberNo());
				request.setAttribute("allMail", allMail);
				//存這個表示有來查詢
				session.setAttribute("ueryToken", "ueryToken");
				request.getRequestDispatcher("/mail/sitemail.jsp").forward(request, response);
				return;
			}
		}
		// 這裡執行刪除全部公告信及全部狀態信
		if (allMailBotton!=null) {
			String[] allMailNo = request.getParameterValues("allMail");
			if (allMailNo != null) {
				for (String all : allMailNo) {
					int mailNo = Integer.parseInt(all);
					siteMailService.deleteMail(mailNo);
				}
			}
			String[] announceMailNo = request.getParameterValues("announceMail");
			if (announceMailNo != null) {
				for (String announce : announceMailNo) {
					System.out.println("announce="+announce);
					int announceNo = Integer.parseInt(announce);
					siteMailService.deleteAnnounceMail(announceNo);
				}
			}
			request.getRequestDispatcher("/overViewMailServlet.do").forward(request, response);
			return;
		}

		//執行刪除未讀公告信及未讀狀態信
		if(unReadMailBotton!=null){
			String[] allMailNo = request.getParameterValues("allMail1");
			if (allMailNo != null) {
				for (String all : allMailNo) {
					int mailNo = Integer.parseInt(all);
					siteMailService.deleteMail(mailNo);
				}
			}
			String[] announceMailNo = request.getParameterValues("announceMail1");
			if (announceMailNo != null) {
				for (String announce : announceMailNo) {
					System.out.println("announce="+announce);
					int announceNo = Integer.parseInt(announce);
					siteMailService.deleteAnnounceMail(announceNo);
				}
			}
			request.getRequestDispatcher("/overViewMailServlet.do").forward(request, response);
			return;
		}
		

		// 執行查詢信件
		if (temp != null) {
			Integer siteMailNo = Integer.parseInt(temp);
			MailBean mailBean = siteMailService.selectSpecificMail(siteMailNo);
			request.setAttribute("mailBean", mailBean);
			//將讀取狀態改為已讀
			SiteMailBean siteMailBean=new SiteMailBean();
			siteMailBean.setSiteMailNo(mailBean.getSiteMailNo());
			siteMailBean.setMemberNo(mailBean.getMemberNo());
			siteMailBean.setSiteMailCanNo(mailBean.getSiteMailCanNo());
			siteMailBean.setSiteMailTime(mailBean.getSiteMailTime());
			int updateNum=siteMailService.updateMailStatus(siteMailBean);
			request.getRequestDispatcher("/mail/specificmaiil.jsp").forward(request, response);
			return;
		}
		// 執行查詢信件
		if (temp1 != null) {
			Integer siteMailNo1 = Integer.parseInt(temp1);
			AnnouncementBean announcementBean = siteMailService.selectAnnounceDetail(siteMailNo1);
			request.setAttribute("announcementBean", announcementBean);
			//將讀取狀態改為已讀
			int updateNum=siteMailService.updateAnnounceMailStatus(announcementBean);
			System.out.println("updateNum="+updateNum);
			request.getRequestDispatcher("/mail/specificmaiil.jsp").forward(request, response);
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
