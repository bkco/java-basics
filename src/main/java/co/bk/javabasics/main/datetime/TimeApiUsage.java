package co.bk.javabasics.main.datetime;

import java.time.Instant;

/**
 * @author bkco
 */
public class TimeApiUsage {

    public static void main(String[] args) {
        TimeApiUsage tua = new TimeApiUsage();
        tua.runExercises();
    }


    public void runExercises() {
        System.out.println("JDK 8 time api examples");

        getSeconds();
    }


    /**
     * Timestamp in seconds from UTC generated
     */
    private void getSeconds() {
        long seconds = Instant.now().getEpochSecond();
        System.out.println("getSeconds() now! " + seconds);
    }
}
