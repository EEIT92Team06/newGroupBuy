package eeit9212.model;

public interface GrouperCreditDAO {

	boolean selectGrouperCredit(int memberNo);

	int insertGrouperCredit(int memberNo, int score);

	int updateGrouperCredit(int memberNo, int score);

}