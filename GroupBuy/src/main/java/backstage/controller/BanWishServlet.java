package backstage.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import insertAnnounce.model.AnnouncementService;
import searchgroup.model.SearchService;
import wish.model.WishPoolBean;
import wish.model.WishPoolService;

@WebServlet("/Backstage/BanWishServlet")
public class BanWishServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private WishPoolService wishPoolService;

	@Override
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);
		wishPoolService = (WishPoolService) context.getBean("wishPoolService");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("UTF-8");
		String[] checkboxs = request.getParameterValues("checkbox");

		Map<String, String> errorMsg = new HashMap<String, String>();
		if (checkboxs == null) {
			errorMsg.put("banGroup", "請選擇封鎖許願");
		}
		if(!errorMsg.isEmpty()){
			RequestDispatcher rd = request.getRequestDispatcher("/Backstage/newwishbackstage.jsp");
			rd.forward(request, response);
			return;
		}
		for (String checkbox : checkboxs) {
			wishPoolService.delete(Integer.parseInt(checkbox));
		}

		List<WishPoolBean> AllWish = wishPoolService.select(null);
		HttpSession session = request.getSession();
		session.setAttribute("AllWish", AllWish);
		String contextPath = getServletContext().getContextPath();
		response.sendRedirect(response.encodeRedirectURL(contextPath + "/Backstage/newwishbackstage.jsp"));
		return;
	}

}