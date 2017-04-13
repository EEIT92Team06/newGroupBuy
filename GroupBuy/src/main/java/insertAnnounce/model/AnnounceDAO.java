package insertAnnounce.model;

import java.util.List;

public interface AnnounceDAO {
	
	public List<Object[]> select();
	
	public List<Object[]> selectPartMem();
	
	public void insert(AnnouncementBean announcement);
	
	public int update(int memberNo);
	
	public int insertBan(int memberNo);
	
	public int updateGroupStatus(int groupInfoNo);
	
	public int updateOrderInfoStatusByGroupInfoNo(int groupInfoNo);
	
	public List<Object[]> selectReports();
}
