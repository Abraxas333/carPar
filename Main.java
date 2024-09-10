import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;



public class Main {
    public static void main(String[] args) {

        DataRetriever.getCars();
        Scanner sc = new Scanner(System.in);
        boolean finished = false;

        while (!finished) {
            System.out.println("To create a vehicle input 'add', to continue to the Vehicles management dialogue type 'manage', to end the program type 'end'.");
            String input = sc.nextLine();

            if (input.equalsIgnoreCase("add")) {
                Vehicle vehicle = Manager.createVehicle(sc);
                if (vehicle != null) {
                    Manager.addVehicle(vehicle);
                    DataSaver.saveVehicle(vehicle);
                } else {
                    System.out.println("Vehicle creation failed.");
                }
            } else if (input.equalsIgnoreCase("manage")) {
                boolean managing = true;
                while (managing) {
                    System.out.println("Choose from available options: list, update, retrieve, delete, end");
                    String action = sc.nextLine();

                    switch (action.toLowerCase()) {
                        case "list":
                            Manager.listVehicles();
                            break;
                        case "update":
                            System.out.println("Enter the ID of the vehicle to update:");
                            int updateId = sc.nextInt();
                            sc.nextLine();
                            Manager.updateVehicle(updateId, sc);
                            break;
                        case "retrieve":
                            System.out.println("Enter the ID of the vehicle to retrieve:");
                            int retrieveId = sc.nextInt();
                            sc.nextLine();
                            Vehicle vehicle = Manager.findVehicleById(retrieveId);
                            if (vehicle != null) {
                                Manager.retrieveVehicleInfo(vehicle);
                            } else {
                                System.out.println("Vehicle not found.");
                            }
                            break;
                        case "delete":
                            System.out.println("Enter the ID of the vehicle to delete:");
                            int deleteId = sc.nextInt();
                            sc.nextLine();  
                            Manager.deleteVehicleById(deleteId);
                            break;
                        case "end":
                            managing = false;
                            break;
                        default:
                            System.out.println("Invalid action. Please try again.");
                            break;
                    }
                }
            } else if (input.equalsIgnoreCase("end")) {
                finished = true;
            } else {
                System.out.println("Invalid command. Please try again.");
            }
        }

        sc.close();
    }
}

