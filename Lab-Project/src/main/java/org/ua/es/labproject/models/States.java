package org.ua.es.labproject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.LinkedList;
import java.util.List;

/**
 * labproject - States <br>
 *
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

    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public List<State> getStates() {
        return parsed_states;
    }

    public void setStates(List<List> states) {
        // Parsing the states from a list of generics into a list of state objects

        parsed_states = new LinkedList<>();
        for (List state : states ) {
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
