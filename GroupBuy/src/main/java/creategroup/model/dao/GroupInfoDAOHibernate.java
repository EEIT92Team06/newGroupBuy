package creategroup.model.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import creategroup.model.CreateGroupService;
import creategroup.model.GroupInfoBean;
import creategroup.model.GroupInfoDAO;
import creategroup.model.GroupInfoDetailsBean;
import creategroup.model.GroupInfoPicBean;

public class GroupInfoDAOHibernate implements GroupInfoDAO{
	private  SessionFactory sessionFactory;

	public GroupInfoDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public GroupInfoDAOHibernate(){
		
	}
	Connection conn = null;

	public final static String SELECT_BY_GroupInfo_No = "select*from groupInfo g join member m on g.member_No=m.member_No "
			+ "join groupStatus gs on g.groupStatus_No= gs.groupStatus_No "
			+ "join productType pt on g.productType_No=pt.productType_No " + "where groupInfo_No=?";
	public final static String SELECT_ALL = "select*from groupInfo g join member m on g.member_No=m.member_No "
			+ "join groupStatus gs on g.groupStatus_No= gs.groupStatus_No "
			+ "join productType pt on g.productType_No=pt.productType_No ";

	public final static String INSERTgroupInfo = "insert into groupInfo values(?,?,?,?,?,?,?,?,?,?,?,?)";
	public static final String INSERTgroupInfoDetails = "insert into groupInfoDetails values(?,?,?)";
	public static final String INSERTgroupInfoPic = "insert into groupInfoPic values(?,?)";

	public final static String UPDATE = "update groupInfo set groupInfo_No=?,member_No=?,groupStatus_No=?"
			+ "productType_No=?,groupInfo_Name=?,groupInfoMin_ProductQt=?"
			+ "groupInfo_tartDate=?,groupInfo_DeadLine=?,groupInfo_Content=?"
			+ "groupInfo_ShippingWay=?,groupInfo_BankAccount=?";
	public final static String DELETE = "delete from groupInfo where groupInfo_No=?";

	public static void main(String[] args) throws FileNotFoundException {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		SessionFactory sessionFactory=(SessionFactory) context.getBean("sessionFactory");
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		GroupInfoDAOHibernate groupInfoDAOHibernate=(GroupInfoDAOHibernate) context.getBean("groupInfoDAOHibernate");
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
		CreateGroupService createGroupService=new CreateGroupService();
		byte[]coverPics=createGroupService.imagesToBytes(file);
		
		groupInfoBean.setGroupInfoCoverPic(coverPics);

		Set<GroupInfoDetailsBean> groupInfoDetailsBeanSet=new HashSet<GroupInfoDetailsBean>();
		GroupInfoDetailsBean groupInfoDetailsBean=new GroupInfoDetailsBean();
		
		groupInfoDetailsBean.setGroupInfoNo(groupInfoBean);
		groupInfoDetailsBean.setGroupInfoDetailsProdcutName("屌");
		groupInfoDetailsBean.setGroupInfoDetailsProductPrice(100);
		groupInfoDetailsBeanSet.add(groupInfoDetailsBean);
		GroupInfoDetailsBean groupInfoDetailsBean1=new GroupInfoDetailsBean();
		groupInfoDetailsBean1.setGroupInfoNo(groupInfoBean);
		groupInfoDetailsBean1.setGroupInfoDetailsProdcutName("操");
		groupInfoDetailsBean1.setGroupInfoDetailsProductPrice(1000);
		groupInfoDetailsBeanSet.add(groupInfoDetailsBean1);
		
		groupInfoBean.setGroupInfoDetails(groupInfoDetailsBeanSet);
		
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
		

		groupInfoDAOHibernate.insertGroupInfo(groupInfoBean);
		groupInfoDAOHibernate.selectAll();
		
		sessionFactory.getCurrentSession().getTransaction().commit();

		((ConfigurableApplicationContext) context).close();
	}

	@Override
	public GroupInfoBean insertGroupInfo(GroupInfoBean bean) {
        Session session=this.sessionFactory.getCurrentSession();
        session.save(bean);
        GroupInfoBean groupInfoBean=(GroupInfoBean)session.get(GroupInfoBean.class,bean.getGroupInfoNo());  
        return groupInfoBean;
	}
    private static final String SELECT="from GroupInfoBean";
    public List<GroupInfoBean> selectAll(){
    	Session session=this.sessionFactory.getCurrentSession();
    	List<GroupInfoBean> groupInfoList=session.createQuery(SELECT, GroupInfoBean.class).getResultList();
        
        for(GroupInfoBean b:groupInfoList){
        	System.out.print(b.getGroupInfoNo()+",");
        	System.out.print(b.getMemberNo()+",");
        	System.out.print(b.getGroupStatusNo()+",");
        	System.out.print(b.getProductTypeNo()+",");
        	System.out.print(b.getGroupInfoName()+",");
        	System.out.print(b.getGroupInfoMinProductQt()+",");
        	System.out.print(b.getGroupInfoStartDate()+",");
        	System.out.print(b.getGroupInfoDeadLine()+",");
        	System.out.print(b.getGroupInfoContent()+",");
        	System.out.print(b.getGroupInfoShippingWay()+",");
        	System.out.print(b.getGroupInfoBankAccount()+",");
        	System.out.print(b.getGroupInfoClickTimes()+",");
        	System.out.print(b.getGroupInfoCoverPic()+",");
        	System.out.println("");
        }
		return groupInfoList;
    } 
	
    public GroupInfoBean getPK(GroupInfoBean bean){
    	Session session=this.sessionFactory.getCurrentSession();
    	GroupInfoBean groupInfoBean=(GroupInfoBean)session.get(GroupInfoBean.class,bean.getGroupInfoNo());
    	return groupInfoBean;
    }


}
