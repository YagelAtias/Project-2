package Race;
/**
 * @authors Yagel Atias 208905448, Slava Ignatiev 322015280
 */

import java.util.InputMismatchException;

/**
 * Represents a racer that can run on a track.
 */
public class Racer implements Runnable {

    // Static variable to track the global identifier for racers
    private static int globalId = 1;

    // Speed of the racer, must be between 1 and 10 (inclusive)
    private final int speed;

    // The track on which the racer is running
    private final Track track;

    // Unique identifier for each racer
    public int id;

    /**
     * Constructs a racer with a specified speed and track.
     *
     * @param speed The speed of the racer (between 1 and 10).
     * @param track The track on which the racer will run.
     * @throws InputMismatchException If the speed is not within the valid range.
     */
    public Racer(int speed, Track track) {
        if (speed < 1 || speed > 10)
            throw new InputMismatchException("The racer's speed must be between 1 and 10");
        else
            this.speed = speed;
        this.track = track;
        this.id = globalId++;
    }

    /**
     * The method representing the racer's movement on the track.
     * Uses synchronization to ensure thread safety when updating the shared track.
     */
    public synchronized void go() {
        final int FINISH_LINE = 100;
        Thread currentThread = Thread.currentThread();
        currentThread.setPriority(speed);

        for (int i = 1; i <= FINISH_LINE; i++) {
            try {
                Thread.sleep(100 / speed);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // Display the progress of the racer
            System.out.println("Runner " + id + " ran " + i + " meters");

            // Synchronize on the track to safely update shared information
            synchronized (track) {
                if (i == FINISH_LINE) {
                    track.incrementFinishedRacers();
                    System.out.print("Runner " + id + " finished ");

                    // Display the finishing position based on the number of finished racers
                    switch (track.getFinishedRacers()) {
                        case 1 -> System.out.println(1 + "st");
                        case 2 -> System.out.println(2 + "nd");
                        case 3 -> System.out.println(3 + "rd");
                        default -> System.out.println(track.getFinishedRacers() + "th");
                    }
                }
            }
        }
    }

    /**
     * Overrides the run method of the Runnable interface.
     * Initiates the racer's movement by calling the go method.
     */
    @Override
    public void run() {
        go();
    }
}
