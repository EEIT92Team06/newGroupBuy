package searchgroup.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import searchgroup.model.SearchDAO;

public class SearchDAOhb implements SearchDAO {

	private SessionFactory sessionFactory;
	public SearchDAOhb(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.cfg.xml");
		SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();
		SearchDAO serchDAOJdbc = (SearchDAO) context.getBean("searchDAO");
		List<Object[]> result = serchDAOJdbc.select("豆");
		for(Object[] objArr : result){
			for(int i=0;i<objArr.length;i++){
				System.out.println(objArr[i]);
			}
		}
		// ============================================================
//		List<Object[]> result = serchDAOJdbc.select();
//		for(Object[] objArr : result){
//			for(int i=0;i<objArr.length;i++){
//				System.out.println(objArr[i]);
//			}
//		}
		// ============================================================		
//		List<Object[]> result = serchDAOJdbc.select(4);
//		for(Object[] objArr : result){
//			for(int i=0;i<objArr.length;i++){
//				System.out.println(objArr[i]);
//			}
//		}
		// ============================================================
//		List<Object[]> result = serchDAOJdbc.selectGroupType();
//		for(Object[] objArr : result){
//			for(int i=0;i<objArr.length;i++){
//				System.out.println(objArr[i]);
//			}
//		}
		// ============================================================
//		List<Object[]> result = serchDAOJdbc.selectTop2Group();
//		for(Object[] objArr : result){
//			for(int i=0;i<objArr.length;i++){
//				System.out.println(objArr[i]);
//			}
//		}
		// ============================================================
//		Object[] result = serchDAOJdbc.selectRecommendGroup(1);
//		int[] resultToint = new int[8];
//		for(int i =1 ; i< result.length; i++){
//			resultToint[i-1] = (int)result[i];
//			System.out.println(resultToint[i-1]);
//		}
//		int[] index = {1,2,3,4,5,6,7,8};
//		while(true){
//			int counter = 0;
//			for(int i = 0;i<resultToint.length-1;i++){
//				int temp = 0;
//				int temp1 = 0;
//				if(resultToint[i]<resultToint[i+1]){
//					temp = resultToint[i];
//					temp1 = index[i];
//					resultToint[i] = resultToint[i+1];
//					index[i] = index[i+1];
//					resultToint[i+1] = temp;
//					index[i+1] = temp1;
//					counter++;
//				}
//			}
//			if(counter == 0)break;
//		}
//		for(int i =0 ; i< resultToint.length; i++){
//			System.out.print("index : " + index[i]);
//			System.out.println(" || result = " + resultToint[i]);
//		}
		// ============================================================
//		int a = 1;
//		int b = 1;
//		int result = serchDAOJdbc.insertClickTimes
//				("update recommend set recommend_ProductType"+a+"=recommend_ProductType"+a+"+1 where member_No = "+b);
		 
//		System.out.println("result ==> " + result);
		sessionFactory.getCurrentSession().getTransaction().commit();
		((ConfigurableApplicationContext) context).close();
	}

	private static final String SELECT_BY_GROUPBANE = "select groupInfo_No , groupInfo_Name , productType, groupStatus , member_Name , grouperCredit_totalScore/grouperCredit_totalPeople result "
			+ "from groupInfo join productType on groupInfo.productType_No = productType.productType_No "
			+ "join groupStatus on groupInfo.groupStatus_No = groupStatus.groupStatus_No "
			+ "join member on groupInfo.member_No = member.member_No "
			+ "Left Outer join grouperCredit on grouperCredit.member_No = member.member_No "
			+ "where groupInfo_Name like ? order by groupInfo_No desc";

	@Override
	public List select(String groupName) {
		Session session = this.getSession();
		NativeQuery query = session.createNativeQuery(SELECT_BY_GROUPBANE);
		query.setParameter(1, "%"+groupName+"%");
		return query.getResultList();
	}
	
	private static final String SELECT_ALL = "select groupInfo_No , groupInfo_Name , productType, groupStatus , member_Name , grouperCredit_totalScore/grouperCredit_totalPeople result "
			+ "from groupInfo join productType on groupInfo.productType_No = productType.productType_No "
			+ "join groupStatus on groupInfo.groupStatus_No = groupStatus.groupStatus_No "
			+ "join member on groupInfo.member_No = member.member_No "
			+ "Left Outer join grouperCredit on grouperCredit.member_No = member.member_No order by groupInfo_No desc";

	@Override
	public List<Object[]> select() {
		Session session = this.getSession();
		NativeQuery query = session.createNativeQuery(SELECT_ALL);
		return query.getResultList();
	}
//
	private static final String SELECT_BY_PRODUCTTYPENO = "select groupInfo_No , groupInfo_Name , productType, groupStatus , member_Name , grouperCredit_totalScore/grouperCredit_totalPeople result "
			+ "from groupInfo join productType on groupInfo.productType_No = productType.productType_No "
			+ "join groupStatus on groupInfo.groupStatus_No = groupStatus.groupStatus_No "
			+ "join member on groupInfo.member_No = member.member_No "
			+ "Left Outer join grouperCredit on grouperCredit.member_No = member.member_No "
			+ "where productType.productType_No = ? order by groupInfo_No desc";

	@Override
	public List<Object[]> select(int groupTypeNo) {
		Session session = this.getSession();
		NativeQuery query = session.createNativeQuery(SELECT_BY_PRODUCTTYPENO);
		query.setParameter(1, groupTypeNo);
		return query.getResultList();
	}
//
	private static final String SELECTGROUPTYPE = "select productType_No , productType from productType";
//
	@Override
	public List<Object[]> selectGroupType() {
		Session session = this.getSession();
		NativeQuery query = session.createNativeQuery(SELECTGROUPTYPE);
		return query.getResultList();
	}
//
	private static final String SELECT_TOP2_GROUP = "select Top 3 groupInfo_No , groupInfo_Name , productType, groupStatus , member_Name , grouperCredit_totalScore/grouperCredit_totalPeople result "
			+ "from groupInfo join productType on groupInfo.productType_No = productType.productType_No "
			+ "join groupStatus on groupInfo.groupStatus_No = groupStatus.groupStatus_No "
			+ "join member on groupInfo.member_No = member.member_No "
			+ "Left Outer join grouperCredit on grouperCredit.member_No = member.member_No "
			+ "order by groupInfo_ClickTimes desc ";
//
	@Override
	public List<Object[]> selectTop2Group() {
		Session session = this.getSession();
		NativeQuery query = session.createNativeQuery(SELECT_TOP2_GROUP);
		return query.getResultList();
	}
//
	private static final String SELECT_RECOMMEND_TABLE_BY_MEMBERNO = 
			"select * from recommend where member_No = ?";
	public Object[] selectRecommendTable(int memberNo){
		Session session = this.getSession();
		NativeQuery<Object[]> query = session.createNativeQuery(SELECT_RECOMMEND_TABLE_BY_MEMBERNO);
		query.setParameter(1, memberNo);
		return query.getSingleResult();
	}
//
	public int insertClickTimes(String INSERT_RECOMMEND_CLICKTIME) {
		Session session = this.getSession();
		NativeQuery query = session.createNativeQuery(INSERT_RECOMMEND_CLICKTIME);
		return query.executeUpdate();
	}
	
	
	private static final String insertRecommend= 
	"insert into recommend values(?,0,0,0,0,0,0,0,0)";
	@Override
	public int insertRecommend(int memberNo) {
		Session session = this.getSession();
		NativeQuery query = session.createNativeQuery(insertRecommend);
		query.setParameter(1, memberNo);
		return query.executeUpdate();
	}
	
	
}
