import java.util.Scanner;

public class UserInput {

    private static Scanner scan = new Scanner (System.in);

    static String input (String field){

        boolean validInput = false;
        String inputText = "";

        while(!validInput){
            inputText = scan.next();
            switch (field.toLowerCase()){
                case "plate":
                    if (isPlateValid(inputText)){
                        validInput = true;
                    }
                    break;
                case "type":
                    if (isTypeValid(inputText)){
                        validInput = true;
                    }
                    break;
                default:
                    validInput = true;
                    break;
            }
        }

        return inputText;
    }

    private static boolean isPlateValid (String plate){
        boolean valid = false;
        String regex = "([A-Za-z]){3}-(\\d){4}$";
        if (plate.matches(regex)){
            valid=true;
        }
        return valid;
    }

    private static boolean isTypeValid (String type){
        boolean valid = false;
        if (type.equalsIgnoreCase("CAR")
                ||type.equalsIgnoreCase("MOTORCYCLE")){
            valid=true;
        }
        return valid;
    }

}
