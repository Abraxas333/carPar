public class Motorcycle extends Vehicle {
    private int engineCapacity;


    public Motorcycle(String type, String brand, double tank, int mileage, int id, int engineCapacity) {
        super(type, brand, tank, mileage, id);
        this.engineCapacity = engineCapacity;

    }

    public int getEngineCapacity() { return engineCapacity; }

    public void setEngineCapacity(int engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

}
