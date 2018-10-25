package mx.servis.admonticket.rest.error;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

public class MoreInfo {
	
	private HttpServletRequest httpRequest;
	private Throwable throwable;
	
	public MoreInfo(HttpServletRequest httpRequest, Throwable throwable) {
		super();
		this.httpRequest = httpRequest;
		this.throwable = throwable;
	}
	
	public String build() {
		String idError = UUID.randomUUID().toString();
//		saveError(idError);
		return getURLMoreInfo(idError);
	}
	
	private String getURLMoreInfo(String idError) {
		StringBuffer urlMoreInfo = new StringBuffer("http://");
		 urlMoreInfo.append(httpRequest.getServerName())
		 .append(":")
		 .append(httpRequest.getServerPort())
		 .append("/error/")
		 .append(idError);
		 
		 return urlMoreInfo.toString();
	}
	
	
	private void saveError(String idError) {
		ObjectOutputStream o  = null;
		try {
			URL urlPath = this.getClass().getResource("/errors/");
			String filePath = urlPath.toURI().getPath();
			String fileFullPath = filePath + idError + ".bin";
		
			FileOutputStream f = new FileOutputStream(new File(fileFullPath));
			o = new ObjectOutputStream(f);
			o.writeObject(throwable);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if( o != null) {
				try {
					o.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	

}
