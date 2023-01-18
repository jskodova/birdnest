package com.birdnest.app;

import com.birdnest.app.model.Drone;
import com.birdnest.app.service.DroneService;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.*;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) {
        TimerTask task = new TimerTask() {
            public void run() {
                try {
                    //Send HTTP GET Request
                    HttpClient httpClient = HttpClient.newHttpClient();
                    HttpResponse response = httpClient.send(HttpRequest.newBuilder()
                                    .GET()
                                    .uri(URI.create("http://assignments.reaktor.com/birdnest/drones"))
                                    .header("accept", "application/xml")
                                    .build(),
                            HttpResponse.BodyHandlers.ofString());

                    //Handle response
                    if (response.statusCode() != 200) {
                        throw new RuntimeException("Failed : HTTP error code : " + response.statusCode());
                    }

                    //Parse response
                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder builder = factory.newDocumentBuilder();
                    Document doc = builder.parse(new InputSource(new StringReader(response.body().toString())));

                    NodeList nList = doc.getElementsByTagName("drone");

                    //Map xml to Drone objects
                    List<Drone> drones = DroneService.mapXmlToDrones(nList);
                    List<Drone> violations = DroneService.findViolations(drones);
                    List<Drone> dronesWithPilots = DroneService.findPilots(violations);

                    //Save Drone objects to database
                    //Create a connection to the database first
                    String connectionUrl = "jdbc:postgres://skodovajarmilatt:KvNA72zFsdey@ep-royal-paper-930875.eu-central-1.aws.neon.tech/neondb";
                    String username = "skodovajarmilatt";
                    String password = "KvNA72zFsdey";
                    try (Connection connection = DriverManager.getConnection(connectionUrl, username, password)) {
                        String dropDB = "UPDATE drones SET expiration_time = DATE_ADD(NOW(), INTERVAL 10 MINUTE) WHERE expiration_time IS NULL OR expiration_time < NOW();";
                        //Create the database table if it doesn't exist
                        String createTableSQL = "CREATE TABLE IF NOT EXISTS drones (" +
                                "serialNumber VARCHAR(15) NOT NULL," +
                                "manufacturer VARCHAR(25) NOT NULL," +
                                "model VARCHAR(25) NOT NULL," +
                                "positionX FLOAT NOT NULL," +
                                "positionY FLOAT NOT NULL," +
                                "pilotId VARCHAR(15)," +
                                "firstName VARCHAR(50)," +
                                "lastName VARCHAR(50)," +
                                "phoneNumber VARCHAR(15)," +
                                "email VARCHAR(50)" +
                                ")";
                        for (Drone drone : dronesWithPilots) {
                            String query = "INSERT INTO drones (serialNumber, manufacturer, model, positionX, positionY, pilotId, firstName, lastName, phoneNumber, email) VALUES ((?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                        PreparedStatement statement = connection.prepareStatement(query);
                        PreparedStatement stmnt = connection.prepareStatement(dropDB);
                        PreparedStatement create = connection.prepareStatement(createTableSQL);
                        statement.setString(1, drone.getSerialNumber());
                        statement.setString(2, drone.getManufacturer());
                        statement.setString(3, drone.getModel());
                        statement.setFloat(4, drone.getPositionX());
                        statement.setFloat(5, drone.getPositionY());
                        statement.setString(6, drone.getPilotId());
                        statement.setString(7, drone.getFirstName());
                        statement.setString(8, drone.getLastName());
                        statement.setString(9, drone.getPhoneNumber());
                        statement.setString(10, drone.getEmail());
                        create.executeUpdate();
                        statement.executeUpdate();
                        stmnt.executeUpdate();

                        ResultSet resultSet = statement.executeQuery("SELECT * FROM drones");
                    }
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }


                } catch (IOException | SAXException | ParserConfigurationException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 0, 2000);
    }

}