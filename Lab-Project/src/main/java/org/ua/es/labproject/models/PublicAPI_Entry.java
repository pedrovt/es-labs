package org.ua.es.labproject.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * labproject - PublicAPI_Entry <br>
 * Based on given examples <br>
 * @author Pedro Teixeira pedro.teix@ua.pt
 * @version 1.0 - February 22, 2020
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PublicAPI_Entry {

    private String API;
    private String Description;
    private String Auth;
    private String HTTPS;
    private String Cors;
    private String Link;
    private String Category;

    public PublicAPI_Entry() {
    }

    public String getAPI() {
        return this.API;
    }

    public String getDescription() {
        return this.Description;
    }

    public void setAPI(String API) {
        this.API = API;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public String getAuth() {
        return Auth;
    }

    public void setAuth(String auth) {
        Auth = auth;
    }

    public String getHTTPS() {
        return HTTPS;
    }

    public void setHTTPS(String HTTPS) {
        this.HTTPS = HTTPS;
    }

    public String getCors() {
        return Cors;
    }

    public void setCors(String cors) {
        Cors = cors;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    @Override
    public String toString() {
        return "PublicAPI_Entry{" +
                "API='" + API + '\'' +
                ", Description='" + Description + '\'' +
                ", Auth='" + Auth + '\'' +
                ", HTTPS='" + HTTPS + '\'' +
                ", Cors='" + Cors + '\'' +
                ", Link='" + Link + '\'' +
                ", Category='" + Category + '\'' +
                '}';
    }
}


