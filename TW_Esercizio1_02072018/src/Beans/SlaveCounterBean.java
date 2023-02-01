package Beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

public class SlaveCounterBean extends Thread{
	
	
	private int conteggio = -1;
	private long elapesedMillisec =0;
	private String[] fileNames= new String[3];
	
	
	
	public int getConteggio() {
		return conteggio;
	}



	public void setConteggio(int conteggio) {
		this.conteggio = conteggio;
	}



	public long getElapesedMillisec() {
		return elapesedMillisec;
	}



	public void setElapesedMillisec(long elapesedMillisec) {
		this.elapesedMillisec = elapesedMillisec;
	}



	public String[] getFileNames() {
		return fileNames;
	}



	public void setFileNames(String[] fileNames) {
		this.fileNames = fileNames;
	}



	public SlaveCounterBean() {
		super();
		this.conteggio=0;
		this.elapesedMillisec=0;
		this.fileNames = new String[3];
	}



	@Override
	public void run() {
		long startTime = new Date().getTime();
		try{
			for(int i=0;i< this.fileNames.length;i++)
			{
				FileInputStream fis = new FileInputStream(new File(this.fileNames[0]));
				int c =fis.read();
				if(c>=65 && c<= 90)
					this.conteggio++;
				while(c>=0)
				{
					if(c>=65 && c<= 90)
						this.conteggio++;
					c=fis.read();		
				}
				fis.close();
			}
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long endTime = new Date().getTime();
		this.elapesedMillisec = endTime - startTime;
	}

}
