package login.model;

public interface LoginDAO {

	MemberBean selectByAccount(String memberAccount);
	
	public Object selectban(int memberNo);
	
	public Object selectMemberStatus(int memberNo);
	
	public int delete(int memberNo);
	
	public int update(int memberNo);
}