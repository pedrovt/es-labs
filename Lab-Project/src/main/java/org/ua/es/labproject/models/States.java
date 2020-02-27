package org.ua.es.labproject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.LinkedList;
import java.util.List;

/**
 * labproject - State <br>
 * Based on given examples <br>
 * @author Pedro Teixeira pedro.teix@ua.pt
 * @version 1.0 - February 22, 2020
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class States {

    private int time;
    private List<List> states;
    @JsonIgnore
    private List<State> parsed_states;

    public States() {
        System.out.println("Creating a state");
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public List getStates() {
        return states;
    }

    public void setStates(List<List> states) {

        System.out.println("Setting a state");
        // Parsing the states from a list of generics into a list of state objects

        parsed_states = new LinkedList<>();
        for (List state : states ) {
            System.out.println(" STATE IS " + state);
            parsed_states.add(new State(state));
        }

        this.parsed_states = parsed_states;
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
