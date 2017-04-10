package creategroup.model;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CreateGroupService {
    private GroupInfoDAO groupInfoDAO;
    
    public CreateGroupService() {
	
	}
    
	public CreateGroupService(GroupInfoDAO groupInfoDAO) {
		this.groupInfoDAO = groupInfoDAO;
	}
	public static void main(String[]args){
		ApplicationContext context=new ClassPathXmlApplicationContext("beans.config.xml");
		CreateGroupService createGroupService=(CreateGroupService)context.getBean("createGroupService");
		SessionFactory sessionFactory=(SessionFactory)context.getBean("sessionFactory");
		//啟動交易
		sessionFactory.getCurrentSession().beginTransaction();
		//以下為加假資料到GroupInfoBean裡
		GroupInfoBean groupInfoBean=new GroupInfoBean();
		groupInfoBean.setMemberNo(1);
		groupInfoBean.setGroupStatusNo(1);
		groupInfoBean.setProductTypeNo(1);
		groupInfoBean.setGroupInfoName("爽團");
		groupInfoBean.setGroupInfoMinProductQt(10);
		groupInfoBean.setGroupInfoStartDate(new Timestamp(0));
		groupInfoBean.setGroupInfoDeadLine(new Timestamp(0));
		groupInfoBean.setGroupInfoContent("這團是測試用");
		groupInfoBean.setGroupInfoShippingWay("黑貓");
		groupInfoBean.setGroupInfoBankAccount("0806449");
		File file=new File("C://EEIT92_GroupBuy//pic//1.jpg");
		byte[]coverPics=createGroupService.imagesToBytes(file);
		
		groupInfoBean.setGroupInfoCoverPic(coverPics);

	    Set<GroupInfoDetailsBean> groupInfoDetailsBeanList=new HashSet<GroupInfoDetailsBean>();
		GroupInfoDetailsBean groupInfoDetailsBean=new GroupInfoDetailsBean();
		
		groupInfoDetailsBean.setGroupInfoNo(groupInfoBean);
		groupInfoDetailsBean.setGroupInfoDetailsProdcutName("屌");
		groupInfoDetailsBean.setGroupInfoDetailsProductPrice(100);
		groupInfoDetailsBeanList.add(groupInfoDetailsBean);
		GroupInfoDetailsBean groupInfoDetailsBean1=new GroupInfoDetailsBean();
		groupInfoDetailsBean1.setGroupInfoNo(groupInfoBean);
		groupInfoDetailsBean1.setGroupInfoDetailsProdcutName("操");
		groupInfoDetailsBean1.setGroupInfoDetailsProductPrice(1000);
		groupInfoDetailsBeanList.add(groupInfoDetailsBean1);
		
		groupInfoBean.setGroupInfoDetails(groupInfoDetailsBeanList);
		
		Set<GroupInfoPicBean> groupInfoPicBeanList=new HashSet<GroupInfoPicBean>();
		File file1=new File("C://EEIT92_GroupBuy//pic//2.jpg");
		byte[]pic1=createGroupService.imagesToBytes(file1);
     	GroupInfoPicBean groupInfoPicBean=new GroupInfoPicBean();
		groupInfoPicBean.setGroupInfoNo(groupInfoBean);
		groupInfoPicBean.setGroupInfoPicProductPic(pic1);
		groupInfoPicBeanList.add(groupInfoPicBean);
		File file2=new File("C://EEIT92_GroupBuy//pic//3.jpg");
		byte[]pic2=createGroupService.imagesToBytes(file2);
		GroupInfoPicBean groupInfoPicBean1=new GroupInfoPicBean();
		groupInfoPicBean1.setGroupInfoNo(groupInfoBean);
		groupInfoPicBean1.setGroupInfoPicProductPic(pic2);
		groupInfoPicBeanList.add(groupInfoPicBean1);
		groupInfoBean.setGroupInfoPics(groupInfoPicBeanList);
		
		//測試SERVICEK的創團服務
		createGroupService.createGroup(groupInfoBean);
		sessionFactory.getCurrentSession().getTransaction().commit();
		((ConfigurableApplicationContext)context).close();
	}
	//創團服務
	public GroupInfoBean createGroup(GroupInfoBean groupInfoBean){
		GroupInfoBean groupInfoBean1=groupInfoDAO.insertGroupInfo(groupInfoBean);
		return groupInfoBean1;
	}
	
	
	// 將照片轉成byte陣列
	public byte[] imagesToBytes(File file) {
		FileInputStream fis = null;
		try {
			byte[] imgData = new byte[(int) file.length()];
			fis = new FileInputStream(file);
			fis.read(imgData);
			return imgData;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;

	}

}
