package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

import creategroup.model.GlobalService;
import creategroup.model.GroupInfoBean;
import creategroup.model.GroupInfoDetailsBean;
import creategroup.model.PicBean;
import creategroup.model.CreateGroupService;

@WebServlet("/createGroupServlet.do")
@MultipartConfig
public class createGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CreateGroupService creatGroupService;
	
	@Override
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);
		creatGroupService = (CreateGroupService) context.getBean("creatGroupService");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Map<String, String> errorMessages = new HashMap<String, String>();
		Map<String, String> successMessage = new HashMap<String, String>();
		HttpSession session = request.getSession();
		session.setAttribute("errorMessages", errorMessages);
		request.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html charset=UTF-8");
		session.setAttribute("successMessage", successMessage);
		/*
		 * getPart()
		 * 方法接受一個字串，代表著檔案上傳欄位的name屬性，getPart()方法上有著getHeader()、getInputStream()
		 * 等方法 ， 其中 getInputStream()可以取得代表上傳檔案區段的檔案內容之輸入串流。
		 */
		String groupName = "";
		int productTypeNo = 0;
		int productNum = 0;
		Timestamp deadLine = null;
		String productName = "";
		int countName = 0;
		double productPrice = 0;
		String productInfo = "";
		String bankAccount = "";
		String shippingWay = "";
		List<GroupInfoDetailsBean> detailList = null;
		List<PicBean> picList = null;
		Collection<Part> parts = request.getParts();
		PicBean picBean = null;
		if (parts != null) {
			picList = new ArrayList<PicBean>();
			for (Part p : parts) {
				String fieldName = p.getName();// 拿到<input
												// type="text"name="example">裡example的值
				// System.out.println(fieldName);
				String value = request.getParameter(fieldName);// 取出輸入值
				// System.out.println(value);
				if (p.getContentType() == null) {// 等於null表示不是圖片，如果是圖片ContentType應該像是image/jpeg此種檔案格式
					if ("groupName".equals(fieldName)) {
						if (value == null || value.trim().length() == 0) {
							errorMessages.put("groupNameError", "請輸入團名");
						} else {
							groupName = value;
						}
					} else if ("productType".equals(fieldName)) {
						if (value.equals("freshFood")) {
							productTypeNo = 1;
						} else if (value.equals("delicious")) {
							productTypeNo = 2;
						} else if (value.equals("makeUp")) {
							productTypeNo = 3;
						} else if (value.equals("clothes")) {
							productTypeNo = 4;
						} else if (value.equals("kid")) {
							productTypeNo = 5;
						} else if (value.equals("life")) {
							productTypeNo = 6;
						} else if (value.equals("relax")) {
							productTypeNo = 7;
						} else if (value.equals("technology")) {
							productTypeNo = 8;
						}
					} else if ("productNum".equals(fieldName)) {
						if (value == null || value.trim().length() == 0) {
							errorMessages.put("productNumError", "請輸入團購達標下限");
						} else {
							try {
								productNum = Integer.parseInt(value);
								if (productNum <= 0) {
									errorMessages.put("productNumError", "團購達標下限請大於0");
								} else {
								}
							} catch (NumberFormatException e) {
								errorMessages.put("productNumError", "請輸入數字");
							}
						}
					} else if ("deadLine".equals(fieldName)) {
						if (value == null || value.trim().length() == 0) {
							errorMessages.put("deadLineError", "請輸入團購截止日期");
						} else {
							String hours = request.getParameter("hours");
							String minutes = request.getParameter("minutes");
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
							String fullDateName = value + " " + hours + ":" + minutes;
							try {
								java.util.Date d = sdf.parse(fullDateName);

								long d1 = d.getTime();
								deadLine = new Timestamp(d1);
								System.out.println("宇呈Servlet印的截止日期="+deadLine);
							} catch (ParseException e) {
								errorMessages.put("deadLineError", "日期格式錯誤");
								e.printStackTrace();
							}

						}
					} else if ("productInfo".equals(fieldName)) {
						productInfo = value;
					} else if ("bankAccount".equals(fieldName)) {
						if (value == null || value.trim().length() == 0) {
							errorMessages.put("bankAccountError", "請輸入匯款帳號");
						} else {
							try {
								int i = Integer.parseInt(value);
							} catch (NumberFormatException e) {
								errorMessages.put("bankAccountError", "匯款帳號請輸入數字");
							}
							bankAccount = value;
						}
					} else if ("shippingWay".equals(fieldName)) {
						if (value.equals("post")) {
							shippingWay = "郵局";
						} else {
							shippingWay = "黑貓";
						}
					}
				} else {
					String fileName = GlobalService.getFileName(p);// 取得檔案檔名
					if (fileName != null && fileName.trim().length() != 0) {
						if ("cover".equals(fieldName)) {
							picBean = new PicBean();
							picBean.setFis((FileInputStream) p.getInputStream());
							picBean.setSize(p.getSize());
						} else {
							PicBean groupInfoPicBean = new PicBean();
							groupInfoPicBean.setSize(p.getSize());
							groupInfoPicBean.setFis((FileInputStream) p.getInputStream());
							picList.add(groupInfoPicBean);
						}
					} else {
						errorMessages.put("pictureError", "請挑選封面及產品圖片檔");
					}
				}

			}
		}

		String[] productNames = request.getParameterValues("productName");
		String[] productPrices = request.getParameterValues("productPrice");
		detailList = new ArrayList<GroupInfoDetailsBean>();
		for (int i = 0; i < productNames.length; i++) {
			GroupInfoDetailsBean groupInfoDetailsBean = new GroupInfoDetailsBean();
			if ((productNames[i].trim().length() == 0 || productNames[i] == null)
					|| ((productPrices[i].trim().length() == 0 || productPrices[i] == null))) {
				errorMessages.put("productError", "請輸入品項及價格");
				break;
			}
			groupInfoDetailsBean.setGroupInfoDetailsProdcutName(productNames[i]);
			try {
				double newPrice = Double.parseDouble(productPrices[i]);
				if (newPrice <= 0) {
					errorMessages.put("productPriceError", "價格請大於0");
				}
				groupInfoDetailsBean.setGroupInfoDetailsProductPrice(newPrice);
				detailList.add(groupInfoDetailsBean);
			} catch (NumberFormatException e) {
				errorMessages.put("productPriceError", "價格請輸入數值");
			}

		}
		String path = request.getContextPath();
		if (!errorMessages.isEmpty()) {
			response.sendRedirect(path + "/creategroup/createGroup.jsp");
			return;
		}
		GroupInfoBean groupInfoBean = new GroupInfoBean();
		groupInfoBean.setMemberNo(1);
		groupInfoBean.setMemberAccount("eeit9211@gmail.com");
		groupInfoBean.setGroupStatusNo(1);
		groupInfoBean.setProductTypeNo(productTypeNo);
		groupInfoBean.setGroupInfoName(groupName);
		groupInfoBean.setGroupInfoMinProductQt(productNum);
		groupInfoBean.setGroupInfoDeadLine(deadLine);
		groupInfoBean.setGroupInfoContent(productInfo);
		groupInfoBean.setGroupInfoShippingWay(shippingWay);
		groupInfoBean.setGroupInfoBankAccount(bankAccount);
		groupInfoBean.setGroupInfoCoverPic(picBean);
		groupInfoBean.setGroupInfoDetailsBean(detailList);
		groupInfoBean.setGroupInfoPicBean(picList);
		int insertNum = creatGroupService.createGroup(groupInfoBean, picList);
		System.out.println("insertNum=" + insertNum);
		if (insertNum != 0) {
			successMessage.put("createSuccess", "創團成功!!!");
			String path1 = request.getContextPath();
			response.sendRedirect(path + "/creategroup/successCreate.jsp");
			
			return;
		} else {
			response.sendRedirect(path + "/creategroup/successCreate.jsp");
			return;
		}
	}
}
