package org.framework.utils;
import java.io.DataOutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HTTPsURLConnectionPostRequest {

	
	public static HttpsURLConnection httpsPostRequest(String urlString) throws Exception{
		URL url = new URL(urlString);
	    HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
	    con.setRequestMethod("POST");
	    con.connect();
	    con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlString);
		wr.flush();
		wr.close();
	    return con;
	}
	
}
