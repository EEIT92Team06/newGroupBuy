package controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import registry.model.RegistryService;


@WebServlet("/finishedRegistryServlet.do")
public class FinishedRrgistryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private RegistryService registryService;

	@Override
	public void init() throws ServletException {
      ServletContext application=this.getServletContext();
      ApplicationContext context=WebApplicationContextUtils.getWebApplicationContext(application);
       registryService=(RegistryService)context.getBean("registryService");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("UTF-8");
      response.setContentType("UTF-8");
	  String memberAccount=request.getParameter("memberAccount");
	  int updateNum = registryService.updateStatus(memberAccount);
	  System.out.println("updateNum="+updateNum);
	  if(updateNum==1){
		  String path=request.getContextPath();
		  response.sendRedirect(path+"/secure/indexFromEmail.jsp");//此網頁是專門給email點選驗證信後的首頁，之後會跳轉到正確的首頁
		  return;
	  }
	  
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
	}

}
