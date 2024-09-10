import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataSaver {

    public static void saveVehicle(Vehicle vehicle) {
        String query = "";

        if (vehicle instanceof Car) {
            query = "INSERT INTO cars (type, brand, tank, mileage, number_of_doors, has_air_conditioning, is_electric) VALUES (?, ?, ?, ?, ?, ?, ?)";
        } else if (vehicle instanceof Truck) {
            query = "INSERT INTO trucks (type, brand, tank, mileage, max_load_capacity, number_of_axles) VALUES (?, ?, ?, ?, ?, ?)";
        } else if (vehicle instanceof Motorcycle) {
            query = "INSERT INTO motorcycles (type, brand, tank, mileage, engine_capacity) VALUES (?, ?, ?, ?, ?)";
        } else if (vehicle instanceof Bike) {
            query = "INSERT INTO bikes (type, brand, mileage, gear_count, is_electric) VALUES (?, ?, ?, ?, ?)";
        } else {
            System.out.println("Unknown vehicle type, cannot save.");
            return;
        }

        try (Connection conn = DatabaseConnection.connect()) {
            if (conn != null) {
                PreparedStatement stmt = conn.prepareStatement(query);

                // Set common fields
                stmt.setString(1, vehicle.getType());
                stmt.setString(2, vehicle.getBrand());
                stmt.setDouble(3, vehicle.getTank());
                stmt.setInt(4, vehicle.getKmh());

                // Set specific fields based on the vehicle type
                if (vehicle instanceof Car) {
                    Car car = (Car) vehicle;
                    stmt.setInt(5, car.getNumberOfDoors());
                    stmt.setBoolean(6, car.getHasAirConditioning());
                    stmt.setBoolean(7, car.getIsElectric());
                } else if (vehicle instanceof Truck) {
                    Truck truck = (Truck) vehicle;
                    stmt.setDouble(5, truck.getMaxLoadCapacity());
                    stmt.setInt(6, truck.getNumberOfAxles());
                } else if (vehicle instanceof Motorcycle) {
                    Motorcycle motorcycle = (Motorcycle) vehicle;
                    stmt.setInt(5, motorcycle.getEngineCapacity());
                } else if (vehicle instanceof Bike) {
                    Bike bike = (Bike) vehicle;
                    stmt.setInt(5, bike.getGearCount());
                    stmt.setBoolean(6, bike.getIsElectricBike());
                }


                int rowsInserted = stmt.executeUpdate();

                if (rowsInserted > 0) {
                    System.out.println("A new vehicle was inserted successfully!");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error while inserting data: " + e.getMessage());
        }
    }
}
