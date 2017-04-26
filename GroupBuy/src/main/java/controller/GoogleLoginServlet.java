package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.Gson;

import login.model.GoogleBean;
import login.model.GsonUtility;
import login.model.LoginService;
import login.model.MemberBean;
import login.model.SetUp;
import registry.model.RegistryService;

@WebServlet("/googleLoginServlet.do")
public class GoogleLoginServlet extends HttpServlet {
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
		System.out.println("this is servlet");
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("this is servlet");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String code = request.getParameter("code");
		// Google取得access_token的url
		URL url = new URL("https://accounts.google.com/o/oauth2/token");
		// 建立HttpURLConnection物件，並利用URL的openConnection()來建立連線。
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		// 設定此connection使用Post
		conn.setRequestMethod("POST");
		// 以方法 setDoOutput(true) 允許連線時送出資料(預設不輸入)
		conn.setDoOutput(true);
		// 由conn連線物件取得輸出資料流，再轉為Writer類別
		OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
		// 送出參數資料
		// 要再傳上面的幾個參數到上面的url，Google才會回傳給我們access_token
		out.write("code=" + code + "&");// 取得Google回傳的參數code
		out.write("client_id=" + SetUp.CLIENT_ID + "&");
		out.write("client_secret=" + SetUp.CLIENT_SECRET + "&");
		out.write("redirect_uri=" + SetUp.REDIRECT_URL + "&");
		out.write("grant_type=authorization_code");
		// 強制把所有資料送出去，不寫可能會出錯
		out.flush();
		out.close();
		// 如果認證成功
		if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
			// 使用StringBuilder串流字串速度較快
			StringBuilder sbLines = new StringBuilder("");

			// 取得Google回傳的資料(JSON格式)
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String strLine = "";
			// 一次讀一行，加到sbLines後
			while ((strLine = reader.readLine()) != null) {
				sbLines.append(strLine);
			}
			// 把上面取回來的資料，放進JSONObject中，以方便我們直接存取到想要的參數
			String accessToken = GsonUtility.getJsonElementString("access_token", sbLines.toString());
			System.out.println("accessToken=" + accessToken);
			// 利用上述得到的access_token，然後把這個token帶給Google API即可獲得資訊
			url = new URL("https://www.googleapis.com/oauth2/v1/userinfo?access_token=" + accessToken);
			conn = (HttpURLConnection) url.openConnection();
			// 去讀取加上access_token的url的用戶資訊
			reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			StringBuilder sbLines1 = new StringBuilder("");
			String strLine1 = "";
			while ((strLine1 = reader.readLine()) != null) {
				sbLines1.append(strLine1);
			}
			// 下兩行將json格式資料用gson來處理，存到GoogleBean裡面對應的資料型態，而stringBuilder必須要toString才能轉字串
			Gson gson = new Gson();
			GoogleBean googleBean = (GoogleBean) gson.fromJson(sbLines1.toString(), GoogleBean.class);
			//System.out.println("googleBean=" + googleBean);
			System.out.println("googleBean="+googleBean);
			out.close();
			reader.close();
			MemberBean memberBean = null;
			if (googleBean != null) {
				memberBean = registryService.selectMemberAccount(googleBean.getEmail());
				
				if(memberBean!=null){
					System.out.println("memberNO="+memberBean.getMemberNo());
					session.setAttribute("loginToken", memberBean);
					String path=request.getContextPath();
					response.sendRedirect(path+"/theindex.jsp");
					return;
				}
			}
			request.setAttribute("googleData", googleBean);
			request.getRequestDispatcher("/secure/newRegistry.jsp").forward(request, response);

			return;
		}

	}

}
