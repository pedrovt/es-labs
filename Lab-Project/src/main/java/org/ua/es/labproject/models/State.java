package org.ua.es.labproject.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * labproject - PublicAPI_Entry <br>
 * Based on given examples <br>
 * @author Pedro Teixeira pedro.teix@ua.pt
 * @version 1.0 - February 22, 2020
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class State {

    private String icao24;
    private String callsign;
    private String origin_country;
    private int time_position;
    private int last_contact;
    private float longitude;
    private float latitude;
    private float baro_altitude;
    private String on_ground;
    private float velocity;
    private float true_track;
    private float vertical_rate;
    private String sensors;
    private float geo_altitude;
    private String squawk;
    private String spi;
    private int position_source;


    public State() {
    }

    public State(List state) {
        setIcao24(state.get(0));
        setCallsign(state.get(1));
        setOrigin_country(state.get(2));
        setTime_position(state.get(3));
        setLast_contact(state.get(4));
        setLongitude(state.get(5));
        setLatitude(state.get(6));
        setBaro_altitude(state.get(7));
        setOn_ground(state.get(8));
        setVelocity(state.get(9));
        setTrue_track(state.get(10));
        setVertical_rate(state.get(11));
        setSensors(state.get(12));
        setGeo_altitude(state.get(13));
        setSquawk(state.get(14));
        setSpi(state.get(15));
        setPosition_source(state.get(16));
    }

    public String getIcao24() {
        return icao24;
    }

    public void setIcao24(Object icao24) {
        if (icao24 == null) {
            this.icao24 = "";
        }
        else {
            this.icao24 = icao24.toString();
        }
    }

    public String getCallsign() {
        return callsign;
    }

    public void setCallsign(Object callsign) {
        if (callsign == null) {
            this.callsign = "";
        }
        else {
            this.callsign = callsign.toString();
        }
    }

    public String getOrigin_country() {
        return origin_country;
    }

    public void setOrigin_country(Object origin_country) {
        if (origin_country == null) {
            this.origin_country = "";
        }
        else {
            this.origin_country = origin_country.toString();
        }
    }

    public int getTime_position() {
        return time_position;
    }

    public void setTime_position(Object time_position) {
        try {
            this.time_position = Integer.parseInt(time_position.toString());
        }
        catch (Exception e) {
            this.time_position = 0;
        }
    }

    public int getLast_contact() {
        return last_contact;
    }

    public void setLast_contact(Object last_contact) {
        try {
            this.last_contact = Integer.parseInt(last_contact.toString());
        }
        catch (Exception e) {
            this.last_contact = 0;
        }
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(Object longitude) {
        try {
            this.longitude = Float.parseFloat(longitude.toString());
        }
        catch (Exception e) {
            this.longitude = 0;
        }
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(Object latitude) {
        try {
            this.latitude = Float.parseFloat(latitude.toString());
        }
        catch (Exception e) {
            this.latitude = 0;
        }
    }

    public float getBaro_altitude() {
        return baro_altitude;
    }

    public void setBaro_altitude(Object baro_altitude) {
        try {
            this.baro_altitude = Float.parseFloat(baro_altitude.toString());
        }
        catch (Exception e) {
            this.baro_altitude = 0;
        }
    }

    public String getOn_ground() {
        return on_ground;
    }

    public void setOn_ground(Object on_ground) {
        if (on_ground == null) {
            this.on_ground = "";
        }
        else {
            this.on_ground = on_ground.toString();
        }
    }

    public float getVelocity() {
        return velocity;
    }

    public void setVelocity(Object velocity) {
        try {
            this.velocity = Float.parseFloat(velocity.toString());
        }
        catch (Exception e) {
            this.velocity = 0;
        }
    }

    public float getTrue_track() {
        return true_track;
    }

    public void setTrue_track(Object true_track) {
        try {
            this.true_track = Float.parseFloat(true_track.toString());
        }
        catch (Exception e) {
            this.true_track = 0;
        }
    }

    public float getVertical_rate() {
        return vertical_rate;
    }

    public void setVertical_rate(Object vertical_rate) {
        try {
            this.vertical_rate = Float.parseFloat(vertical_rate.toString());
        }
        catch (Exception e) {
            this.vertical_rate = 0;
        }
    }

    public String getSensors() {
        return sensors;
    }

    public void setSensors(Object sensors) {
        if (sensors == null) {
            this.sensors = "";
        }
        else {
            this.sensors = sensors.toString();
        }
    }

    public float getGeo_altitude() {
        return geo_altitude;
    }

    public void setGeo_altitude(Object geo_altitude) {
        try {
            this.geo_altitude = Float.parseFloat(geo_altitude.toString());
        }
        catch (Exception e) {
            this.geo_altitude = 0;
        }
    }

    public String getSquawk() {
        return squawk;
    }

    public void setSquawk(Object squawk) {
        if (squawk == null) {
            this.squawk = "";
        }
        else {
            this.squawk = squawk.toString();
        }
    }

    public String getSpi() {
        return spi;
    }

    public void setSpi(Object spi) {
        if (spi == null) {
            this.spi = "";
        }
        else {
            this.spi = spi.toString();
        }
    }

    public int getPosition_source() {
        return position_source;
    }

    public void setPosition_source(Object position_source) {
        try {
            this.position_source = Integer.parseInt(position_source.toString());
        }
        catch (Exception e) {
            this.position_source = 0;
        }
    }

    @Override
    public String toString() {
        return "Value{" +
                "icao24='" + icao24 + '\'' +
                ", callsign='" + callsign + '\'' +
                ", origin_country='" + origin_country + '\'' +
                ", time_position=" + time_position +
                ", last_contact=" + last_contact +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", baro_altitude=" + baro_altitude +
                ", on_groudn=" + on_ground +
                ", velocity=" + velocity +
                ", true_track=" + true_track +
                ", vertical_rate=" + vertical_rate +
                ", sensors='" + sensors + '\'' +
                ", geo_altitude=" + geo_altitude +
                ", squawk='" + squawk + '\'' +
                ", spi=" + spi +
                ", position_source=" + position_source +
                '}';
    }

}


