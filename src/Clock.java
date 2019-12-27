import java.time.LocalDateTime;

import static java.lang.Integer.parseInt;

public class Clock {

    private static boolean clockHack = false;
    private static int timeDelta = 0;

    static LocalDateTime time(){
        LocalDateTime now = LocalDateTime.now();
        if (clockHack){
            now = now.plusMinutes(timeDelta);
        }
        return now;
    }

    static void hackTime(){
        UserInterface.printClockHack("ini");
        String minutes = UserInput.input("hackTime");
        clockHack = true;
        timeDelta += parseInt(minutes);
        UserInterface.printClockHack("end");
    }
}
