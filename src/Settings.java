import static java.lang.Integer.parseInt;

public class Settings {
    // Holds the Parking Meter Settings
    private static int freeOfCharge = 20;
    private static int chargePeriod = 60;
    private static int firstPeriod = 180;

    private static double firstPriceCar = 5.0;
    private static double periodPriceCar = 2.5;

    private static double firstPriceBike = 3.0;
    private static double periodPriceBike = 1.5;

    private static final int PARKING_LOT_SIZE = 50;

    public static void setFreeOfCharge(int freeOfCharge) {
        Settings.freeOfCharge = freeOfCharge;
    }

    public static void setChargePeriod(int chargePeriod) {
        Settings.chargePeriod = chargePeriod;
    }

    public static void setFirstPeriod(int firstPeriod) {
        Settings.firstPeriod = firstPeriod;
    }

    public static void setFirstPriceCar(double firstPriceCar) {
        Settings.firstPriceCar = firstPriceCar;
    }

    public static void setPeriodPriceCar(double periodPriceCar) {
        Settings.periodPriceCar = periodPriceCar;
    }

    public static void setFirstPriceBike(double firstPriceBike) {
        Settings.firstPriceBike = firstPriceBike;
    }

    public static void setPeriodPriceBike(double periodPriceBike) {
        Settings.periodPriceBike = periodPriceBike;
    }

    static void changeSettings (String field) {

        String valueStr = " ";
        String afterStr = " ";

        switch (field){
            case "freeOfCharge":
                valueStr = String.valueOf(freeOfCharge);
                break;
            case "chargePeriod":
                valueStr = String.valueOf(chargePeriod);
                break;
            case "firstPeriod":
                valueStr = String.valueOf(firstPeriod);
                break;
            case "firstPriceCar":
                valueStr = String.valueOf(firstPriceCar);
                break;
            case "periodPriceCar":
                valueStr = String.valueOf(periodPriceCar);
                break;
            case "firstPriceBike":
                valueStr = String.valueOf(firstPriceBike);
                break;
            case "periodPriceBike":
                valueStr = String.valueOf(periodPriceBike);
                break;
            default:
                System.out.println("Unexpected input Error at ChangeSettings function ");
        }

        UserInterface.printChangeSettings(field, "ini", valueStr);
        String input = UserInput.input("settings");

        switch (field){
            case "freeOfCharge":
                Settings.freeOfCharge = parseInt(input);
                afterStr = String.valueOf(freeOfCharge);
                break;
            case "chargePeriod":
                Settings.chargePeriod = parseInt(input);
                afterStr = String.valueOf(chargePeriod);
                break;
            case "firstPeriod":
                Settings.firstPeriod = parseInt(input);
                afterStr = String.valueOf(firstPeriod);
                break;
            case "firstPriceCar":
                Settings.firstPriceCar = parseInt(input);
                afterStr = String.valueOf(firstPriceCar);
                break;
            case "periodPriceCar":
                Settings.periodPriceCar = parseInt(input);
                afterStr = String.valueOf(periodPriceCar);
                break;
            case "firstPriceBike":
                Settings.firstPriceBike = parseInt(input);
                afterStr = String.valueOf(firstPriceBike);
                break;
            case "periodPriceBike":
                Settings.periodPriceBike = parseInt(input);
                afterStr = String.valueOf(periodPriceBike);
                break;
            default:
                System.out.println("Unexpected input Error at ChangeSettings function ");
        }

        UserInterface.printChangeSettings("period", "end", afterStr);
    }

    public static int getFreeTime(){
        return freeOfCharge;
    }

    public static int getFirstPeriod(){
        return firstPeriod;
    }

    public static int getChargePeriod(){
        return chargePeriod;
    }

    public static double getFirstPrice(String type){
        if (type.equalsIgnoreCase("motorcycle")){
            return firstPriceBike;
        } else {
            return firstPriceCar;
        }
    }

    public static double getPeriodPrice(String type){
        if (type.equalsIgnoreCase("motorcycle")){
            return periodPriceBike;
        } else {
            return periodPriceCar;
        }
    }

}
