package creategroup.model;

import java.io.FileInputStream;
import java.sql.Blob;

public class PicBean {

	private FileInputStream fis;
    private long size;
	
	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public FileInputStream getFis() {
		return fis;
	}

	public void setFis(FileInputStream fis) {
		this.fis = fis;
	}




}
