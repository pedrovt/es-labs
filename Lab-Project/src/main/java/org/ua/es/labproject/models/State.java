package org.ua.es.labproject.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * labproject - State <br>
 * Based on given examples <br>
 * @author Pedro Teixeira pedro.teix@ua.pt
 * @version 1.0 - February 22, 2020
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class State {

    private int time;
    private List states;

    public State() {

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

    public void setStates(List states) {
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
