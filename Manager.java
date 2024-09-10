import java.util.HashMap;
import java.util.Scanner;


public class Manager {

    public static HashMap<Integer, Vehicle> vehiclesMap = new HashMap<>();


    public static String addVehicle(Vehicle vehicle) {
        if (vehicle != null) {
            vehiclesMap.put(vehicle.getId(), vehicle);
            System.out.println("Vehicle added successfully.");
        } else {
            System.out.println("Failed to add vehicle.");
        }
        return null;
    }
    public static Vehicle createVehicle(Scanner sc) {

        System.out.println("Enter vehicle type (PKW, LKW, Motorcycle, Bike):");
        String type = sc.nextLine().trim().toLowerCase();

        System.out.println("Enter brand:");
        String brand = sc.nextLine();

        System.out.println("Enter mileage:");
        int mileage = sc.nextInt();

        int id;
        System.out.println("Enter vehicle ID:");
        id = sc.nextInt();

        sc.nextLine();


        switch (type) {
            case "pkw":
                System.out.println("Enter tank/battery capacity:");
                double tank = sc.nextDouble();
                System.out.println("Enter number of doors:");
                int numberOfDoors = sc.nextInt();
                System.out.println("Has air conditioning (yes/no)?");
                sc.nextLine();  // Consume newline
                boolean hasAirConditioning = sc.nextLine().equalsIgnoreCase("yes");
                System.out.println("Is it electric (yes/no)?");
                boolean isElectric = sc.nextLine().equalsIgnoreCase("yes");
                return new Car(type, brand, tank, mileage, id, numberOfDoors, hasAirConditioning, isElectric);

            case "lkw":
                System.out.println("Enter tank capacity:");
                tank = sc.nextDouble();
                System.out.println("Enter max load capacity:");
                double maxLoadCapacity = sc.nextDouble();
                System.out.println("Enter number of axles:");
                int numberOfAxles = sc.nextInt();
                return new Truck(type, brand, tank, mileage, id, maxLoadCapacity, numberOfAxles);

            case "motorcycle":
                System.out.println("Enter tank capacity:");
                tank = sc.nextDouble();
                System.out.println("Enter engine capacity:");
                int engineCapacity = sc.nextInt();
                return new Motorcycle(type, brand, tank, mileage, id, engineCapacity);

            case "bike":
                System.out.println("Enter gear count:");
                int gearCount = sc.nextInt();
                System.out.println("Is it electric (yes/no)?");
                sc.nextLine();
                boolean isElectricBike = sc.nextLine().equalsIgnoreCase("yes");
                return new Bike(type, brand, 0, mileage, id, gearCount, isElectricBike);

            default:
                System.out.println("Invalid vehicle type.");
                return null;
        }
    }

    public static void listVehicles() {
        if (vehiclesMap.isEmpty()) {
            System.out.println("No vehicles available.");
            return;
        }

        for (Vehicle v : vehiclesMap.values()) {

            System.out.println("ID: " + v.getId() + ", Type: " + v.getType() + ", Brand: " + v.getBrand() + ", Tank: " + v.getTank() + ", Mileage: " + v.getKmh());
            if (v instanceof Car) {
                Car car = (Car) v;
                System.out.println("Number of doors: " + car.getNumberOfDoors() + ", Air conditioning: " + (car.getHasAirConditioning() ? "Yes" : "No") + ", Electric: " + (car.getIsElectric() ? "Yes" : "No"));
            } else if (v instanceof Truck) {
                Truck truck = (Truck) v;
                System.out.println("Max load capacity: " + truck.getMaxLoadCapacity() + ", Number of axles: " + truck.getNumberOfAxles() + ", Current load: " + truck.getCurrentLoad());
            }

            System.out.println();
        }
    }


    public static void retrieveVehicleInfo(Vehicle vehicle) {
        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        // Display common attributes
        System.out.println("Vehicle ID: " + vehicle.getId());
        System.out.println("Type: " + vehicle.getType());
        System.out.println("Brand: " + vehicle.getBrand());
        System.out.println("Tank Capacity: " + vehicle.getTank());
        System.out.println("Mileage: " + vehicle.getKmh());


        if (vehicle instanceof Car) {
            Car car = (Car) vehicle;
            System.out.println("Number of doors: " + car.getNumberOfDoors());
            System.out.println("Has Air Conditioning: " + (car.getHasAirConditioning() ? "Yes" : "No"));
            System.out.println("Is Electric: " + (car.getIsElectric() ? "Yes" : "No"));
        } else if (vehicle instanceof Truck) {
            Truck truck = (Truck) vehicle;
            System.out.println("Max Load Capacity: " + truck.getMaxLoadCapacity() + " tons");
            System.out.println("Number of Axles: " + truck.getNumberOfAxles());
            System.out.println("Current Load: " + truck.getCurrentLoad() + " tons");
        } else if (vehicle instanceof Motorcycle) {
            Motorcycle motorcycle = (Motorcycle) vehicle;
            System.out.println("Engine Capacity: " + motorcycle.getEngineCapacity() + " cc");
        } else if (vehicle instanceof Bike) {
            Bike bike = (Bike) vehicle;
            System.out.println("Gear Count: " + bike.getGearCount());
            System.out.println("Is Electric: " + (bike.getIsElectricBike() ? "Yes" : "No"));
        } else {
            System.out.println("Unknown vehicle type.");
        }
        System.out.println();
    }

    public static Vehicle findVehicleById(int id) {
        return vehiclesMap.get(id);  // O(1) lookup in HashMap
    }

    public static void updateVehicle(int id, Scanner sc) {
        Vehicle vehicle = vehiclesMap.get(id);  // O(1) lookup
        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        System.out.println("Updating vehicle ID: " + vehicle.getId());

        System.out.println("Enter new type (current: " + vehicle.getType() + "):");
        String newType = sc.nextLine();
        vehicle.setType(newType);

        System.out.println("Enter new brand (current: " + vehicle.getBrand() + "):");
        String newBrand = sc.nextLine();
        vehicle.setBrand(newBrand);

        System.out.println("Enter new tank capacity (current: " + vehicle.getTank() + "):");
        String newTank = sc.nextLine();
        try {
            vehicle.setTank(Double.parseDouble(newTank));
        } catch (NumberFormatException e) {
            System.out.println("Invalid input detected. Please input a number.");
        }

        System.out.println("Enter new mileage (current: " + vehicle.getKmh() + "):");
        String newKmh = sc.nextLine();
        try {
            vehicle.setKmh(Integer.parseInt(newKmh));
        } catch (NumberFormatException e) {
            System.out.println("Invalid input detected. Please input a number.");
        }

        System.out.println("Vehicle updated successfully.");
    }

    public static void deleteVehicleById(int id) {
        Vehicle removedVehicle = vehiclesMap.remove(id);
        if (removedVehicle != null) {
            System.out.println("Vehicle removed successfully.");
        } else {
            System.out.println("Vehicle not found.");
        }
    }
}


