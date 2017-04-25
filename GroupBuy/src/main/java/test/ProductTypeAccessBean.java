package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class ProductTypeAccessBean{
	private String tagName = "";
	private int selected = -1;
	public int getSeleted() {
		return selected;
	}
    public ProductTypeAccessBean(){
    	
    }
	public void setSeleted(int selected) {
		this.selected = selected;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	private DataSource dataSource;

	public ProductTypeAccessBean(DataSource dataSource) {
		this.dataSource = dataSource;
	}




	public final String SELECTALL = "select * from productType";
	public List<ProductTypeBean> getProductType(){
		List<ProductTypeBean> con = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = new ArrayList<ProductTypeBean>();
			conn=dataSource.getConnection();
			pstmt = conn.prepareStatement(SELECTALL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductTypeBean productTypeBean = new ProductTypeBean();
				productTypeBean.setProductTypeNo(rs.getInt("productType_No"));
				productTypeBean.setProductType(rs.getString("productType"));
				System.out.println(productTypeBean);
				con.add(productTypeBean);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return con;
	}

	public String getSelectTag(){
		String selectTag = "";		
		List<ProductTypeBean> pb = getProductType();
		System.out.println(11111);
		selectTag = "<select name='" + getTagName() + "'>";
		for (ProductTypeBean bean : pb) {
			int productTypeNo = bean.getProductTypeNo();
			String productType = bean.getProductType();
			if (productTypeNo == selected) {
				selectTag += "<option value='" + productTypeNo + "' selected>" + productType + "</option>";
			} else {
				selectTag += "<option value='" + productTypeNo + "'>" + productType + "</option>";
			}
		}
		selectTag += "</select>";
		return selectTag;
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		ProductTypeAccessBean productTypeAccessBean = (ProductTypeAccessBean) context.getBean("productTypeAccessBean");
		List<ProductTypeBean> BeanList = productTypeAccessBean.getProductType();
		Iterator<ProductTypeBean> pb = BeanList.iterator();

		while (pb.hasNext()) {
			ProductTypeBean productTypeBean = pb.next();
			System.out.println("ProductTypeNo=" + productTypeBean.getProductTypeNo());
			System.out.println("ProductType=" + productTypeBean.getProductType());

		}
	}
}
