import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserInterface {

    // ------------------------ UI Interface SIZE ------------------------------
    private static int width = 85;
    private static int margin = 5;


    // -------------------------- UI Formatters --------------------------------
    static DateTimeFormatter dateTimeFormatter =
            DateTimeFormatter.ofPattern("yyyy/MM/dd '-' HH:mm:ss");
    static DateTimeFormatter listFormatter =
            DateTimeFormatter.ofPattern("yyyy/MM/dd '-' HH:mm");
    static DateTimeFormatter timeFormatter =
            DateTimeFormatter.ofPattern("HH:mm");
    static DecimalFormat priceFormatter =
            new DecimalFormat( "'R$ '#,###,###,##0.00" );


    //////////////////////////////// UI TEXT \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    // --------------------------- ALERT TEXT ----------------------------------
    private static final String LOGIN = "You are Logged In";


    // ---------------------------- GUI TEXT -----------------------------------
    private static final String WELCOME = "WELCOME TO PARKMETER";
    private static final String PLEASE_LOGIN = "please make sure to log in before use";
    private static final String CHECK_IN = "VEHICLE CHECK-IN";
    private static final String CHECK_OUT = "VEHICLE CHECK-OUT";


    // ------------------------- INPUT REQUESTS --------------------------------
    private static final String PLATE = "Enter the License Plate (XXX-0000): ";
    private static final String TYPE = "Enter the Vehicle Type (Motorcycle/Car): ";
    private static final String PAYMENT = "Payment received: ";
    private static final String PASSWORD = "Enter your password: ";
    private static final String CLOCK_HACK = "Minutes to add: ";
    private static final String OPR = "Operation: ";


    // --------------------------- ERROR TEXT ----------------------------------
    private static final String NOT_FOUND = "Vehicle not found!";
    private static final String NO_MONEY = "Not enough money to pay the bill!";
    private static final String ALREADY_PARKED = "This vehicle is already parked!";
    private static final String INVALID_PASSWORD = "Wrong password";
    private static final String INPUT_ERROR = "Invalid Input";

    private static final String INVALID_PLATE =
            "\nPlate is invalid please enter a plate following (XXX-0000)\n";
    private static final String INVALID_TYPE =
            "\nType is invalid please enter (Car or Motorcycle)\n";


    private static String formatDateTime (LocalDateTime dateTime, String mode){
        if (mode.equalsIgnoreCase("full")){
            return dateTime.format(dateTimeFormatter);
        } else{
            return dateTime.format(timeFormatter);
        }
    }

    private static String lineBuilder (boolean fill){
        StringBuilder line = new StringBuilder();

        if (fill){
            for (int i=0; i<width; i++){
                if (i==0){
                    line.append("+");
                } else if (i == (width-1)){
                    line.append("+\n");
                } else {
                    line.append("-");
                }
            }
        } else {
            for (int i=0; i<width; i++){
                if (i==0){
                    line.append("|");
                } else if (i == (width-1)){
                    line.append("|\n");
                } else {
                    line.append(" ");
                }
            }
        }

        return String.valueOf(line);
    }

    private static String blankSpaces (int blanks){
        StringBuilder spaces = new StringBuilder();

        for (int i=0; i<blanks; i++){
            spaces.append(" ");
        }

        return String.valueOf(spaces);
    }

    private static String headerBuilder (String title){
        StringBuilder header = new StringBuilder();

        // Defining blank spaces before and after Title
        int beforeTitle = (width - title.length() - 2)/2;
        int afterTitle = width - 2 - beforeTitle - title.length();

        header.append("\n");
        header.append(lineBuilder(true));
        header.append("|");
        header.append(blankSpaces(beforeTitle));
        header.append(title);
        header.append(blankSpaces(afterTitle));
        header.append("|\n");
        header.append(lineBuilder(true));

        return String.valueOf(header);
    }

    private static String optionsBuilder (String[] options){
        StringBuilder optionsText = new StringBuilder();

        int afterOption; // blank spaces after option text

        for (int i=0; i<options.length; i++){
            optionsText.append("|");
            optionsText.append(blankSpaces(margin));
            optionsText.append(i+1);
            optionsText.append(" - ");
            optionsText.append(options[i]);
            afterOption = width - margin - options[i].length() - 6;
            optionsText.append(blankSpaces(afterOption));
            optionsText.append("|\n");
        }

        return String.valueOf(optionsText);
    }

    private static String centerTextBuilder (String text){
        StringBuilder centerText = new StringBuilder();

        int beforeText = (width - text.length() - 2)/2;
        int afterText = width - 2 - beforeText - text.length();

        centerText.append("|");
        centerText.append(blankSpaces(beforeText));
        centerText.append(text);
        centerText.append(blankSpaces(afterText));
        centerText.append("|\n");

        return String.valueOf(centerText);
    }

    static void getMenu(String menu){
        StringBuilder fullMenu = new StringBuilder();
        String[] options = {};

        switch (menu){
            case "settings":
                options = Main.SETTINGS_ITEMS;
                fullMenu.append(headerBuilder("SETTINGS"));
                break;
            case "loginMenu":
                options = Main.LOGIN_MENU_ITEMS;
                fullMenu.append(headerBuilder("LOGIN SETTINGS"));
                break;
            case "priceMenu":
                options = Main.PRICE_MENU_ITEMS;
                fullMenu.append(headerBuilder("PRICE SETTINGS"));
                break;
            default:
                options = Main.HOME_ITEMS;
                fullMenu.append(headerBuilder("PARKMETER"));
                break;
        }

        fullMenu.append(lineBuilder(false));
        fullMenu.append(optionsBuilder(options));
        fullMenu.append(lineBuilder(false));
        fullMenu.append(lineBuilder(true));

        System.out.println(fullMenu);
    }

    static void getStart(){
        StringBuilder start = new StringBuilder();

        int beforeVersion = width - Main.VERSION.length() - 3;

        start.append(lineBuilder(true));
        start.append(lineBuilder(false));
        start.append(centerTextBuilder(WELCOME));
        start.append(lineBuilder(false));
        start.append(centerTextBuilder(PLEASE_LOGIN));
        start.append("|");
        start.append(blankSpaces(beforeVersion));
        start.append(Main.VERSION);
        start.append(" |\n");
        start.append(lineBuilder(true));

        System.out.print(start);
    }

    static void getCheckInHeader(){
        System.out.println(headerBuilder(CHECK_IN));
    }

    static void getCheckOutHeader(){
        System.out.println(headerBuilder(CHECK_OUT));
    }

    static void printClockHack(String state){
        if (state.equalsIgnoreCase("ini")){
            System.out.println("\n"+lineBuilder(true));
            System.out.println("Original time: ");
            System.out.println(formatDateTime(Clock.time(), "full")+"\n");
            System.out.print(CLOCK_HACK);
        } else{
            System.out.println("\nNew time: ");
            System.out.println(formatDateTime(Clock.time(), "full")+"\n");
            System.out.println(lineBuilder(true));
        }

    }

    static void printRequest(String state){
        switch (state){
            case "plate":
                System.out.print(PLATE);
                break;
            case "type":
                System.out.print("\n" + TYPE);
                break;
            case "payment":
                System.out.print("\n" + PAYMENT);
            case "opr":
                System.out.print(OPR);
        }
    }

    static void printReadyToPark (String plate, String type){
        System.out.print("\n" + type.toUpperCase() + " - " + plate.toUpperCase());
        System.out.print(" entered at: ");
        System.out.print(formatDateTime(Clock.time(), "time"));
        System.out.print(" and is ready to park!\n\n");
        System.out.println(lineBuilder(true));
    }

    static void printCheckOut (String state, int minutes, double cash){
        switch (state){
            case "timeAndPrice":
                int min = minutes % 60;
                int hr = (minutes - min)/60;
                System.out.printf
                        ("\nYou parked here for %d hours and  %d minutes. It will be %s \n"
                        ,hr , min, priceFormatter.format(cash));
                break;
            case "change":
                System.out.printf("Your change is %s", priceFormatter.format(cash));
                break;
            case "print":
                System.out.println("\n\nPrint Receipt? (y/n)");
                break;
            default:
                System.out.println(lineBuilder(true));
                break;
        }
    }

    static void printList (Vehicle vehicle, int index, int listSize ){
        int listSpacing = (width - (2*margin) - 49)/3;
        int firstSpacing = (width - (2*listSpacing) - (2*margin) - 49);
        int dashBreak = (width-(2*margin));
        String id = String.format("%08d", vehicle.getID());

        StringBuilder list = new StringBuilder();

        if (index == 0){
            list.append(headerBuilder("LIST OF PARKED VEHICLES"));
            list.append(lineBuilder(false));
        }

        list.append("|");
        list.append(blankSpaces(margin));
        list.append("ID:");
        list.append(id);
        list.append(blankSpaces(firstSpacing));
        list.append(vehicle.getVehicleType());

        if (vehicle.getVehicleType().equalsIgnoreCase("car")){
            list.append("       ");
        }

        list.append(blankSpaces(listSpacing));
        list.append(vehicle.getLicensePlate());
        list.append(blankSpaces(listSpacing));
        list.append(vehicle.getEntranceTime().format(listFormatter));
        list.append(blankSpaces(margin));
        list.append("|\n");

        if (index < listSize-1){
            list.append("|");
            list.append(blankSpaces(margin-1));
            for (int i =0; i<dashBreak; i++){
                list.append("-");
            }

            list.append(blankSpaces(margin-1));
            list.append("|\n");
        }

        if (index == listSize-1) {
            list.append(lineBuilder(false));
            list.append(lineBuilder(true));
            list.append("\n");
        }

        System.out.print(list);
    }

    static void printChangeSettings (String field, String state, String value){
        switch (field){
            case "period":
                if (state.equals("ini")){
                    System.out.println("\n Your current charge period is every "
                            +value+ " minutes.");
                    System.out.print("\n Change charging period to: ");
                } else{
                    System.out.println("\nYour charge period was changed to " +value+ " minutes.");
                }
                break;
            case "freeOfCharge":
                if (state.equals("ini")){
                    System.out.println("Your current free of charge period is "+value+ " minutes.");
                    System.out.print("\n Change free of charge period to: ");
                } else{
                    System.out.println("\nYour free of charge period was changed to "
                            +value+ " minutes.\n");
                }
                break;
            case "firstPeriod":
                if (state.equals("ini")){
                    System.out.println("Your current first period is "+value+ " minutes long.");
                    System.out.print("\n Change first period duration to: ");
                } else{
                    System.out.println("\nYour first period was changed to be "
                            +value+ " minutes long.\n");
                }
                break;
            case "carPeriodPrice":
                if (state.equals("ini")){
                    System.out.println("The current price for a car period is: R$" + value);
                    System.out.print("\n Change first period duration to: ");
                } else{
                    System.out.println("\nThe period price for cars was changed to R$"
                            +value+ "\n");
                }
                break;
        }
    }

    static void printError (String type){

        switch (type){
            case "parked":
                System.out.println(ALREADY_PARKED);
                break;
            case "notFound":
                System.out.println(NOT_FOUND);
                break;
            case "lowPayment":
                System.out.println(NO_MONEY);
        }

    }

}
