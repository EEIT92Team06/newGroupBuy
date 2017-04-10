package login.model;

public interface LoginDAO {

	MemberBean selectByAccount(String memberAccount);
    
}