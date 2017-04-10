package sitemail.model;

import java.util.List;

public interface SiteMailDAO {

	List<Object[]> selectMailByMemberNo(int memberNo);

	int deleteMail(int siteMailNo);
	
	MailBean selectSpecificMail(Integer siteMailNo);

	List<Object[]> selectAnnounceMailByMemberNo(Integer memberNo);

	AnnouncementBean selectAnnounceMail(Integer siteMailNo);

	int insertSiteMail(SiteMailBean siteMailBean);

	int deleteAnnounceMail(int siteMailNo);

	List<Object[]> searchMail(String keyWord, int memberNo);

	List<Object[]> searchAnnounceMail(String keyWord, int memberNo);

	List<Object[]> selectUnReadMailByMemberNo(int memberNo);

	List<Object[]> selectUnReadAnnounceMailByMemberNo(Integer memberNo);
	
	int updateMailStatus(SiteMailBean siteMailBean);

	int updateAnnounceMailStatus(AnnouncementBean announcementBean);


}