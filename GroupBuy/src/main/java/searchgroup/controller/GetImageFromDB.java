package searchgroup.controller;

import java.io.*;
import java.sql.*;
import java.util.List;

import javax.naming.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.util.SerializationUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
//本類別會依據傳入的書籍編號(BookID)讀取eBook表格內CoverImage欄位內的圖片，
//然後傳回給提出請求的瀏覽器
@WebServlet("/searchImg/getImage")
public class GetImageFromDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SessionFactory sessionFactory;
	@Override
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);
		sessionFactory = (SessionFactory) context.getBean("sessionFactory");
	}


	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("image/jpeg");
		Connection conn = null;
		OutputStream os = null;
		InputStream is = null;
		try {
			// 讀取瀏覽器傳送來的團id
			String id = request.getParameter("id");
			String type = request.getParameter("type");
			System.out.println("type = " + type);
			System.out.println("id = " + id);
			
			String sql = "";
			if("groupCover".equals(type)){
				sql = "select groupInfo_CoverPic from groupInfo where groupInfo_No = ?";
				Session session = sessionFactory.getCurrentSession();
				NativeQuery query = session.createNativeQuery(sql);
				query.setParameter(1, id);
				byte[] result = (byte[])query.getSingleResult();
			    is = new ByteArrayInputStream(result);			
			}else if("groupPhoto".equals(type)){  
				sql = "select groupInfoPic_ProductPic from groupInfoPic where groupInfoPic_No = ?";
				Session session = sessionFactory.getCurrentSession();
				NativeQuery query = session.createNativeQuery(sql);
				query.setParameter(1, id);
				byte[] result = (byte[])query.getSingleResult();
			    is = new ByteArrayInputStream(result);	
			}else{
				System.out.println("something wrong");
			}
			os = response.getOutputStream();				
			int count = 0;
			byte[] bytes = new byte[8192];
			while ((count = is.read(bytes)) != -1) {
				os.write(bytes, 0, count);
			}
				
		} finally {
			if (os != null) {
				os.close();
			}
		}
	}
}