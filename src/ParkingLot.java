import java.time.LocalDateTime;
import java.util.ArrayList;

public class ParkingLot {
    private static ArrayList<Vehicle> vehicles = new ArrayList<>();

    static int findPlate (String plate){
        int index = -1;
        for (int i = 0; i<vehicles.size(); i++){
            if (vehicles.get(i).getLicensePlate().equalsIgnoreCase(plate)){
                if (vehicles.get(i).isParked()){
                    index = i;
                    break;
                }
            }
        }
        return index;
    }

    static void in (String plate, String type){
        vehicles.add(new Vehicle(plate, type));
    }

    static void out ( int index, LocalDateTime outTime, double price, double payment){
        vehicles.get(index).setParked(false);
        vehicles.get(index).setDepartureTime(outTime);
        vehicles.get(index).setPrice(price);
        vehicles.get(index).setPayment(payment);
    }

    static Vehicle getInfo (int index){
        return vehicles.get(index);
    }

    static int getDataSize (){
        return vehicles.size();
    }

    static void parseData (int id,
                         String plate,
                         String type,
                         LocalDateTime timeIn,
                         LocalDateTime timeOut,
                         double price,
                         double payment,
                         boolean parked){

        vehicles.add(new Vehicle(plate, type));
        int index = getDataSize() - 1;
        vehicles.get(index).setParkingID(id);
        vehicles.get(index).setEntranceTime(timeIn);
        vehicles.get(index).setDepartureTime(timeOut);
        vehicles.get(index).setPrice(price);
        vehicles.get(index).setPayment(payment);
        vehicles.get(index).setParked(parked);

    }

}
