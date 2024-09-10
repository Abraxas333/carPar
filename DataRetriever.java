import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataRetriever {

    public static void getCars() {
        try (Connection conn = DatabaseConnection.connect()) {
            if (conn != null) {
                String query = "SELECT * from cars";

                PreparedStatement stmt = conn.prepareStatement(query);

                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    String type = rs.getString("type");
                    double tank = rs.getDouble("tank");
                    int mileage = rs.getInt("mileage");
                    int id = rs.getInt("id");
                    int numberOfDoors = rs.getInt("number_of_doors");
                    boolean hasAirConditioning = rs.getBoolean("has_air_conditioning");
                    boolean isElectric = rs.getBoolean("is_electric");
                    String brand = rs.getString("brand");

                    System.out.println("type: " + type + ", brand: " + brand + ", tank: " + tank + ", mileage: " + mileage + ", ID: " + id + ", number of doors: " + numberOfDoors + ", has air conditioning: " + hasAirConditioning + ", is electric: " + isElectric);
                }
                rs.close();
                stmt.close();

                }
            } catch (SQLException e) {
            System.out.println("Error while retrieving cars: " + e.getMessage());
        }
    }
}