import java.time.LocalDateTime;

public class Vehicle {

    private static int idCounter = 0;

    private int parkingID; //defines and organize the list off all vehicles that entered the lot

    private String licensePlate;
    private String vehicleType;
    private LocalDateTime entranceTime;
    private LocalDateTime departureTime;

    private double price;
    private double payment;
    private boolean parked;

    public Vehicle(String inputPlate, String inputType) { // constructor for vehicles

        this.licensePlate = inputPlate.toUpperCase();
        this.vehicleType = inputType.toUpperCase();
        this.entranceTime = Clock.time();
        this.parked = true; // since the vehicle is created during parking, parked = true

        idCounter ++; // increments the static counter
        this.parkingID = idCounter;
    }

    public int getID(){
        return parkingID;
    }

    public String getLicensePlate(){
        return licensePlate;
    }

    public String getVehicleType(){
        return vehicleType;
    }

    public LocalDateTime getEntranceTime(){
        return entranceTime;
    }

    public LocalDateTime getDepartureTime(){
        return departureTime;
    }

    public double getPrice(){
        return price;
    }

    public double getPayment(){
        return payment;
    }

    public boolean isParked(){
        return parked;
    }

    public static void setIdCounter(int loadedCounter) {
        idCounter = loadedCounter;
    }

    public void setParkingID (int id){
        this.parkingID = id;
    }

    public void setEntranceTime (LocalDateTime inTime) {
        this.entranceTime = inTime;
    }

    public void setDepartureTime(LocalDateTime outTime) {
        this.departureTime = outTime;
    }

    public void setPrice(double finalPrice) {
        this.price = finalPrice;
    }

    public void setPayment(double pay) {
        this.payment = pay;
    }

    public void setParked(boolean parked) {
        this.parked = parked;
    }
}
