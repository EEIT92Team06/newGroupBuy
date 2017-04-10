package creategroup.model;

import javax.servlet.http.Part;
import javax.sql.DataSource;

public class GlobalService {
 
	public static String getFileName(final Part part) {
		/*
		 * 以一張圖為例 Content-Disposition: form-data; name="picture";
		 * filename="2015-06-08-03-26-34_deco.jpg" Content-Type: image/jpeg
		 * 此段為透過"multipart/form-data"傳送資訊其中的檔案資訊
		 * part.getHeader("content-disposition").split(";")表示將上述陳述句以;號隔開 變
		 * form-data,name="picture",filename="2015-06-08-03-26-34_deco.jpg"
		 * 三段content 而content.trim().startsWith("filename")表示從filename開頭的
		 * ,所以是filename="2015-06-08-03-26-34_deco.jpg"這段
		 * content.substring(content.indexOf('=') + 1)表示
		 * 從=號後的下一位開始取,所以值為，"2015-06-08-03-26-34_deco.jpg"
		 * 最後.trim().replace("\"",
		 * "")表示去空白，再將"號以空白取代(以\"號跳脫字元表示)最後可得2015-06-08-03-26-34_deco.jpg檔案名
		 * 
		 */
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				//System.out.println(content.substring(content.indexOf('=') + 1).trim().replace("\"", ""));
				return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}



	public static void main(String[] args) {

	}

}
