package Controllers;

import Entities.Driver;

import java.util.List;
import java.util.UUID;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class DriverController {
    /*private final List<Driver> driverList = new ArrayList<>();

    public void CreateDriver(Driver driver) {
        if(isValidDriver(driver)) {
            Driver newDriver = new Driver(UUID.randomUUID(), driver.name(), driver.phone(),
                    driver.vehicleNumber(), driver.age());
            driverList.add(newDriver);
            System.out.println("Driver created successfully!");
        }
    }

    private boolean isValidDriver(Driver driver) {
        RegexFormats formats = new RegexFormats();
        if(driver.name() == null || driver.name().isEmpty() || !(driver.name().matches(formats.NameRegex))){
            System.out.println("Invalid Name.");
        }
        return true;
    }

    public Driver readDriver(UUID driverId) {
        return driverList.stream().filter(driver -> driver.id().equals(driverId)).findFirst().orElse(null);
    }

    public void updateDriver(UUID driverId, String name, String phone, String vehicleNumber, int age) {
        if (name == null || name.isEmpty()) {
            System.out.println("Invalid name.");
            return;
        }
        if (phone == null || phone.isEmpty()) {
            System.out.println("Invalid phone number.");
            return;
        }
        if (vehicleNumber == null || vehicleNumber.isEmpty()) {
            System.out.println("Invalid vehicle number.");
            return;
        }
        if (age <= 0) {
            System.out.println("Age must be greater than zero.");
            return;
        }

        for (int i = 0; i < driverList.size(); i++) {
            Driver driver = driverList.get(i);
            if (driver.id().equals(driverId)) {
                Driver updatedDriver = new Driver(driverId, name, phone, vehicleNumber, age);
                driverList.set(i, updatedDriver);
                System.out.println("Driver updated successfully!");
                return;
            }
        }
        System.out.println("Driver not found.");
    }

    public void deleteDriver(UUID driverId) {
        boolean removed = driverList.removeIf(driver -> driver.id().equals(driverId));
        if (removed) {
            System.out.println("Driver deleted successfully!");
        } else {
            System.out.println("Driver not found.");
        }
    }*/
    private static final String BASE_URL = "http://localhost:8081/driver";
    private final Gson gson = new Gson();

    // Create a new driver
    public String createDriver(String name, String phone, String vehicleNumber, int age) throws IOException {
        URL url = new URL(BASE_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "text/plain");
        connection.setDoOutput(true);

        String requestData = String.join(",", name, phone, vehicleNumber, String.valueOf(age));
        try (OutputStream os = connection.getOutputStream()) {
            os.write(requestData.getBytes());
            os.flush();
        }

        return readResponse(connection);
    }

    // Get driver by ID
    public Driver getDriver(UUID driverId) throws IOException {
        URL url = new URL(BASE_URL + "?id=" + driverId);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        String response = readResponse(connection);

        if (response.startsWith("{")) {
            // Parse the JSON object into a Driver
            return gson.fromJson(response, Driver.class);
        } else {
            // Handle cases where the response is a plain string
            System.out.println("Server response: " + response);
            return new Driver(driverId, "Unknown", response, "N/A", 0); // Or handle as per your logic
        }
        //return gson.fromJson(response, Driver.class);
    }

    public List<Driver> listDrivers() throws IOException {
        URL url = new URL(BASE_URL + "/list"); // Define the endpoint for listing drivers
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                // Parse the JSON response into a List<Driver>
                return gson.fromJson(response.toString(), new TypeToken<List<Driver>>() {}.getType());
            } else {
                throw new IOException("Failed to retrieve drivers. HTTP Code: " + connection.getResponseCode());
            }
        } finally {
            connection.disconnect();
        }
    }

    // Update an existing driver
    public String updateDriver(UUID driverId, Driver driver) throws IOException {
        URL url = new URL(BASE_URL + "?id=" + driverId);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Content-Type", "text/plain");
        connection.setDoOutput(true);

        String requestData = String.join(",", driver.getName(), driver.getPhone(), driver.getVehicleNumber(), String.valueOf(driver.getAge()));
        try (OutputStream os = connection.getOutputStream()) {
            os.write(requestData.getBytes());
            os.flush();
        }

        return readResponse(connection);
    }

    // Delete a driver by ID
    public String deleteDriver(UUID driverId) throws IOException {
        URL url = new URL(BASE_URL + "?id=" + driverId);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("DELETE");

        return readResponse(connection);
    }

    // Helper method to read the response from the server
    private String readResponse(HttpURLConnection connection) throws IOException {
        int responseCode = connection.getResponseCode();
        InputStream inputStream = (responseCode >= 200 && responseCode < 300)
                ? connection.getInputStream()
                : connection.getErrorStream();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            return response.toString();
        } finally {
            connection.disconnect();
        }
    }
}
