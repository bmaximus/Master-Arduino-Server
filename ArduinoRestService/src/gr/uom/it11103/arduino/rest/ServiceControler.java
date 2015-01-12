package gr.uom.it11103.arduino.rest;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;





public class ServiceControler
{
	public static String REGISTRATIONID = "APA91bEvMmoQndNVtbPPKFzgojA7A8d89G0KfcYwCEl1XzuBwnfuC2SkgMmpX43rADkPbgmrD_TpkAKj9oju2sTkVKPSmQ5iph7U8O7Kh6MFDv-4br8yaiUZEFdgRyvKN50gBYcvmFpC0lT1e7ki2esMedR4Etwe9iY-b5j5ja3O_tXmgcncF0A";
	public static String ApiKey = "AIzaSyAtaT4JBpddxCSm6Qp0_Gv1WICVCw0F8GE";
	
	public static String getApiKey() {
		return ApiKey;
	}

	public static void setApiKey(String apiKey) 
	{
		ApiKey = apiKey;
	}

	public int ArduinoToAndroid(int movement, int light, int magnet)
	{
		
		boolean moved = false;
		boolean lighted = false;
		boolean magneted = false;
		String apiKey = "AIzaSyAtaT4JBpddxCSm6Qp0_Gv1WICVCw0F8GE";   //this is the new one
		
		String registrationId = getREGISTRATIONID();
		
		if(movement > 0) { moved = true;}  //H KAPOIA STATHERA POU THA ORISW GIA KINISI
		if(light > 0) { lighted = true;}  //H KAPOIA STATHERA POU THA ORISW GIA FWS
		if(magnet > 0) { magneted = true;}  //H KAPOIA STATHERA POU THA ORISW GIA FWS
		POST2GCM post2gcm = new POST2GCM();
	   
		if(!moved && !lighted && !magneted)      //0.0.0
		{
			post2gcm.post(apiKey, "This is a test Message from user. There should not be 0/0/0");
			return 1;
		} else if(!moved && !lighted && magneted)       //0.0.1
		{
			post2gcm.post(apiKey, "This is a message from Arduino, Your Magnetic Switch was interrupted. Please check it.");
			return 2;
		} else if(!moved && lighted && !magneted)       //0.1.0
		{
			post2gcm.post(apiKey, "This is a message from Arduino, Your Light Sensor recorded some Light. Please check it.");
			return 3;
		} else if(!moved && !lighted && !magneted)       //0.1.1
		{ 
			post2gcm.post(apiKey, "This is a message from Arduino, Your Light Sensor and Magnetic Switch were recorded some activity. Please check it.");
			return 4;
		} else if(moved && !lighted && !magneted)        //1.0.0
		{
			post2gcm.post(apiKey, "This is a test Message from user. Your Movement Sensor found some suspicious movement. Please check it.");
			return 5;
		} else if(moved && !lighted && magneted)         //1.0.1
		{
			post2gcm.post(apiKey, "This is a message from Arduino, Your Magnetic Switch and Movement Sensor were interrupted. Please check it.");
			return 6;
		} else if(moved && lighted && !magneted)         //1.1.0
		{
			post2gcm.post(apiKey, "This is a message from Arduino, Your Light Sensor and Movement Seonsor were interrupted. Please check it.");
			return 7;
		} else if(moved && lighted && magneted)          //1.1.1
		{ 
			post2gcm.post(apiKey, "This is a message from Arduino, Your Light Sensor and Magnetic Switch were recorded some activity. Please check it.");
			return 8;
		}
			
		
		return 0 ;
	}
	
