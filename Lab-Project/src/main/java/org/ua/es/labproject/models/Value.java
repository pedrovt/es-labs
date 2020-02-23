package org.ua.es.labproject.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * labproject - PublicAPI_Entry <br>
 * Based on given examples <br>
 * @author Pedro Teixeira pedro.teix@ua.pt
 * @version 1.0 - February 22, 2020
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Value {

    private int icao24;
    private String callsign;
    private String origin_country;
    private int time_position;
    private int last_contact;
    private float longitude;
    private float latitude;
    private float baro_altitude;
    private boolean on_ground;
    private float velocity;
    private float true_track;
    private float vertical_rate;
    private String sensors;
    private float geo_altitude;
    private String squawk;
    private boolean spi;
    private int position_source;


    public Value() {
    }

    public int getIcao24() {
        return icao24;
    }

    public void setIcao24(int icao24) {
        this.icao24 = icao24;
    }

    public String getCallsign() {
        return callsign;
    }

    public void setCallsign(String callsign) {
        this.callsign = callsign;
    }

    public String getOrigin_country() {
        return origin_country;
    }

    public void setOrigin_country(String origin_country) {
        this.origin_country = origin_country;
    }

    public int getTime_position() {
        return time_position;
    }

    public void setTime_position(int time_position) {
        this.time_position = time_position;
    }

    public int getLast_contact() {
        return last_contact;
    }

    public void setLast_contact(int last_contact) {
        this.last_contact = last_contact;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getBaro_altitude() {
        return baro_altitude;
    }

    public void setBaro_altitude(float baro_altitude) {
        this.baro_altitude = baro_altitude;
    }

    public boolean isOn_ground() {
        return on_ground;
    }

    public void setOn_ground(boolean on_ground) {
        this.on_ground = on_ground;
    }

    public float getVelocity() {
        return velocity;
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }

    public float getTrue_track() {
        return true_track;
    }

    public void setTrue_track(float true_track) {
        this.true_track = true_track;
    }

    public float getVertical_rate() {
        return vertical_rate;
    }

    public void setVertical_rate(float vertical_rate) {
        this.vertical_rate = vertical_rate;
    }

    public String getSensors() {
        return sensors;
    }

    public void setSensors(String sensors) {
        this.sensors = sensors;
    }

    public float getGeo_altitude() {
        return geo_altitude;
    }

    public void setGeo_altitude(float geo_altitude) {
        this.geo_altitude = geo_altitude;
    }

    public String getSquawk() {
        return squawk;
    }

    public void setSquawk(String squawk) {
        this.squawk = squawk;
    }

    public boolean isSpi() {
        return spi;
    }

    public void setSpi(boolean spi) {
        this.spi = spi;
    }

    public int getPosition_source() {
        return position_source;
    }

    public void setPosition_source(int position_source) {
        this.position_source = position_source;
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


