package org.ua.es.labproject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.LinkedList;
import java.util.List;

/**
 * labproject - States <br>
 *
 * @author Paulo Vasconcelos paulobvasconcelos@gmail.com
 * @author Pedro Teixeira pedro.teix@ua.pt
 * @version 2.0 - March 11, 2020
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class APIResult {

    private int time;
    private List<List> states;
    @JsonIgnore
    private List<Flight> parsed_flights;

    public APIResult() {

    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public List<Flight> getStates() {
        return parsed_flights;
    }

    public void setStates(List<List> states) {
        // Parsing the states from a list of generics into a list of state objects

        parsed_flights = new LinkedList<>();
        for (List state : states ) {
            parsed_flights.add(new Flight(state));
        }

        this.parsed_flights = parsed_flights;
        this.states = states;
    }

    @Override
    public String toString() {
        return "State{" +
                "time='" + time + '\'' +
                ", value=" + states +
                '}';
    }
}
