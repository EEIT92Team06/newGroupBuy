package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import login.model.MemberBean;
import registry.model.RegistryService;

@WebServlet("/registryServlet.do")
@MultipartConfig(location = "C:/temp")
public class RegistryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RegistryService registryService;

	@Override
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);
		registryService = (RegistryService) context.getBean("registryService");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("UTF-8");
		Map<String, String> errorMessages = new HashMap<String, String>();
		HttpSession session = request.getSession();
		session.setAttribute("errorMessages", errorMessages);
		// 抓資料
		String temp1 = request.getParameter("memberAccount");
		String memberAccount = "";
		String temp2 = request.getParameter("memberPassword");
		String memberPassword = "";
		String temp3 = request.getParameter("checkPassword");
		String checkPassword = "";
		String temp4 = request.getParameter("memberName");
		String memberName = "";
		String temp5 = request.getParameter("memberNickName");
		String memberNickName = "";
		String cityName = request.getParameter("cityName");
		String address = request.getParameter("address");
		String memberAddress = cityName + address;
		String temp6 = request.getParameter("memberBirth");
		java.util.Date memberBirth = null;
		String temp7 = request.getParameter("memberPhone");
		String memberPhone = "";
		String pictureName = "";
		int num = 0;
		// 判斷是否為空
		if (temp1 == null || temp1.trim().length() == 0) {
			errorMessages.put("memberAccount", "請輸入電子郵件");
		}
		if (temp2 == null || temp2.trim().length() == 0) {
			errorMessages.put("memberPassword", "請輸入密碼");
		}
		if (temp3 == null || temp3.trim().length() == 0) {
			errorMessages.put("checkPassword", "請確認密碼");
		}
		if (temp4 == null || temp4.trim().length() == 0) {
			errorMessages.put("memberName", "請輸入姓名");
		}
		if (address == null || address.trim().length() == 0) {
			errorMessages.put("memberAddress", "請輸入地址");
		}
		if (temp6 == null || temp6.trim().length() == 0) {
			errorMessages.put("memberBirth", "請輸入生日");
		}
		if (temp7 == null || temp7.trim().length() == 0) {
			errorMessages.put("memberPhone", "請輸入手機號碼");
		}
		if (!errorMessages.isEmpty()) {
			String path = request.getContextPath();
			response.sendRedirect(path + "/secure/newRegistry.jsp");
			return;
		}
		// 判斷格式
		if (temp1.indexOf("@") == -1) {
			errorMessages.put("memberAccount", "電子郵件格式錯誤");
		} else {
			memberAccount = temp1;
		}
		if (temp2.length() < 8 || temp2.length() > 16) {
			errorMessages.put("memberPassword", "密碼請介於8碼到16碼之間");
		} else {
			memberPassword = temp2;
		}
		if (!temp3.equals(memberPassword)) {
			errorMessages.put("checkPassword", "請輸入一致的密碼");
		} else {
			checkPassword = temp3;
		}
		if (temp4.length() > 10) {
			errorMessages.put("memberName", "請輸入正確的姓名");
		} else {
			memberName = temp4;
		}
		if (temp5.length() > 20) {
			errorMessages.put("memberNickName", "暱稱請勿大於10個字");
		} else {
			memberNickName = temp5;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			java.util.Date d = sdf.parse(temp6);
			memberBirth = d;
		} catch (ParseException e) {
			errorMessages.put("memberBirth", "生日格式應為yyyy-MM-dd");
			e.printStackTrace();
		}
		if (temp7.length() > 15) {
			errorMessages.put("memberPhone", "號碼長度過長");
		} else {
			// 有問題
			// try {
			// num = Integer.parseInt(temp7);
			memberPhone = temp7;
			// System.out.println(memberPhone);
			// System.out.println(num);
			// } catch (NumberFormatException e) {
			// errorMessages.put("memberPhone", "電話格式錯誤");
			// }
		}
		// 照片處理

		try {
			for (Part part : request.getParts()) {
				// 把不是"picUpload"的物件都擋掉
				if ("memberPic".equals(part.getName())) {
					// 如果沒上傳圖片,陣列會塞入空值""，所以要擋掉
					if (part.getSubmittedFileName() != "") {
						// 替所有圖片加入會員編號及時間，避免檔案同名造成覆蓋
						pictureName = new SimpleDateFormat("yyyyMMddHH").format(new Date())
								+ part.getSubmittedFileName();
						String realPath = this.getServletContext().getRealPath("/pictures");
						System.out.println("realPath=" + realPath);
						write(part, realPath);
					}
				}
			}
		} catch (Exception e) {
			errorMessages.put("memberPic", "請上傳大頭貼");
		}
		if (!errorMessages.isEmpty()) {
			String path = request.getContextPath();
			response.sendRedirect(path + "/secure/newRegistry.jsp");
			return;
		}

		// 呼叫麻豆
		MemberBean memberBean = new MemberBean();
		memberBean.setMemberAccount(memberAccount);
		memberBean.setMemberPassword(memberPassword);
		memberBean.setMemberName(memberName);
		memberBean.setMemberNickName(memberNickName);
		memberBean.setMemberAddress(memberAddress);
		memberBean.setMemberBirth(memberBirth);
		memberBean.setMemberPhone(memberPhone);
		memberBean.setMemberPic(pictureName);
		memberBean.setMemberStatus(9100);
		MemberBean memberBean1 = registryService.addMember(memberBean);
		if (memberBean1 == null) {
			errorMessages.put("memberAccount", "帳號已經申請過");
			String path = request.getContextPath();
			response.sendRedirect(path + "/secure/newRegistry.jsp");
			return;
		}
		int sendMailNum=registryService.sendRegistryMail(memberAccount);
		System.out.println("sendMailNum="+sendMailNum);
		session.setAttribute("registryTokenAccount", memberBean1);
		System.out.println("memberBean="+memberBean);
		String path = request.getContextPath();
		response.sendRedirect(path + "/secure/sendregistryemail.jsp");
		return;
	}

	private void write(Part part, String realPath) throws IOException, FileNotFoundException {
		// 替所有圖片加入會員編號及時間，避免檔案同名造成覆蓋
		String fileName = new SimpleDateFormat("yyyyMMddHH").format(new Date()) + part.getSubmittedFileName();
		System.out.println("fileName=" + fileName);
		System.out.println("realPath+File.separator+fileName="+realPath+File.separator+fileName);
		part.write(realPath+File.separator+fileName);
	}
}
