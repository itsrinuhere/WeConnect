package api.main.services;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.servlet.ServletContainer;
import api.main.AlertSystem.*;
@Path("/endpoint")
public class Service extends Application{
	@RolesAllowed("ADMIN")
	@Path("test")
	@GET
	@Produces(MediaType.TEXT_PLAIN)	
	public String testing() {
		return "hello";
	}
	@RolesAllowed("ADMIN")
	@GET
	@Path("/Alerts/getallalerts")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllAlerts(){
		   List < Alert > data=new Operation().getAlerts();
	        if (!data.isEmpty()) {
	            return Response.ok(data).build();
	        } else {
	            return Response.status(Response.Status.NOT_FOUND).build();
	        }
	}//end of get all alerts
	@GET
	@Path("/Alerts/getalert/{id}")
	//@Produces(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Alert> getAlert(@PathParam("id") long id){
	//	return Response.ok(new Repository().getAlert(id)).build();
	List<Alert> data=	new Operation().getAlert(id);
	if(!data.isEmpty())
		return data;
			return null;
	}
	
	@DELETE
	@Path("/Alerts/deletealerts/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response DeleteAlert(@PathParam("id") long id) {
		if(new Operation().DeleteAlert(id))
					return Response.ok().status(Response.Status.NO_CONTENT).build();
					return Response.notModified().build();
		}
	
	@PUT
	@Path("/Alerts/updatealerts/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response UpdateAlerts(@PathParam("id") long id,Alert alerts){
		boolean res = false;
		if(id >= 0){
			List<Alert> model = new Operation().getAlert(id);
		if(!model.isEmpty())
			if(alerts.getAlertMessage()!=null && alerts.getAlertTitle()==null) {
				System.out.println("alert msg");
				model.get(0).setAlertMessage(alerts.getAlertMessage());
				res = (new Operation().updateAlert(model.get(0)))?true:false;
			}
			else if(alerts.getAlertTitle() != null &&alerts.getAlertMessage()==null ) {
				System.out.println("alert title");
				model.get(0).setAlertTitle(alerts.getAlertTitle());
				res = (new Operation().updateAlert(model.get(0)))?true:false;
			}else if(alerts.getAlertMessage()!= null && alerts.getAlertTitle()!=null) {
				System.out.println("alert msg and tile");
				model.get(0).setAlertMessage(alerts.getAlertMessage());
				model.get(0).setAlertTitle(alerts.getAlertTitle());
				res = (new Operation().updateAlert(model.get(0)))?true:false;
			}
		}
		if(res)
			return Response.status(200).entity(Response.Status.NO_CONTENT).build();
			return Response.notModified().build();
	}
	@POST
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/Alerts/insertAlerts")
	//@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertAlert(Alert model){
		//boolean res =new Repository().updateAlert(model);
		 if (new Operation().saveAlert(model)) {
	            return Response.ok().status(Response.Status.CREATED).build();
	        } else {
	            return Response.notModified().build();
	        } 
	}
	
}
