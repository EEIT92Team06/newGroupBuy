package eeit9212.model.dao;

import java.io.Serializable;

import eeit9212.model.ReportBean;

public interface ReportDAO {

	Serializable insertReport(ReportBean bean);

	void updateReport(ReportBean bean);

	ReportBean selectReport(ReportBean bean);
	
	
}