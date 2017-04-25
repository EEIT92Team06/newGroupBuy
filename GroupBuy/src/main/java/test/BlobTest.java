package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BlobTest {
	private DataSource dataSource;

	public BlobTest(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		DataSource dataSource = (DataSource) context.getBean("dataSource");
		BlobTest blobTest=new BlobTest(dataSource);
		int updateNum=blobTest.updatePic("C://groupInfoPic//6.jpg",6);
		System.out.println("updateNum="+updateNum);
		((ConfigurableApplicationContext) context).close();
	}

	String updatePic = "update groupInfoPic set groupInfoPic_ProductPic=? where groupInfoPic_No=?";

	public int updatePic(String file,int groupInfoPicNo) {
		int updateNum=0;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			File pic = new File(file);
			InputStream is = new FileInputStream(pic);
			PreparedStatement pstmt = conn.prepareStatement(updatePic);
			pstmt.setBinaryStream(1, is, pic.length());
			pstmt.setInt(2,groupInfoPicNo);
			pstmt.executeUpdate();
			updateNum=1;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return updateNum;
	}
    String selectPic="s";
//	public selectPic(){
//		
//	}
}
