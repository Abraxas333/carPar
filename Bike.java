public class Bike extends Vehicle {
    private int gearCount;
    private boolean isElectricBike;

    public Bike(String type, String brand, double tank, int mileage, int id, int gearCount, boolean isElectricBike) {
        super(type, brand, tank, mileage, id);
        this.gearCount = gearCount;
        this.isElectricBike = isElectricBike;
    }

    public void switchGear(int gear) {
        if (gear > 0 && gear <= gearCount) {
            System.out.println("Switching to gear: " + gear);
        } else {
            System.out.println("Invalid gear");
        }
    }
    public int getGearCount()  { return gearCount; }

    public void setGearCount(int gearCount) {
        this.gearCount = gearCount;
    }

    public boolean getIsElectricBike() { return isElectricBike; }

    public void setIsElectricBike(boolean isElectricBike) {
        this.isElectricBike = isElectricBike;
    }
}
