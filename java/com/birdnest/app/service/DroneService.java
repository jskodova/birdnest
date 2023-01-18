package com.birdnest.app.service;

import com.birdnest.app.model.Drone;
import com.birdnest.app.RadiusCheck;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class DroneService {
    public static List<Drone> mapXmlToDrones(NodeList nList) {
        List<Drone> drones = new ArrayList<Drone>();
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                Drone drone = new Drone(
                        eElement.getElementsByTagName("serialNumber").item(0).getTextContent(),
                        eElement.getElementsByTagName("manufacturer").item(0).getTextContent(),
                        eElement.getElementsByTagName("model").item(0).getTextContent(),
                        Float.valueOf(eElement.getElementsByTagName("positionX").item(0).getTextContent()),
                        Float.valueOf(eElement.getElementsByTagName("positionY").item(0).getTextContent()));
                drones.add(drone);
            }
        }
        return drones;
    }


    public static List<Drone> findViolations(List<Drone> drones) {
        List<Drone> violations = new ArrayList<Drone>();
        for (Drone drone : drones) {
            float x = drone.getPositionX();
            float y = drone.getPositionY();
            if (!RadiusCheck.isInCircle(x, y)) {
                drone.setDistance(RadiusCheck.distancefromCircle(x, y));
                violations.add(drone);
            }
        }
        return violations;
    }
    public static List<Drone> findPilots(List<Drone> violations) {
        List<Drone> dronesWithPilots = new ArrayList<>();
        for (Drone drone : violations) {
            String pilotUrl = "https://assignments.reaktor.com/birdnest/pilots/" + drone.getSerialNumber();
            RestTemplate restTemplate = new RestTemplate();
            Drone dronePilot = restTemplate.getForObject(pilotUrl, Drone.class);
            Drone droneWithPilot = new Drone(drone.getSerialNumber(), drone.getModel(),drone.getManufacturer(), dronePilot.getPositionX(), dronePilot.getPositionY(), drone.getDistance(), dronePilot.getPilotId(), dronePilot.getFirstName(), dronePilot.getLastName(), dronePilot.getPhoneNumber(), dronePilot.getEmail());
            dronesWithPilots.add(droneWithPilot);

        }
        return dronesWithPilots;
    }

}
