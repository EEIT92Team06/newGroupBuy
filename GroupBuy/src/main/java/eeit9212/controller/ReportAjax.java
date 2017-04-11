package eeit9212.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import eeit9212.model.ReportBean;
import eeit9212.model.ReportService;
import login.model.MemberBean;


@WebServlet("/reportajax")
public class ReportAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReportService reportService;

	@Override
	public void init() throws ServletException {
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		reportService = (ReportService) context.getBean("reportService");
	}
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session=request.getSession();
		MemberBean memberBean=(MemberBean)session.getAttribute("loginToken");
		Integer memberNo=memberBean.getMemberNo();
		String reportTypeNoTemp=request.getParameter("reportTypeNo");
		String reportContent=request.getParameter("reportContent");
		String reportTargetTemp=request.getParameter("reportTarget");
		System.out.println("memberNo="+memberNo);
		System.out.println("reportTypeNoTemp="+reportTypeNoTemp);
		System.out.println("reportContent="+reportContent);
		System.out.println("reportTargetTemp="+reportTargetTemp);
		
		int reportTypeNo=-1;
		if(reportTypeNoTemp!=null&&reportTypeNoTemp.length()!=0){
			try{
				reportTypeNo=Integer.parseInt(reportTypeNoTemp);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		int reportTarget=-1;
		if(reportTargetTemp!=null&&reportTargetTemp.length()!=0){
			try{
				reportTarget=Integer.parseInt(reportTargetTemp);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		ReportBean bean=new ReportBean();
		bean.setMemberNo(memberNo);
		bean.setReportTypeNo(reportTypeNo);
		bean.setReportStatusNo(9201);
		bean.setReportTarget(reportTarget);
		bean.setReportContent(reportContent);
		
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		if(reportService.report(bean)){
			out.print("檢舉成功，請靜待處理。");	
		}else{
			out.print("您的檢舉還在處理中，已經更新您的檢舉內容，請靜待處理。");
		}
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
