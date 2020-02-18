/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.util.Date;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.event.Event;
import javax.inject.Inject;




import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import com.fasterxml.jackson.jaxrs.annotation.JacksonFeatures;
import java.net.URI;
import javax.ejb.Timer;


/**
 *
 * @author jfern
 */
@Startup
@Singleton
public class ARestClient {

    ClientConfig config;
    Client client;
    WebTarget target; 
    
   public ARestClient()
    {
      this.config= new ClientConfig();
      this.client=ClientBuilder.newClient(config);
      this.target = client.target(ARestClient.getBaseURI());           
    }   
    
    
    private static URI getBaseURI() {        
        String Url= "http://gturnquist-quoters.cfapps.io/api/random";       
        return UriBuilder.fromUri( Url).build();
    } 
        
  public String read() {        
          
        // Get JSON for application
        String jsonResponse = target.request()
                .accept(MediaType.APPLICATION_JSON).get(String.class);

        System.out.println(jsonResponse); 
        return jsonResponse;
   }
   
    @Schedule(hour = "*", minute = "*", second = "*/5", info = "Every 5 second timer")
    public void automaticallyScheduled() {
    
        // do something 
        //  System.out.println( this.read());
        
    } 
 
}   

