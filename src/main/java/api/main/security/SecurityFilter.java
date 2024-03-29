package api.main.security;
//import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
//import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

//import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.internal.util.Base64;
//import org.glassfish.jersey.server.ResourceConfig;
@Provider
public class SecurityFilter implements ContainerRequestFilter{
	@Context
    private ResourceInfo resourceInfo;
	   private static final String AUTHORIZATION_PROPERTY = "Authorization";
	    private static final String AUTHENTICATION_SCHEME = "Basic";
	 private static final Response ACCESS_DENIED = Response.status(Response.Status.UNAUTHORIZED)
				.entity("Invalid").build();
private static final Response ACCESS_FORBIDDEN = Response.status(Response.Status.FORBIDDEN)
				.entity("Access blocked for all users !!").build();

	@Override
	public void filter(ContainerRequestContext requestContext) {
		try {
		Method method = resourceInfo.getResourceMethod();
		 //Access allowed for all
        if( ! method.isAnnotationPresent(PermitAll.class))
        {
            //Access denied for all
            if(method.isAnnotationPresent(DenyAll.class))
            {
                requestContext.abortWith(ACCESS_FORBIDDEN);
                return;
            }
             
            //Get request headers
            final MultivaluedMap<String, String> headers = requestContext.getHeaders();
             
            //Fetch authorization header
            final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);
             
            //If no authorization information present; block access
            if(authorization == null || authorization.isEmpty())
            {
                requestContext.abortWith(ACCESS_DENIED);
                return;
            }
             
            //Get encoded user name and password
            final String encodedUserPassword = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");
             
            //Decode user name and password
            String usernameAndPassword = new String(Base64.decode(encodedUserPassword.getBytes()));;
 
            //Split user name and password tokens
            final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
            final String username = tokenizer.nextToken();
            final String password = tokenizer.nextToken();
             
            //Verifying User name and password
            System.out.println(username);
            System.out.println(password);
            System.out.println("security filter called");
            //Verify user access
            if(method.isAnnotationPresent(RolesAllowed.class))
            {
                RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
                Set<String> rolesSet = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));
                 
                //Is user valid?
                if( ! isUserAllowed(username, password, rolesSet))
                {
                    requestContext.abortWith(ACCESS_DENIED);
                    return;
                }
            }
        }//end of if block
		}//end of try block
		catch(Exception ex) {
			//throws IOException, WebApplicationException
			System.out.println(ex);
			//requestContext.setRequestUri(
			//return;
		}
    }

	private boolean isUserAllowed(String username, String password, Set<String> rolesSet) {
		// TODO Auto-generated method stub
		boolean isAllowed = false;
        
        //Step 1. Fetch password from database and match with password in argument
        //If both match then get the defined role for user from database and continue; else return isAllowed [false]
        //Access the database and do this part yourself
        //String userRole = userMgr.getUserRole(user name);
        
        if(username.equals("username") && password.equals("password"))
        {
        	String userRole = "ADMIN";
            
            //Step 2. Verify user role
            if(rolesSet.contains(userRole))
            {
                isAllowed = true;
            }
        }
        return isAllowed;
	}
	
}
