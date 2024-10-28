package Controllers;

import Entities.Driver;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import Extensions.RegexFormats;

public class DriverController {
    private final List<Driver> driverList = new ArrayList<>();

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
    }
}
