package controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

import creategroup.model.CreateGroupService;
import creategroup.model.GlobalService;
import creategroup.model.GroupInfoBean;
import creategroup.model.GroupInfoDetailsBean;
import creategroup.model.GroupInfoPicBean;
import login.model.MemberBean;
import sitemail.model.SiteMailService;
import wish.model.WishInterestBean;
import wish.model.WishInterestService;
import wish.model.WishPoolBean;
import wish.model.WishPoolService;

@WebServlet("/createGroupServlet.do")
@MultipartConfig
public class CreateGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CreateGroupService createGroupService;
    private WishPoolService wishPoolService;
    private WishInterestService wishInterestService;
    private SiteMailService siteMailService;
	@Override
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);
		createGroupService = (CreateGroupService) context.getBean("createGroupService");
		wishPoolService = (WishPoolService) context.getBean("wishPoolService");
		wishInterestService = (WishInterestService) context.getBean("wishInterestService");
		siteMailService = (SiteMailService)context.getBean("siteMailService");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
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
		byte[] coverPicData = null;
		byte[] productPic = null;
		Set<GroupInfoDetailsBean> detailList = null;
		Set<GroupInfoPicBean> picList = null;
		Collection<Part> parts = request.getParts();
		GroupInfoBean groupInfoBean = new GroupInfoBean();

		if (parts != null) {
			detailList = new HashSet<GroupInfoDetailsBean>();
			picList = new HashSet<GroupInfoPicBean>();
			for (Part p : parts) {
				String fieldName = p.getName();// 拿到<input
												// type="text"name="example">裡example的值
				// System.out.print(fieldName+",");
				String value = request.getParameter(fieldName);// 取出輸入值
				// System.out.print(value);
				// System.out.println("");
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
								System.out.println(deadLine);
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
								long i = Long.parseLong(value);
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
							InputStream is = p.getInputStream();
							coverPicData = new byte[is.available()];
							is.read(coverPicData);

						} else {
							InputStream is = p.getInputStream();
							productPic = new byte[is.available()];
							is.read(productPic);
							GroupInfoPicBean groupInfoPicBean = new GroupInfoPicBean();
							groupInfoPicBean.setGroupInfoNo(groupInfoBean);
							groupInfoPicBean.setGroupInfoPicProductPic(productPic);
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
		detailList = new HashSet<GroupInfoDetailsBean>();
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
				groupInfoDetailsBean.setGroupInfoNo(groupInfoBean);
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
		MemberBean bean=(MemberBean)session.getAttribute("loginToken");
		groupInfoBean.setMemberNo(bean.getMemberNo());
		groupInfoBean.setGroupStatusNo(1);
		groupInfoBean.setProductTypeNo(productTypeNo);
		groupInfoBean.setGroupInfoName(groupName);
		groupInfoBean.setGroupInfoMinProductQt(productNum);
		java.util.Date start = new java.util.Date();
		long d = start.getTime();
		Timestamp time = new Timestamp(d);
		groupInfoBean.setGroupInfoStartDate(time);
		groupInfoBean.setGroupInfoDeadLine(deadLine);
		groupInfoBean.setGroupInfoContent(productInfo);
		groupInfoBean.setGroupInfoShippingWay(shippingWay);
		groupInfoBean.setGroupInfoBankAccount(bankAccount);
		groupInfoBean.setGroupInfoCoverPic(coverPicData);
		
		groupInfoBean.setGroupInfoDetails(detailList);
		groupInfoBean.setGroupInfoPics(picList);
		GroupInfoBean insertResult = createGroupService.createGroup(groupInfoBean);
		if (insertResult != null) {
			successMessage.put("createSuccess", "創團成功!!!");
			List<WishPoolBean> wishDetail = (List<WishPoolBean>)session.getAttribute("wishDetail");
			
			if (wishDetail != null) {
				int wishNo = wishDetail.get(0).getWishNo();
				List<WishInterestBean> members = wishInterestService.interestMembers(wishNo);
				Boolean deleteStatus = wishPoolService.delete(wishNo);
				System.out.println("deleteStatus=" + deleteStatus);
				for (WishInterestBean member : members) {
					int memberNo = member.getMemberNo();
					bean.setMemberNo(memberNo);
					siteMailService.sendMail(bean, 4);
					int count = 0;
					count++;
					System.out.println("count="+count);
				}
			}
			
			String path1 = request.getContextPath();
			response.sendRedirect(path + "/creategroup/successCreate.jsp");
			return;
		} else {
			response.sendRedirect(path + "/creategroup/successCreate.jsp");
			return;
		}
	}
}