	public static String getREGISTRATIONID() {
		return REGISTRATIONID;
	}
	public void setREGISTRATIONID(String rEGISTRATIONID) {
		REGISTRATIONID = rEGISTRATIONID;
	}
	

	
	public String RegistrationIdToTempFile(String regId){
		setREGISTRATIONID(regId);
		
		File f = new File(("C:\\Users\\Administrator\\Desktop\\KEYS\\"+regId));
		String message = "";
		try{
		    if(f.mkdir()) { 
		        System.out.println("Directory Created");
		        message = "Directory Created";
		    } else {
		        System.out.println("Directory is not created");
		        message = "Directory not Created";
		    }
		} catch(Exception e){
		    e.printStackTrace();
		} 
		
		
		return message;
		
	}


public class POST2GCM {

	
	 public void post(String apiKey, String  messageFromSender)
	 {
		 
		 
	        try{

	        // 1. URL
	        URL url = new URL("https://android.googleapis.com/gcm/send");
	        	// URL url = new URL("https://android.clients.google.com/c2dm/send");
	   
	       	 
	        	 
	        	    StringBuilder postDataBuilder = new StringBuilder();
	        	    postDataBuilder.append("registration_id").append("=")
	        	        .append(getREGISTRATIONID());	        	
	        	    postDataBuilder.append("&").append("data.price").append("=")
	        	        .append(URLEncoder.encode(messageFromSender,  "UTF-8"));	        	  
			        byte[] postData = postDataBuilder.toString().getBytes("UTF-8");
			        
			        	        	    
	        	    HttpsURLConnection.setDefaultHostnameVerifier(new CustomizedHostnameVerifier());
	        	    HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
	        	    conn.setDoOutput(true);
	        	    conn.setUseCaches(false);
	        	    conn.setRequestMethod("POST");
	        	    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
	        	  //  conn.setRequestProperty("Content-Length",   Integer.toString(postData.length));
	        	    conn.setRequestProperty("Authorization", "key="+getApiKey());
	        	           	    
	        	         	 
	        	    
	        	    

	        	    OutputStream out = conn.getOutputStream();
	        	    out.write(postData);
	        	    out.close();

	        	    int responseCode = conn.getResponseCode();
	        	    
	        	    System.out.println("\nSending 'POST' request to URL : " + url);
	        	    System.out.println("\nSending 'POST' request to apikey  : " + getApiKey());
	        	    System.out.println("\nSending 'POST' request to regID : " + getREGISTRATIONID());
		            System.out.println("Response Code : " + responseCode);
		            String str = new String(postData, "UTF-8");
		            System.out.println("Post : " + str);
	        	 
	        	 
	        	    	 
	        	 
	   /*    
	        StringBuilder postDataBuilder = new StringBuilder();
	        postDataBuilder.append("registration_id").append("=")
	            .append(getREGISTRATIONID());
	        postDataBuilder.append("&").append("collapse_key").append("=")
	            .append("0");
	        postDataBuilder.append("&").append("key").append("=")
            .append(getApiKey());
	        postDataBuilder.append("&").append("data.payload").append("=")
	            .append(URLEncoder.encode("MAXIM TESTT", "UTF-8"));

	        byte[] postData = postDataBuilder.toString().getBytes("UTF-8");
	        	       
	        HttpsURLConnection.setDefaultHostnameVerifier(new CustomizedHostnameVerifier());
	        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
	        conn.setDoOutput(true);
	        conn.setUseCaches(false);
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Content-Type",
	            "application/x-www-form-urlencoded;charset=UTF-8");
	        conn.setRequestProperty("Content-Length",
	            Integer.toString(postData.length));
	        conn.setRequestProperty("Authorization", "key="
	            + getApiKey());
	              DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
	            wr.flush();

	            wr.close();     
	            int responseCode = conn.getResponseCode();
	            System.out.println("\nSending 'POST' request to URL : " + url);
	            System.out.println("Response Code : " + responseCode);
	            BufferedReader in = new BufferedReader( new InputStreamReader(conn.getInputStream()));
	            String inputLine;
	            StringBuffer response = new StringBuffer();
	            while ((inputLine = in.readLine()) != null) {
	                response.append(inputLine);
	            }
	            in.close();
	            System.out.println(response.toString());
*/
	            } catch (MalformedURLException e) {
	                e.printStackTrace();
	            } catch (IOException e) {
	                e.printStackTrace();
	            } 
	        }
	 
}


public static Content createContent(){

	Content c= new Content();
    c.addRegId(getREGISTRATIONID());
    c.createData("Test Title","Test Message" );
    
    return c;
}
private static class CustomizedHostnameVerifier implements HostnameVerifier {
    public boolean verify(String hostname, SSLSession session) {
      return true;
    }


}
}
