package eeit9212.model;

public interface GroupAttendanceDAO {

	boolean selectGroupAttendance(int memberNo);

	int insertGroupAttendance(int memberNo, int success);

	int updateGroupAttendance(int memberNo, int success);

}