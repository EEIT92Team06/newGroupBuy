package searchgroup.model;

import java.util.List;

public interface SearchDetailsDAO {
	
	public Object[] selectDetails(int groupInfoNo);
	
	public List<Object[]> selectGroupProdsDetails(int groupInfoNo);
	
	public List<Object> selectDetailsPicNo(int groupInfoNo);
		
	public Object selectmemberPic(int memberNo);
	
	public int insertClickTimes(int groupInfoNo);
	
}
