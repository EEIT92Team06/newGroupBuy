package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
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
import wish.model.WishPictureBean;
import wish.model.WishPictureService;
import wish.model.WishPoolBean;
import wish.model.WishPoolService;

@WebServlet("/wish/wishform.controller")
@MultipartConfig(location = "C:/temp")
public class WishFormServlet extends HttpServlet {
	Integer memberNo;
	private WishPoolService wishPoolService;
    private WishPictureService wishPictureService;
	@Override
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);
		wishPoolService = (WishPoolService) context.getBean("wishPoolService");
		wishPictureService = (WishPictureService)context.getBean("wishPictureService");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String picNo = request.getParameter("picNo");
		//System.out.println("picNo : "+picNo);
		HttpSession session = request.getSession();
		session.setAttribute("picNo", picNo);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Map<String, String> errorMsg = new HashMap<String, String>();
		request.setAttribute("errorMsg", errorMsg);
		// 接收資料
		HttpSession session = request.getSession();
		MemberBean member = (MemberBean) session.getAttribute("loginToken");// 取得MemberNo

		memberNo = member.getMemberNo(); //Integer
		String title = request.getParameter("title");
		String type = request.getParameter("productType");
		String name = request.getParameter("name");
		String price = request.getParameter("price");
		String source = request.getParameter("source");
		String content = request.getParameter("content");
		String send = request.getParameter("send");
		//String productType = request.getParameter("productType");
        //接收封面資料
		//把使用者上傳的圖片存入陣列
		ArrayList<String> pictures = new ArrayList<String>();
		for (Part part : request.getParts()) {
			//把不是"picUpload"的物件都擋掉
			if ("picUpload".equals(part.getName())) {
				//如果沒上傳圖片,陣列會塞入空值""，所以要擋掉
				if (part.getSubmittedFileName() != "") {
					//替所有圖片加入會員編號及時間，避免檔案同名造成覆蓋
					String picture = memberNo.toString() + new SimpleDateFormat("yyyyMMddHH").format(new Date()) + part.getSubmittedFileName();
					pictures.add(picture);
				}
			}
		}
		//從session取得使用者選取哪一張圖片作為封面
		String picNo = null;
		try {
			picNo = (String) session.getAttribute("picNo");
		} catch (Exception e) {
			errorMsg.put("upload", "");
		}
		String cover = null;
		if (picNo != null && picNo.trim().length() != 0) {
			try {
				int picIndex = Integer.parseInt(picNo);
				cover = pictures.get(picIndex);
			} catch (NumberFormatException e) {
				errorMsg.put("upload", "請上傳圖片，並點擊縮圖選取封面<br>(預設封面為第一張圖)");
			}
		}else if(picNo==null && !(pictures.isEmpty())){
			//如果使用者有上傳圖檔，預設使用者上傳的第一張圖片作為封面
			try { 
				cover = pictures.get(0);
			} catch (NumberFormatException e) {
				errorMsg.put("upload", "請上傳圖片，並點擊縮圖選取封面<br>(預設封面為第一張圖)");
			}
		}
		session.removeAttribute("picNo");

		

		// 驗證資料
		if ("送出".equals(send)) {
			if (title == null || title.trim().length() == 0) {
				errorMsg.put("title", "請輸入標題");
			}
			if ("0".equals(type) ) {
				errorMsg.put("type", "請選擇產品類別");
			}
			if (name == null || name.trim().length() == 0) {
				errorMsg.put("name", "請輸入產品名稱");
			}
			if (price == null || price.trim().length() == 0) {
				errorMsg.put("price", "請輸入產品價格");
			}
			if (content == null || content.trim().length() == 0) {
				errorMsg.put("content", "簡短介紹一下這項產品吧!");
			}
			if (pictures == null || pictures.size() == 0) {
				errorMsg.put("upload", "請上傳圖片");
			}
		}
		// 轉換資料
		int pType = 0;
		if (type != null && type.trim().length() != 0) {
			try {
				pType = Integer.parseInt(type);
			} catch (NumberFormatException e) {
				errorMsg.put("type", "請選擇產品類別");
			}
		}
		double productPrice = 0;
		if (price != null && price.trim().length() != 0) {
			try {
				productPrice = Double.parseDouble(price);
			} catch (NumberFormatException e) {
				errorMsg.put("price", "請輸入數字");
			}
		}
		
		if (errorMsg != null && !errorMsg.isEmpty()) {
			RequestDispatcher rd = request.getRequestDispatcher("/wish/wishform.jsp");
			rd.forward(request, response);
			return;
		}
		// 呼叫model, 根據Model執行結果呼叫View
		WishPoolBean bean = new WishPoolBean();
		bean.setMemberNo(memberNo);
		bean.setTitle(title);
		bean.setProductType(pType);
		bean.setProductName(name);
		bean.setPrice(productPrice);
		bean.setSource(source);
		bean.setContent(content);
		bean.setCoverPic(cover);
        
		if ("送出".equals(send)) {
			Boolean wishInsert = wishPoolService.insert(bean);
			if (wishInsert == true) {
				for (Part part : request.getParts()) {
					if ("picUpload".equals(part.getName())) {
						//取得/pictures資料夾的RealPath
						String realPath = this.getServletContext().getRealPath("/pictures");
						write(part,realPath);
					}
				}
				if (pictures != null) {
					
					WishPoolBean wishPoolBean = new WishPoolBean();
					for (int i = 0; i < pictures.size(); i++) {
						WishPictureBean picBean = new WishPictureBean();
						System.out.println(666);
						wishPoolBean.setWishNo(wishPoolService.getWishNo(cover));
						picBean.setWishPoolBean(wishPoolBean);
                        picBean.setWishPicture(pictures.get(i));
						wishPictureService.insertPic(picBean);
					}
				}
				response.sendRedirect(request.getContextPath() + "/wish/wishpool.controller");
				return;
			} else {
				errorMsg.put("fail", "系統忙碌中，請5分鐘後後再次許願");
				request.getRequestDispatcher("/wish/wishform.jsp").forward(request, response);
				return;
			}
        }
	}
	
	private void write(Part part,String realPath) throws IOException , FileNotFoundException {
		//替所有圖片加入會員編號及時間，避免檔案同名造成覆蓋
		String fileName = memberNo.toString() + new SimpleDateFormat("yyyyMMddHH").format(new Date()) + part.getSubmittedFileName();
        part.write(realPath+File.separator+fileName);
	}
}
