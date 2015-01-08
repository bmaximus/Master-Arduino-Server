package gr.uom.it11103.arduino.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
@Path("/regid")
public class RegistrationIdSetter {
	@GET
	@Path("{param1}")
	public String StoreRegistrationId(
			@PathParam("param1") String regId)
		{
		
		ServiceControler sc = new ServiceControler();
	 String message =   sc.RegistrationIdToTempFile(regId);
		return message;
		
	}
	
}
