import java.time.LocalDateTime;

public class Main {

    public static final String VERSION = "V0.3";

    static int menu = 0;
    static boolean display = false;
    static boolean exit = false;

    static final String[] HOME_ITEMS = {
            "Check in",
            "Check out",
            "Vehicles",
            "Settings"
    };
    static final String[] SETTINGS_ITEMS = {
            "Free of Charge Period",
            "Charge Period",
            "First Period",
            "Change prices",
            "Login Settings",
            "Back to Main Menu"
    };
    static final String[] LOGIN_MENU_ITEMS = {
            "Change Password",
            "Remove User",
            "Create User",
            "Back to Settings Menu"
    };
    static final String[] PRICE_MENU_ITEMS = {
            "Car Entrance Price",
            "Car Period Price",
            "Motorcycle Entrance Price",
            "Motorcycle Period Price",
            "Back to Settings Menu"
    };


    public static void main(String[] args) {

        DataManager.loadData();

        UserInterface.getStart();
        UserInterface.getMenu("home");

        while (true){

            if (menu == 0){
                selectOperation();
            } else if (menu == 1){
                selectSettings();
            } else if (menu == 2){
                selectLoginOperation();
            } else if (menu == 3){
                selectPriceOperation();
            }

            if (exit){
                break;
            }
        }


        DataManager.saveAll();

    }

    private static void selectOperation() {
        if (display){
            UserInterface.getMenu("home");
            display = false;
        }
        UserInterface.printRequest("opr");
        String action = UserInput.input("opr");

        switch (action.toLowerCase()){
            case "1":
            case "checkin":
            case "check in":
                ParkingMeter.checkIn();
                break;
            case "2":
            case "checkout":
            case "check out":
                ParkingMeter.checkOut();
                break;
            case "3":
            case "vehicles":
                ParkingMeter.getParkedVehicles();
                break;
            case "4":
            case "settings":
                menu = 1;
                display = true;
                break;
            case "exit":
                exit = true;
                break;
            case "clock":
                Clock.hackTime();
                break;
            case "menu":
                display = true;
                break;
            default:
                System.out.println("Invalid Input");
        }
    }

    private static void selectSettings() {
        if (display){
            UserInterface.getMenu("settings");
            display = false;
        }
        UserInterface.printRequest("opr");
        String action = UserInput.input("opr");

        switch (action.toLowerCase()){
            case "1":
            case "free of charge period":
                Settings.changeSettings("freeOfCharge");
                break;
            case "2":
            case "charge period":
                Settings.changeSettings("chargePeriod");
                break;
            case "3":
            case "first period":
                Settings.changeSettings("firstPeriod");
                break;
            case "4":
            case "charge prices":
                menu = 3;
                display = true;
                break;
            case "5":
            case "login settings":
                menu = 2;
                display = true;
                break;
            case "6":
            case "back to main menu":
                menu = 0;
                display = true;
                break;
            case "menu":
                display = true;
                break;
            default:
                System.out.println("Invalid Input");
                break;
        }
    }

    private static void selectLoginOperation() {
        if (display){
            UserInterface.getMenu("loginMenu");
            display = false;
        }
        UserInterface.printRequest("opr");
        String action = UserInput.input("opr");

        switch (action.toLowerCase()){
            case "1":
            case "change password":
                ParkingMeter.checkIn();
                break;
            case "2":
            case "remove user":
                ParkingMeter.checkOut();
                break;
            case "3":
            case "create user":
                ParkingMeter.checkOut();
                break;
            case "4":
            case "back":
            case "back to settings menu":
                menu = 1;
                display = true;
                break;
            case "menu":
                display = true;
                break;
            default:
                System.out.println("Invalid Input");
        }
    }

    private static void selectPriceOperation() {
        if (display){
            UserInterface.getMenu("priceMenu");
            display = false;
        }
        UserInterface.printRequest("opr");
        String action = UserInput.input("opr");

        switch (action.toLowerCase()){
            case "1":
            case "car entrance price":
                Settings.changeSettings("firstPriceCar");
                break;
            case "2":
            case "car period price":
                Settings.changeSettings("periodPriceCar");
                break;
            case "3":
            case "motorcycle entrance price":
                Settings.changeSettings("firstPriceBike");
                break;
            case "4":
            case "motorcycle period price":
                Settings.changeSettings("periodPriceBike");
                break;
            case "5":
            case "back":
            case "back to settings menu":
                menu = 1;
                display = true;
                break;
            case "menu":
                display = true;
                break;
            default:
                System.out.println("Invalid Input");
        }
    }

}
