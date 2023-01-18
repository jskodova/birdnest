package com.birdnest.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;
@Entity
public class Drone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String serialNumber;
    private String model;
    private String manufacturer;
    private String ipv4;
    private String ipv6;
    private String firmware;
    private float positionY;
    private float positionX;
    private float distance;
    private String pilotId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Drone drone = (Drone) o;
        return serialNumber == drone.serialNumber && positionY == drone.positionY && positionX == drone.positionX && distance == drone.distance && Objects.equals(model, drone.model) && Objects.equals(manufacturer, drone.manufacturer) && Objects.equals(ipv4, drone.ipv4) && Objects.equals(ipv6, drone.ipv6) && Objects.equals(firmware, drone.firmware);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serialNumber, model, manufacturer, ipv4, ipv6, firmware, positionY, positionX, distance);
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNum) {
        this.serialNumber = serialNum;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getIpv4() {
        return ipv4;
    }

    public void setIpv4(String ipv4) {
        this.ipv4 = ipv4;
    }

    public String getIpv6() {
        return ipv6;
    }

    public void setIpv6(String ipv6) {
        this.ipv6 = ipv6;
    }

    public String getFirmware() {
        return firmware;
    }

    public void setFirmware(String firmware) {
        this.firmware = firmware;
    }

    public float getPositionY() {
        return positionY;
    }

    public void setPositionY(float positionY) {
        this.positionY = positionY;
    }

    public float getPositionX() {
        return positionX;
    }

    public void setPositionX(float positionX) {
        this.positionX = positionX;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }


    public String getPilotId() {
        return pilotId;
    }

    public void setPilotId(String pilotId) {
        this.pilotId = pilotId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Drone(String serialNum, String model, String manufacturer, String ipv4, String ipv6, String firmware, float positionY, float positionX, float distance) {
        this.serialNumber = serialNum;
        this.model = model;
        this.manufacturer = manufacturer;
        this.ipv4 = ipv4;
        this.ipv6 = ipv6;
        this.firmware = firmware;
        this.positionY = positionY;
        this.positionX = positionX;
        this.distance = distance;
    }
    public Drone(String serialNum, String model, String manufacturer, float positionY, float positionX) {
        this.serialNumber = serialNum;
        this.model = model;
        this.manufacturer = manufacturer;
        this.positionY = positionY;
        this.positionX = positionX;
    }
    public Drone(String serialNum, String model, String manufacturer, float positionX, float positionY, float distance, String pilotId, String firstName, String lastName, String phoneNumber,String email) {
        this.serialNumber = serialNum;
        this.model = model;
        this.manufacturer = manufacturer;
        this.positionY = positionY;
        this.positionX = positionX;
        this.distance = distance;
        this.pilotId = pilotId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
    public Drone() {
    }

}
