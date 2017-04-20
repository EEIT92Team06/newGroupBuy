package searchgroup.model.dao;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;

public class autocompleteDAO {
	private SessionFactory sessionFactory;
	public autocompleteDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	static <T> T[] append(T[] arr, T element) {
	    final int N = arr.length;
	    arr = Arrays.copyOf(arr, N + 1);
	    arr[N] = element;
	    return arr;
	}
	
	private static final String SELECT_BY_GROUPBANE =
	"select groupInfo_Name from groupInfo where groupInfo_Name like ?";
	public List<Object> selectResult(String groupName) {
		Session session = this.getSession();
		NativeQuery query = session.createNativeQuery(SELECT_BY_GROUPBANE);
		query.setParameter(1, "%"+groupName+"%");
		List<Object> result = query.getResultList();
		System.out.println("size : " + result.size());
		return result;
	}
	
	public String[] take(String groupName){
		System.out.println("111");
		List<Object> aa = selectResult(groupName);
		System.out.println("222");
		String[] fi=new String[]{}; 

		for(Object bb:aa){
			String xd;
			xd=bb.toString();
			fi=append(fi, xd);
		}
		return fi;
        /////////////
	}
	
	
	
	
}
