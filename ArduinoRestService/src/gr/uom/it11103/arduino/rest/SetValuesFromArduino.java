package gr.uom.it11103.arduino.rest;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("/setvaluesfromarduino")
public class SetValuesFromArduino
{
	@GET
	@Path("{param1}/{param2}/{param3}")
	public int getUserHistory(
			@PathParam("param1") int movement,
			@PathParam("param2") int light, 
			@PathParam("param3") int magnet){
		
		ServiceControler sc = new ServiceControler();
	    int sendingVerificaton =  sc.ArduinoToAndroid(movement, light, magnet);
		return sendingVerificaton;
	}
	
}
  