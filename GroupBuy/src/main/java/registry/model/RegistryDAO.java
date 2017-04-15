package registry.model;

import login.model.MemberBean;

public interface RegistryDAO {

	MemberBean selectByAccount(String memberAccount);

	MemberBean insertMember(MemberBean memberBean);

	MemberBean updateMemberStatus(MemberBean memberBean);

}