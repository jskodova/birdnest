package com.birdnest.app.model;
<<<<<<< Updated upstream

import java.util.Objects;

=======
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
import lombok.Data;

@Data
>>>>>>> Stashed changes
public class Drone {
    private int serialNum;
    private String model;
    private String manufacturer;
    private String ipv4;
    private String ipv6;
    private String firmware;
    private long positionY;
    private long positionX;
    private long altitude;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Drone drone = (Drone) o;
        return serialNum == drone.serialNum && positionY == drone.positionY && positionX == drone.positionX && altitude == drone.altitude && Objects.equals(model, drone.model) && Objects.equals(manufacturer, drone.manufacturer) && Objects.equals(ipv4, drone.ipv4) && Objects.equals(ipv6, drone.ipv6) && Objects.equals(firmware, drone.firmware);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serialNum, model, manufacturer, ipv4, ipv6, firmware, positionY, positionX, altitude);
    }

<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
    public int getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(int serialNum) {
        this.serialNum = serialNum;
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

    public long getPositionY() {
        return positionY;
    }

    public void setPositionY(long positionY) {
        this.positionY = positionY;
    }

    public long getPositionX() {
        return positionX;
    }

    public void setPositionX(long positionX) {
        this.positionX = positionX;
    }

    public long getAltitude() {
        return altitude;
    }

    public void setAltitude(long altitude) {
        this.altitude = altitude;
    }

    public Drone(int serialNum, String model, String manufacturer, String ipv4, String ipv6, String firmware, long positionY, long positionX, long altitude) {
        this.serialNum = serialNum;
        this.model = model;
        this.manufacturer = manufacturer;
        this.ipv4 = ipv4;
        this.ipv6 = ipv6;
        this.firmware = firmware;
        this.positionY = positionY;
        this.positionX = positionX;
        this.altitude = altitude;
    }
    public Drone() {
    }

}
