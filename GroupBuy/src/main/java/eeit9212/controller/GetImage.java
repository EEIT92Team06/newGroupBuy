package eeit9212.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import eeit9212.model.AttendGroupInfoBean;
import eeit9212.model.GroupInfoService;

@WebServlet("/eeit9212/getimage")

public class GetImage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private GroupInfoService groupInfoService;

	@Override
	public void init() throws ServletException {
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		groupInfoService=(GroupInfoService)context.getBean("groupInfoService");
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		String groupInfoNoTemp = request.getParameter("groupInfoNo");
		String groupInfoPicNoTemp = request.getParameter("groupInfoPicNo");
		
		Blob picbyte=null;
		int groupInfoNo = -1;
		if (groupInfoNoTemp != null && groupInfoNoTemp.length() != 0) {
			try {
				groupInfoNo = Integer.parseInt(groupInfoNoTemp);
				picbyte = groupInfoService.selectGroupInfoByGroupInfoNo(groupInfoNo).getGroupInfoCoverPic();
			} catch (NumberFormatException ex) {
				ex.printStackTrace();
				return;
			}
		}
		int groupInfoPicNo=-1;
		if(groupInfoPicNoTemp!= null && groupInfoPicNoTemp.length() != 0){
			try{
			groupInfoPicNo= Integer.parseInt(groupInfoPicNoTemp);
			System.out.println("groupInfoPicNo="+groupInfoPicNo);
			picbyte =groupInfoService.selectGroupInfoPicByNo(groupInfoPicNo).getGroupInfoPicProductPic();
			}catch(NumberFormatException ex){
				ex.printStackTrace();
				return;
			}
		}

		InputStream is = null;
		OutputStream os = null;
		 response.setContentType("image/jpeg");
		try {
			is = picbyte.getBinaryStream();
			os = response.getOutputStream();
			byte[] b = new byte[8192];
			int len = 0;
			while ((len = is.read(b)) != -1) {
				os.write(b, 0, len);
			}

		} catch (Exception ex) {
			throw new ServletException(ex);
		} finally {
			if (is != null) {
				is.close();
			}
			if (os != null) {
				os.close();
			}
		}

	}

}
