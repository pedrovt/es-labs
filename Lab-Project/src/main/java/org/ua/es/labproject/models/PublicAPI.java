package org.ua.es.labproject.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * labproject - PublicAPI <br>
 * Based on given examples <br>
 * @author Pedro Teixeira pedro.teix@ua.pt
 * @version 1.0 - February 22, 2020
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PublicAPI {

    private int count;
    private List entries;

    public PublicAPI () {

    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List getEntries() {
        return entries;
    }

    public void setEntries(List entries) {
        this.entries = entries;
    }

    @Override
    public String toString() {
        return "State{" +
                "time='" + count + '\'' +
                ", value=" + entries +
                '}';
    }
}
