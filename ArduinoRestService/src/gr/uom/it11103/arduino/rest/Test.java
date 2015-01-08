package gr.uom.it11103.arduino.rest; //Βαλε ολα τα rest service σε αυτο το πακετο

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;



// full path  http://localhost:8080/ArduinoRestService/rest/test
@Path("/test")
public class Test {
	 
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String test(){
		return "Hello World!!";
	}
	
	
	}
