/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

// jfernan 2018 10 01
// based on http://www.vogella.com/tutorials/REST/article.html 


import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import com.fasterxml.jackson.jaxrs.annotation.JacksonFeatures;


/**
 *
 * @author jfern
 */
public class TodoTest {
     public static void main(String[] args) {
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target(getBaseURI());
        
        // Get JSON for application
        String jsonResponse = target.request()
                .accept(MediaType.APPLICATION_JSON).get(String.class);

        System.out.println(jsonResponse);
    }

    private static URI getBaseURI() {
        
        String Url= "http://gturnquist-quoters.cfapps.io/api/random";
        
        //String Url= "http://localhost:8080/com.vogella.jersey.jaxb";
        
        return UriBuilder.fromUri( Url).build();
    } 
}
