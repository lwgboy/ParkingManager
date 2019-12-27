import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class DataManager {

    static void saveAll (){
        int dataSize = ParkingLot.getDataSize();
        StringBuilder dataStream = new StringBuilder();

        for (int i=0; i<dataSize; i++){
            Vehicle vehicle = ParkingLot.getInfo(i);
            dataStream.append(vehicle.getID());
            dataStream.append("\t\t");
            dataStream.append(vehicle.getLicensePlate());
            dataStream.append("\t\t");
            dataStream.append(vehicle.getVehicleType());
            dataStream.append("\t\t");
            dataStream.append(vehicle.getEntranceTime());
            dataStream.append("\t\t");
            dataStream.append(vehicle.getDepartureTime());
            dataStream.append("\t\t");
            dataStream.append(vehicle.getPrice());
            dataStream.append("\t\t");
            dataStream.append(vehicle.getPayment());
            dataStream.append("\t\t");
            dataStream.append(vehicle.isParked());
            dataStream.append("\n");
        }

        try {
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter("out/data/parking_data.txt"));
            writer.write(String.valueOf(dataStream));
            writer.close();
        } catch (IOException e) {
            System.out.println("Unable to Save Parking Data");
        }

        StringBuilder settings = new StringBuilder();

        settings.append(Settings.getFreeTime());
        settings.append("\t\t");
        settings.append(Settings.getFirstPeriod());
        settings.append("\t\t");
        settings.append(Settings.getChargePeriod());
        settings.append("\t\t");
        settings.append(Settings.getFirstPrice("car"));
        settings.append("\t\t");
        settings.append(Settings.getFirstPrice("motorcycle"));
        settings.append("\t\t");
        settings.append(Settings.getPeriodPrice("car"));
        settings.append("\t\t");
        settings.append(Settings.getPeriodPrice("motorcycle"));

        try {
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter("out/data/parking_settings.txt"));
            writer.write(String.valueOf(settings));
            writer.close();
        } catch (IOException e) {
            System.out.println("Unable to Save Parking Settings");
        }

    }

    static void loadData (){
        File fileToRead = new File("out/data/parking_data.txt");
        File settingsFile = new File("out/data/parking_settings.txt");

        try {
            String line = null;
            String[] wordsArray;
            BufferedReader in = new BufferedReader(new FileReader(fileToRead));

            while (true){
                line = in.readLine();
                if (line==null){
                    break;
                } else{
                    wordsArray = line.split("\t\t");
                    int id = Integer.parseInt(wordsArray[0]);
                    String plate = wordsArray[1];
                    String type = wordsArray[2];
                    LocalDateTime timeIn = LocalDateTime.parse(wordsArray[3]);
                    LocalDateTime timeOut = null;

                    if (!wordsArray[4].equalsIgnoreCase("null")){
                        timeOut = LocalDateTime.parse(wordsArray[4]);
                    }

                    double price = Double.parseDouble(wordsArray[5]);
                    double payment = Double.parseDouble(wordsArray[6]);
                    boolean parked = Boolean.parseBoolean(wordsArray[7]);

                    ParkingLot.parseData(id,
                            plate,
                            type,
                            timeIn,
                            timeOut,
                            price,
                            payment,
                            parked
                    );
                }
            }

        } catch (Exception e) {
            System.out.println("Unable to Load Data");
        }

        try {
            String line = null;
            String[] wordsArray;
            BufferedReader in = new BufferedReader(new FileReader(settingsFile));

            while (true){
                line = in.readLine();
                if (line==null){
                    break;
                } else{
                    wordsArray = line.split("\t\t");
                    int freeOfCharge = Integer.parseInt(wordsArray[0]);
                    int chargePeriod = Integer.parseInt(wordsArray[1]);
                    int firstPeriod = Integer.parseInt(wordsArray[2]);
                    double firstPriceCar = Double.parseDouble(wordsArray[3]);
                    double periodPriceCar = Double.parseDouble(wordsArray[4]);
                    double firstPriceBike = Double.parseDouble(wordsArray[5]);
                    double periodPriceBike = Double.parseDouble(wordsArray[6]);

                    Settings.setFreeOfCharge(freeOfCharge);
                    Settings.setChargePeriod(chargePeriod);
                    Settings.setFirstPeriod(firstPeriod);
                    Settings.setFirstPriceCar(firstPriceCar);
                    Settings.setPeriodPriceCar(periodPriceCar);
                    Settings.setFirstPriceBike(firstPriceBike);
                    Settings.setPeriodPriceBike(periodPriceBike);

                }
            }

        } catch (Exception e) {
            System.out.println("Unable to Load Settings");
        }

    }

}
