package searchgroup.model;

import java.util.List;

public interface SearchDAO {
	
	public List<Object[]> select(String groupName);
		
	public List<Object[]> select();
//	
	public List<Object[]> selectGroupType();
//	
	public List<Object[]> select(int groupTypeNo);
//	
	public List<Object[]> selectTop2Group();
//	
	public Object[] selectRecommendTable(int memberNo);
//	
	public int insertClickTimes(String INSERT_RECOMMEND_CLICKTIME);
	
	public int insertRecommend(int memberNo);
}
