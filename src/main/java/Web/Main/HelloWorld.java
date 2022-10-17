package Web.Main;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import Web.users.Users;

@Path("/API/V1")
public class HelloWorld {
	@GET
	@Produces("text/html")
	@Path("/hello/{msg}")
	public String sayHello(@PathParam(value="msg") String msg){
		return "Hello "+msg;
	}
	@GET
	@Produces("application/json")
	@Path("/json/{msg}")
	public String json(@PathParam(value="msg") String msg){
		return "{\"employe\":"
				+ "{\"Name\":\""+msg+"\","
				+ "\"id\":\"19641A05M8\","
				+ "\"credits\":52,"
				+ "\"salary\":60000}"
				+ "}";
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/cal?{action}&{value1}&{value2}")
	public Response cal(@PathParam(value="value1")int value,@PathParam(value="value2")int value1) {
		return Response.status(200).entity(""+value1+value).build();
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/calculator/{action}/{value1}/{value2}")
	public Response  ArithmeticOperation(@PathParam(value="action")String action,@PathParam(value="value1")int value,@PathParam(value="value2")int value2){
		int response =0;
			if(action.equals("+"))response=value+value2;
			else if(action.equals("*"))response=value+value2;
			else if(action.equals("/"))response=value+value2;
			else if(action.equals("%"))response=value+value2;
			String res = "{\"values\":\""+response+"\""
					+"}";
		return Response.status(200)
				.entity(res).build();
	}
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("/student")
	public List<Users> verifystudent()
	{
		Users user = new Users();
		user.setId("12345");
		user.setPhonenumber("123456789");
		user.setUserbranch("CSE");
		user.setUsername("useer1");
		Users user1 = new Users();
		user1.setId("123456");
		user1.setPhonenumber("123212");
		user1.setUserbranch("MECH");
		user1.setUsername("user2");
		List<Users> ls =Arrays.asList(user,user1);
		return ls;
	}
}
