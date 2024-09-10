abstract class Vehicle {
    public static int instanceCount = 0;
    public String type;
    public String brand;
    public double tank;
    public int mileage;
    public int id;

    public Vehicle(String type, String brand, double tank, int mileage, int id) {
        this.type = type;
        this.brand = brand;
        this.tank = tank;
        this.mileage = mileage;
        this.id = id;
        instanceCount++;
    }

    public static int getCount() {
        return instanceCount;
    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getTank() {
        return tank;
    }

    public void setTank(double tank) {
        this.tank = tank;
    }

    public int getKmh() {
        return mileage;
    }

    public void setKmh(int kmh) {
        this.mileage = kmh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }
}
