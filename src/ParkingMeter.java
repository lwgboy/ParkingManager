import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import static java.lang.Double.parseDouble;

public class ParkingMeter {

    static void checkIn (){
        UserInterface.getCheckInHeader();
        UserInterface.printRequest("plate");
        String inputLP = UserInput.input("plate");

        int index = ParkingLot.findPlate(inputLP);

        if (index == -1){
            UserInterface.printRequest("type");
            String inputVT = UserInput.input("type");
            ParkingLot.in(inputLP, inputVT);
            UserInterface.printReadyToPark(inputLP, inputVT);
        } else {
            UserInterface.printError("parked");
        }
    }

    static void checkOut (){
        UserInterface.getCheckOutHeader();
        UserInterface.printRequest("plate");
        String inputLP = UserInput.input("plate");

        int index = ParkingLot.findPlate(inputLP);

        if (index == -1){
            UserInterface.printError("notFound");
        } else {
            Vehicle vehicle = ParkingLot.getInfo(index);

            LocalDateTime timeIn = vehicle.getEntranceTime();
            LocalDateTime timeOut = Clock.time();

            int deltaMin = (int)timeIn.until(timeOut, ChronoUnit.MINUTES);

            double price = calculatePrice(deltaMin, vehicle.getVehicleType());

            UserInterface.printCheckOut("timeAndPrice", deltaMin, price);

            double payment = 0;

            while (payment < price){
                UserInterface.printRequest("payment");
                payment = parseDouble(UserInput.input("payment"));
                if (payment < price){
                    UserInterface.printError("lowPayment");
                }
            }

            UserInterface.printCheckOut("change", 0, (payment - price));

            ParkingLot.out(index, timeOut, price, payment);

            UserInterface.printCheckOut("print", 0, 0);
            String choice = UserInput.input("boolean");

            if (choice.equalsIgnoreCase("y") ||
                    choice.equalsIgnoreCase("s")){

                ReceiptPrinter.print(ParkingLot.getInfo(index));
            }

            UserInterface.printCheckOut("end", 0, 0);
        }
    }

    private static double calculatePrice(int minutes, String type) {
        double price;
        double initialPrice = 0;
        double usagePrice = 0;

        int chargePeriod = Settings.getChargePeriod();
        initialPrice = Settings.getFirstPrice(type);
        usagePrice = Settings.getPeriodPrice(type);

        if (minutes <= Settings.getFreeTime()){
            price = 0;
        } else if (minutes <= Settings.getFirstPeriod()){
            price = initialPrice;
        } else {
            minutes -= Settings.getFirstPeriod();
            price = (Math.ceil((float)minutes/chargePeriod)*usagePrice)+initialPrice;
        }

        return price;
    }

    static void getParkedVehicles (){
        int dataSize = ParkingLot.getDataSize();
        int parkedNumber = 0;

        ArrayList<Integer> parkedVehicles = new ArrayList<Integer>();

        for (int i=0; i<dataSize; i++){
            Vehicle vehicle = ParkingLot.getInfo(i);
            if (vehicle.isParked()){
                parkedVehicles.add(i);
                parkedNumber++;
            }
        }

        for (int i=0; i<parkedNumber; i++){
            Vehicle vehicle = ParkingLot.getInfo(parkedVehicles.get(i));
            UserInterface.printList(vehicle, i, parkedNumber);
        }
    }

}
