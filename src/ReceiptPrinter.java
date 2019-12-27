import jdk.swing.interop.SwingInterOpUtils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ReceiptPrinter {
    //Function to print the receipt to file and to print the receipt to the console

    private static int receiptWidth = 58;


    static void print (Vehicle vehicle){

        String receiptID = String.format("%08d", vehicle.getID());
        String licensePlate = vehicle.getLicensePlate();
        String vehicleType = vehicle.getVehicleType();

        double price = vehicle.getPrice();
        double payment = vehicle.getPayment();
        double change = payment - price;

        LocalDateTime entranceTime = vehicle.getEntranceTime();
        LocalDateTime departureTime = vehicle.getDepartureTime();

        String priceStr = UserInterface.priceFormatter.format(price);
        String paymentStr = UserInterface.priceFormatter.format(payment);
        String changeStr = UserInterface.priceFormatter.format(change);

        String entranceTimeStr = vehicle.getEntranceTime()
                .format(UserInterface.dateTimeFormatter);
        String departureTimeStr = vehicle.getDepartureTime()
                .format(UserInterface.dateTimeFormatter);

        int parkedMin = (int)entranceTime.until(departureTime, ChronoUnit.MINUTES);

        int min = parkedMin % 60;
        int hr = (parkedMin - min)/60;

        StringBuilder receiptText = new StringBuilder();

        receiptText.append(createLine());
        receiptText.append(printCenteredText(("Ticket: " + receiptID)));
        receiptText.append(printCenteredText((vehicleType + " " + licensePlate)));
        receiptText.append(createLine());

        receiptText.append(
                "\n" +
                "ESTACIONAMENTO RANDOM SHOPPING\n" +
                "END.: AVENIDA ALEATORIA, 299\n" +
                "CNPJ: 00.000.000/0001-00\n" +
                "INSC. MUNIC.: 001122\n");

        receiptText.append(createLine());

        receiptText.append("\nENTRADA:   ");
        receiptText.append(entranceTimeStr).append("\n");

        receiptText.append("\nSAIDA:     ");
        receiptText.append(departureTimeStr).append("\n");

        receiptText.append("\nPERMANENCIA:   ");
        receiptText.append(hr).append(":").append(min).append("\n");

        receiptText.append(createLine());

        receiptText.append("\nVALOR TOTAL:   ");
        receiptText.append(priceStr).append("\n");

        receiptText.append("\nVALOR PAGO:    ");
        receiptText.append(paymentStr).append("\n\n");

        receiptText.append("\nTROCO:         ");
        receiptText.append(changeStr).append("\n");

        receiptText.append(createLine());

        receiptText.append(
                printCenteredText("ATENDENDO LEI 12.741 DE 8/12/2012")).append("\n");
        receiptText.append(
                printCenteredText("INFORM. A INCID. DE ISS, PIS E COFINS")).append("\n");
        receiptText.append(
                printCenteredText("NO PERCENTUAL DE 8,65 S/ SERV. PREST.")).append("\n");
        receiptText.append(
                printCenteredText("A V.SAS. ALEM DE OUTRO TRIB. FEDERAIS")).append("\n");
        receiptText.append(
                printCenteredText("APLICAVEIS AO RESULTADO DA EMPRESA"));

        receiptText.append(createLine());

        System.out.println(receiptText);
    }


    private static String createLine (){
        StringBuilder line = new StringBuilder();
        line.append("\n");
        line.append("-".repeat(Math.max(0, receiptWidth)));
        line.append("\n");
        return String.valueOf(line);
    }

    private static String printCenteredText (String str){
        StringBuilder centeredText = new StringBuilder();

        // Defining blank spaces before and after Title
        int beforeTitle = (receiptWidth - str.length())/2;

        for (int i =0; i<beforeTitle; i++){
            centeredText.append(' ');
        }

        centeredText.append(str);

        return String.valueOf(centeredText);
    }



}
