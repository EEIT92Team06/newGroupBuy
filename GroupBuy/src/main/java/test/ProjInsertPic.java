package test;

import java.sql.*;
import java.io.*;

public class ProjInsertPic {
	public static void main(String[] args) {
		Connection conn = null;
//		String inFile = args[0];
//		String outFile = args[1];  
		
//		String inFile = "c:/temp/aaa.jpg";
		String outFile = "test.jgp";
		String[] inFileArray={"C:/temp/fake/214_02大紅棗01.jpg","C:/temp/fake/215_03活菌里肌肉片01.jpg","C:/temp/fake/216_01進口零食00.jpg","C:/temp/fake/217_01休族時間01.jpg","C:/temp/fake/218_01太陽眼鏡01.jpg","C:/temp/fake/219_02頭髮飾品01.jpg","C:/temp/fake/220_02化妝套01.jpg","C:/temp/fake/221_Anycast M2.jpg","C:/temp/fake/222_02迪士尼手機座01.jpg","C:/temp/fake/223_01籃球01.jpg"};
		try {     
			String connUrl = "jdbc:sqlserver://localhost:1433;databaseName=GroupBuy";
			conn = DriverManager.getConnection(connUrl, "sa", "passw0rd");
			
//			PreparedStatement stmt = conn.prepareStatement(qryStmt);
//			stmt.setString(1, inFile);
//			ResultSet rs = stmt.executeQuery();
//			
//			if (rs.next()) {
//				String deleteStmt = "DELETE FROM blobtest WHERE name = ?"; 
//				stmt = conn.prepareStatement(deleteStmt);
//				stmt.setString(1, inFile);
//				stmt.executeUpdate();
//				System.out.println("Delete blob is successful!");
//			}
			for(int i=0;i<inFileArray.length;i++){
			File f = new File(inFileArray[i]);
			FileInputStream fis = new FileInputStream(f);
			String updateStmt = "update groupInfo set groupInfo_CoverPic = ? where groupInfo_No = ?";
//			String insertStmt = "INSERT INTO groupInfoPic VALUES(?,?)";	
			PreparedStatement stmt = conn.prepareStatement(updateStmt);
			stmt.setBinaryStream(1, fis);
			stmt.setInt(2, i+6);	
			stmt.executeUpdate();
			System.out.println("Update blob is successful!");
			}
	
//			String qryStmt = "SELECT groupInfoPic_ProductPic FROM groupInfoPic WHERE groupInfoPic_No = ?";
//			stmt = conn.prepareStatement(qryStmt);
//			stmt.setInt(1,100);
//			ResultSet rs = stmt.executeQuery(); 
//			if (rs.next()) {
//				FileOutputStream fos = new FileOutputStream(outFile);
//				Blob b = rs.getBlob("groupInfoPic_ProductPic");
//				byte[] data = b.getBytes(1, (int)b.length());
//				fos.write(data, 0, (int)b.length());
//				fos.close();
//				System.out.println("File output is successful!");
//			} // end of if (rs.next()) 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
		}
	}// end of main()
}// end of class BLOBDemo 
