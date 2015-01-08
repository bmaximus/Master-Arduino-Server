package gr.uom.it11103.arduino.rest;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;




public class ServiceControler
{
	public static String REGISTRATIONID = "APA91bEvMmoQndNVtbPPKFzgojA7A8d89G0KfcYwCEl1XzuBwnfuC2SkgMmpX43rADkPbgmrD_TpkAKj9oju2sTkVKPSmQ5iph7U8O7Kh6MFDv-4br8yaiUZEFdgRyvKN50gBYcvmFpC0lT1e7ki2esMedR4Etwe9iY-b5j5ja3O_tXmgcncF0A";
	public static String ApiKey = "AIzaSyAtaT4JBpddxCSm6Qp0_Gv1WICVCw0F8GE";
	
	public static String getApiKey() {
		return ApiKey;
	}

	public static void setApiKey(String apiKey) {
		ApiKey = apiKey;
	}

	public int ArduinoToAndroid(int movement, int light, boolean magnet)
	{
		
		boolean moved = false;
		boolean lighted = false;
		String apiKey1 = "AIzaSyA2wmKgCJ0O8isSoHzkq8OBg7M71MwOXJQ";
		String apiKey = "AIzaSyAtaT4JBpddxCSm6Qp0_Gv1WICVCw0F8GE";   //this is the new one
		
		String registrationId = getREGISTRATIONID();
		
		if(movement > 0) { moved = true;}  //H KAPOIA STATHERA POU THA ORISW GIA KINISI
		if(light > 0) { lighted = true;}  //H KAPOIA STATHERA POU THA ORISW GIA FWS
		
		if(moved)
		{
			System.out.println("\n  "+ apiKey);		
		POST2GCM post2gcm = new POST2GCM();
	
		
		post2gcm.post(apiKey, null);
			return 1;
		}
 		
		//some send process
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

	
	 public void post(String apiKey, Content content)
	 {

	        try{

	        // 1. URL
	     //   URL url = new URL("https://android.googleapis.com/gcm/send");
	        	 URL url = new URL("https://android.clients.google.com/c2dm/send");
	      
	      //  HttpURLConnection conn = (HttpURLConnection) url.openConnection();       
	     //   conn.setRequestMethod("POST");	     
	     //   conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");// "application/json");
	      //  conn.setRequestProperty("Authorization", "key="+apiKey);
	      //  conn.setDoOutput(true);
	        
	        	 
	        	 
	        	    StringBuilder postDataBuilder = new StringBuilder();
	        	    postDataBuilder.append("registration_id").append("=")
	        	        .append(getREGISTRATIONID());
	        	    postDataBuilder.append("&").append("collapse_key").append("=")
	        	        .append("0");
	        	    postDataBuilder.append("&").append("data.payload").append("=")
	        	        .append(URLEncoder.encode("MAXIMUSSSSSSSSSSSSS",  "UTF-8"));	        	  
			        byte[] postData = postDataBuilder.toString().getBytes("UTF-8");
			        
			        
			        
	        	    
	        	    HttpsURLConnection
	        	        .setDefaultHostnameVerifier(new CustomizedHostnameVerifier());
	        	    HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
	        	    conn.setDoOutput(true);
	        	    conn.setUseCaches(false);
	        	    conn.setRequestMethod("POST");
	        	    conn.setRequestProperty("Content-Type",
	        	        "application/x-www-form-urlencoded;charset=UTF-8");
	        	    conn.setRequestProperty("Content-Length",
	        	        Integer.toString(postData.length));
	        	    conn.setRequestProperty("Authorization", "GoogleLogin auth="
	        	        + getApiKey());

	        	    OutputStream out = conn.getOutputStream();
	        	    out.write(postData);
	        	    out.close();

	        	    int responseCode = conn.getResponseCode();
	        	    
	        	    System.out.println("\nSending 'POST' request to URL : " + url);
	        	    System.out.println("\nSending 'POST' request to apikey  : " + getApiKey());
	        	    System.out.println("\nSending 'POST' request to regID : " + getREGISTRATIONID());
		            System.out.println("Response Code : " + responseCode);
	        	 
	        	 
	        	 
	        	 
	        	 
	        	 
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
